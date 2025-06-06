package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import controllers.*;
import fileReader.FileChooser;
import model.Solution;
import view.MatrixHandler.MatrixHandler;

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
    private JTable table;
    private JPanel solutionsPanel;
    private JPanel optionsPanel;
	private PropMaker maker = new PropMaker();
	private FileChooser chooser = new FileChooser();
	private MatrixHandler matrixController;
	private ReaderController rController = new ReaderController();
	private RandomController randController = new RandomController();

	public View() {
		setUpFrame();
		createPanels();
		addPanelsToFrame();
		
		matrixController = new MatrixHandler(solutionsPanel, txtRoute, txtWithoutPruning,
				txtWithPruning, btnGenerate, comboBox, progressBar, rController, randController);
		
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
		optionsPanel.add(maker.createLabel(10, 333, 100, 20, "Sin poda:"));
		optionsPanel.add(maker.createLabel(194, 333, 100, 20, "Con poda:"));
		optionsPanel.add(maker.createLabel(10, 307, 100, 20, "Tiempo total:"));
	}
	
	private void addTextFieldsToOptionsPanel() {
		optionsPanel.add(txtRoute = maker.createTextField(10, 35, 200, 25, ""));
		table = new JTable();
		table.setBounds(60, 260, 200, 150);
		optionsPanel.add(table);
		table.setVisible(false);
		optionsPanel.add(txtWithoutPruning = maker.createTextField(10, 356, 110, 25, null));
		optionsPanel.add(txtWithPruning = maker.createTextField(194, 356, 110, 25, null));

		txtRoute.setEditable(false);
		txtWithoutPruning.setEditable(false);
		txtWithoutPruning.setVisible(false);
		txtWithPruning.setEditable(false);
		txtWithPruning.setVisible(false);
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
		optionsPanel.add(progressBar = maker.createProgressBar(10, 145, 280, 30));
	}

	private void setUpListeners() {
		btnLoad.addActionListener(e -> {
		    chooser.fileSelector(txtRoute, this);
		    matrixController.loadMatrix();
		});

		btnRandomMatrix.addActionListener(e -> matrixController.loadRandomMatrix());

		btnGenerate.addActionListener(e -> matrixController.generateSolutions());

		comboBox.addItemListener(e -> {
		    matrixController.getDrawer().resetMatrixColors();
		    int index = comboBox.getSelectedIndex();
		    List<Solution> solutions = matrixController.getBruteForceController().getSolutions();
		    matrixController.getDrawer().showSolutionPath(solutions.get(index));
		});

	}
}