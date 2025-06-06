package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import controllers.*;
import model.Solution;

public class View extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField txtRoute;
	private JTextField txtWithPruning;
	private JTextField txtWithoutPruning;
	
	private JButton btnLoad;
	private JButton btnGenerate;
	private JButton btnRandomMatrix;
    private JComboBox<String> comboBox;
    private JProgressBar progressBar;
    private JPanel solutionsPanel;
    private JPanel optionsPanel;
	private JLabel[][] labels;
	
	private PropMaker maker = new PropMaker();
	private FileChooser chooser = new FileChooser();
	private Visualizer drawer;
	private SolutionEventHandler solutionHandler = null;
	private BruteForceController bfController;
	private TimerController tController;
	private ReaderController rController = new ReaderController();
	private RandomController randController = new RandomController();

	public View() {
		setUpFrame();
		createPanels();
		addPanelsToFrame();
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
		optionsPanel.add(maker.createLabel(10, 240, 106, 20, "Soluciones:"));
		optionsPanel.add(maker.createLabel(10, 333, 100, 20, "Sin poda:"));
		optionsPanel.add(maker.createLabel(194, 333, 100, 20, "Con poda:"));
		optionsPanel.add(maker.createLabel(10, 307, 100, 20, "Tiempo total:"));
	}
	
	private void addTextFieldsToOptionsPanel() {
		optionsPanel.add(txtRoute = maker.createTextField(10, 35, 200, 25, ""));
		optionsPanel.add(txtWithoutPruning = maker.createTextField(10, 356, 110, 25, null));
		optionsPanel.add(txtWithPruning = maker.createTextField(194, 356, 110, 25, null));

		txtRoute.setEditable(false);
		txtWithoutPruning.setEditable(false);
		txtWithoutPruning.setVisible(false);
		txtWithPruning.setEditable(false);
		txtWithPruning.setVisible(false);
	}
	
	private void addButtonsToOptionsPanel() {
		optionsPanel.add(btnGenerate = maker.createButton(70, 130, 160, 30, "Generar Soluciones"));
		btnGenerate.setEnabled(false);
		optionsPanel.add(btnLoad = maker.createButton(215, 35, 80, 25, "Cargar"));
		optionsPanel.add(btnRandomMatrix = maker.createButton(60, 70, 180, 25, "Cargar Matriz Aleatoria"));
	}
	
	private void addMiscToOptionsPanel() {
		optionsPanel.add(comboBox = maker.createStringComboBox(10, 265, 199, 25));
		comboBox.setEnabled(false);
		optionsPanel.add(progressBar = maker.createProgressBar(10, 180, 280, 30));
	}

	private void setUpListeners() {
		btnLoad.addActionListener(e-> { chooser.fileSelector(txtRoute, this); loadMatrix(); });
		
		btnGenerate.addActionListener(e -> { generateSolutions(); });
		
		btnRandomMatrix.addActionListener(e->{ loadRandomMatrix(); });
		
		comboBox.addItemListener(e -> {
			drawer.resetMatrixColors();
			int index = comboBox.getSelectedIndex();
			List<Solution> solutions = bfController.getSolutions();
			drawer.showSolutionPath(solutions.get(index));
		});
	}

	private void loadMatrix() {
		try {
			solutionsPanel.removeAll();
			if (drawer!=null)
				drawer.hideTime();
			String route = txtRoute.getText();
			if (route==null || route.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionada ningun archivo", "Aviso", JOptionPane.WARNING_MESSAGE);			
				return;
			}
			rController.readFile(route);
			if (rController.getMatrix()==null) {
				JOptionPane.showMessageDialog(null, "No se pudo cargar la matriz del archivo", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			bfController = new BruteForceController(rController.getMatrix());
			int[] attributes = rController.getMatrixAttributes();
			labels= new JLabel[attributes[0]][attributes[1]];
			solutionsPanel.setLayout(new GridLayout(attributes[0], attributes[1], 3, 3));
			drawer = new Visualizer(labels, txtWithoutPruning, txtWithPruning);
			drawer.drawMatrix(solutionsPanel, rController.getMatrix());
			solutionsPanel.revalidate();
			solutionsPanel.repaint();
			comboBox.setEnabled(false);
			btnGenerate.setEnabled(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(optionsPanel, "Insertar valores validos por favor", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	
	}
	
	private void generateSolutions() {
		drawer.resetMatrixColors();
		tController= new TimerController();
		solutionHandler = new SolutionEventHandler(bfController, progressBar, comboBox, drawer, 
				tController);
		solutionHandler.execute();
	}
	
	private void loadRandomMatrix() {
		try {
			solutionsPanel.removeAll();
			if (drawer!=null)
				drawer.hideTime();
			int[][] matrix = randController.getMatrix();
			bfController = new BruteForceController(matrix);
			labels= new JLabel[matrix.length][matrix[0].length];
			solutionsPanel.setLayout(new GridLayout(matrix.length, matrix[0].length, 3, 3));
			drawer = new Visualizer(labels, txtWithoutPruning, txtWithPruning);
			drawer.drawMatrix(solutionsPanel, matrix);
			solutionsPanel.revalidate();
			solutionsPanel.repaint();
			comboBox.setEnabled(false);
			btnGenerate.setEnabled(true);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(optionsPanel, "Insertar valores validos por favor", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
