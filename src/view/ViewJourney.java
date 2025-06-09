package view;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.*;
import controllers.*;
import model.Solution;
import view.helper.*;

public class ViewJourney extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtRoute;
	private JLabel[][] labels;
	private JButton btnLoad;
	private JButton btnGenerate;
	private JButton btnRandomMatrix;
	private JComboBox<String> comboBox;
	private JProgressBar progressBar;
	private DataTable table;
	private JPanel solutionsPanel;
	private JPanel optionsPanel;
	private PropMaker maker = new PropMaker();
	private FileChooser chooser = new FileChooser();
	private SolutionVisualizer visualizer;
	private BruteForceController bfController;
	private ReaderController rController = new ReaderController();
	private RandomController randController = new RandomController();
	private TimerController tController;
	private SolutionEventHandler solutionHandler = null;
	

	public ViewJourney() {
		setUpFrame();
		createPanels();
		addPanelsToFrame();
		visualizer = new SolutionVisualizer(progressBar, table);
		setUpListeners();
	}

	private void setUpFrame() {
		setTitle("Titulo");
		setBounds(100, 100, 800, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}

	private void createPanels() {
		optionsPanel = maker.createPanel(475, 11, 300, 420);
		setUpOptionsPanel();
		solutionsPanel = maker.createPanel(10, 11, 454, 420);
		solutionsPanel.setBackground(Color.black);
		solutionsPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.black, null, null, null));
	}

	private void addPanelsToFrame() {
		getContentPane().add(optionsPanel);
		getContentPane().add(solutionsPanel);
	}

	private void setUpOptionsPanel() {
		addLabelsToOptionsPanel();
		addTextFieldsToOptionsPanel();
		addButtonsToOptionsPanel();
		addMiscToOptionsPanel();
	}

	private void addLabelsToOptionsPanel() {
		optionsPanel.add(maker.createLabel(10, 10, 183, 30, "Ingresa ruta del archivo:"));
		optionsPanel.add(maker.createLabel(10, 185, 106, 20, "Soluciones:"));
	}

	private void addTextFieldsToOptionsPanel() {
		optionsPanel.add(txtRoute = maker.createTextField(10, 35, 200, 25, ""));

		txtRoute.setEditable(false);
	}

	private void addButtonsToOptionsPanel() {
		optionsPanel.add(btnGenerate = maker.createButton(70, 105, 160, 30, "Generar Soluciones"));
		btnGenerate.setEnabled(false);
		optionsPanel.add(btnLoad = maker.createButton(215, 35, 80, 25, "Cargar"));
		optionsPanel.add(btnRandomMatrix = maker.createButton(60, 70, 180, 25, "Cargar Matriz Aleatoria"));
	}

	private void addMiscToOptionsPanel() {
		optionsPanel.add(comboBox = maker.createStringComboBox(10, 210, 199, 25));
		comboBox.setEnabled(false);
		optionsPanel.add(table = maker.createDataTable(15, 260, 270, 125));
		table.setVisible(false);
		optionsPanel.add(progressBar = maker.createProgressBar(10, 145, 280, 30));
	}

	private void setUpListeners() {
		btnLoad.addActionListener(e -> {
			if (!isSolutionHandlerRunning()) {
				chooser.fileSelector(txtRoute, this);
				int[][] matrix = FileVerification.loadMatrixFromFile(chooser, rController);
				if (matrix == null)
					return;
				rController.readFile(chooser.getRoute());
				loadMatrix(rController.getMatrix());
				bfController = new BruteForceController(rController.getMatrix());
				visualizer.setMatrixLabel(labels);
				visualizer.drawMatrix(solutionsPanel, rController.getMatrix());
				solutionsPanel.revalidate();
				solutionsPanel.repaint();
			} else {
				JOptionPane.showMessageDialog(this, "Ya hay una generación en proceso.", "Proceso en curso",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		});

		btnRandomMatrix.addActionListener(e -> {
			if (!isSolutionHandlerRunning()) {
				int[][] rm = randController.getMatrix();
				loadMatrix(rm);
				bfController = new BruteForceController(rm);
				visualizer.setMatrixLabel(labels);
				visualizer.drawMatrix(solutionsPanel, rm);
				solutionsPanel.revalidate();
				solutionsPanel.repaint();
			} else {
				JOptionPane.showMessageDialog(this, "Ya hay una generación en proceso.", "Proceso en curso",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		});

		btnGenerate.addActionListener(e -> generateSolutions());

		comboBox.addItemListener(e -> {
			visualizer.setMatrixLabel(labels);
			visualizer.resetMatrixColors();
			int index = comboBox.getSelectedIndex();
			List<Solution> solutions = bfController.getSolutions();
			visualizer.showSolutionPath(solutions.get(index));
		});

	}

	public void loadMatrix(int[][] matrix) {
		table.setVisible(false);
		labels = new JLabel[matrix.length][matrix[0].length];
		solutionsPanel.setLayout(new GridLayout(matrix.length, matrix[0].length, 3, 3));
		comboBox.setEnabled(false);
		btnGenerate.setEnabled(true);
	}

	public void generateSolutions() {
		
		tController = new TimerController(bfController.getMatrix());
		if (!isSolutionHandlerRunning()) {
			visualizer.setProgressBarIndeterminate();
			solutionHandler = new SolutionEventHandler(bfController, tController);
			solutionHandler.addPropertyChangeListener(e -> {
				try {
					if (!solutionHandler.isCancelled() && solutionHandler.getState() == SwingWorker.StateValue.DONE) {
						if (bfController.getAmountOfSolutions() != 0) {
							String[] solutions = new String[bfController.getAmountOfSolutions()];
							for (int i = 0; i < solutions.length; i++) {
								solutions[i] = "Solución " + (i + 1);

							}
							comboBox.setModel(new DefaultComboBoxModel<>(solutions));
							comboBox.setEnabled(true);
							visualizer.showSolutionPath(bfController.getSolutions().get(0)); // Mostramos la primera solución.
						}
						setupDataTable();
						visualizer.stopProgressBar();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
			solutionHandler.execute();
		}
		btnGenerate.setEnabled(false);
	}

	private void setupDataTable() {
		Map<Integer, String> data = new HashMap<Integer, String>();
		int[][] matrix = bfController.getMatrix();
		String matrixAttributes = "Matriz " + matrix[0].length + "x" + matrix.length;
		data.put(1, matrixAttributes);
		String timeWithoutBacktrack = Double.toString(tController.getBruteForceTime());
		System.out.println("without"+timeWithoutBacktrack);
		data.put(2, timeWithoutBacktrack);
		String timeWithBacktrack = Double.toString(tController.getPruningTime());
		data.put(3, timeWithBacktrack);
		System.out.println("with"+timeWithBacktrack);
		String generatedBruteaths = Integer.toString(bfController.getBruteCant());
		data.put(4, generatedBruteaths);
		String generatedPrunningPaths = Integer.toString(bfController.getPrunningCant());
		data.put(5, generatedPrunningPaths);
		visualizer.displayDataTable(data);
	}
	
	private boolean isSolutionHandlerRunning() {
		if (solutionHandler != null && solutionHandler.getState() == SwingWorker.StateValue.PENDING) {
			return true;
		}
		return false;
	}

}