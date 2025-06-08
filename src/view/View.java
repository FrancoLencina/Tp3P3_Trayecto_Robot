package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import controllers.*;
import model.Solution;
import view.helper.*;

public class View extends JFrame {
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
	private SolutionEventHandler solutionHandler = null;

	public View() {
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
				int [][]matrix= FileVerification.loadMatrixFromFile(this, chooser, rController);
				if (matrix == null)
					return;
				rController.readFile(chooser.getRoute());
				loadMatrix(rController.getMatrix());
				bfController = new BruteForceController(rController.getMatrix());
				visualizer.setMatrixLabel(labels);
				solutionsPanel.removeAll();
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
		if (!isSolutionHandlerRunning()) {
			solutionHandler = new SolutionEventHandler(bfController, visualizer, comboBox);
			solutionHandler.execute();
		}
		btnGenerate.setEnabled(false);
	}

	private boolean isSolutionHandlerRunning() {
		if (solutionHandler != null && solutionHandler.isRunning()) {
			return true;
		}
		return false;
	}

}