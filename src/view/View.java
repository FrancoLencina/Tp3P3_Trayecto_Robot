package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import controllers.*;
import model.Solution;

public class View {

	private JFrame frame;
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
    
	private ReaderController rController = new ReaderController();
	private PropMaker maker = new PropMaker();
	private BruteForceController bfController;
	private TimerController tc;
	private SolutionEventHandler solutionHandler = null;
	private JLabel[][] labels;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setUpFrame();
		createPanels();
		addPanelsToFrame();
        setUpListeners();
	}

	private void setUpFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	private void createPanels() {
		optionsPanel = maker.createPanel(475, 11, 300, 420);
		setUpOptionsPanel();
		solutionsPanel = maker.createPanel(10, 11, 454, 420);
		solutionsPanel.setBackground(Color.black);
		solutionsPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.black, null, null, null));
	}
	
	private void addPanelsToFrame() {
		frame.getContentPane().add(optionsPanel);
        frame.getContentPane().add(solutionsPanel);
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
		optionsPanel.add(txtRoute = maker.createTextField(10, 35, 200, 25, "src/fileReader/exampleMatrix.json"));
		optionsPanel.add(txtWithoutPruning = maker.createTextField(10, 356, 110, 25, null));
		optionsPanel.add(txtWithPruning = maker.createTextField(194, 356, 110, 25, null));

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
		btnLoad.addActionListener(e-> { loadMatrix(); });
		
		btnGenerate.addActionListener(e -> { generateSolutions(); });
		
		btnRandomMatrix.addActionListener(e->{ loadRandomMatrix(); });
		
		comboBox.addItemListener(e -> {
			resetColors();
			int index = comboBox.getSelectedIndex();
			List<Solution> solutions = bfController.getSolutions();
			showSolutionPath(solutions, index);
		});
	}

	private void loadMatrix() {
		try {
			solutionsPanel.removeAll();
			String route = txtRoute.getText();
			rController.readFile(route);
			bfController = new BruteForceController(rController.getMatrix());
			int[] attributes = rController.getMatrixAttributes();
			labels= new JLabel[attributes[0]][attributes[1]];
			setMatrixLayout(solutionsPanel, rController.getMatrix());
			comboBox.setEnabled(false);
			btnGenerate.setEnabled(true);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(optionsPanel, "Insertar valores validos por favor", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	private void generateSolutions() {
		resetColors();
		tc= new TimerController();
		solutionHandler = new SolutionEventHandler(bfController, progressBar, comboBox, labels, 
				tc, txtWithPruning, txtWithoutPruning);
		solutionHandler.execute();
	}
	
	private void loadRandomMatrix() {
		//FALTAR CARGAR
	}

	private void setMatrixLayout(JPanel panel, int[][] readMatrix) {
		panel.setLayout(new GridLayout(readMatrix.length, readMatrix[0].length, 3, 3));
		for (int i = 0; i< readMatrix.length; i++) {
			for (int j = 0; j< readMatrix[0].length; j++) {
				JLabel text = new JLabel("" + readMatrix[i][j]);
				labels[i][j] = text;
				text.setBackground(Color.WHITE);
				text.setForeground(Color.BLACK);
				text.setFont(new Font("Arial", 0, 25));
				text.setHorizontalAlignment(JTextField.CENTER);
				labels[i][j].setOpaque(true);
				panel.add(labels[i][j]);
				panel.revalidate();
				panel.repaint();
			}
		}
	}
	
	private void showSolutionPath(List<Solution> solutionList, int solutionIndex) {
		for (int[] c : solutionList.get(solutionIndex).get_journey()) {
		    int row = c[0];
		    int col = c[1];
		    if (row < labels.length && col < labels[0].length) {
		        labels[row][col].setOpaque(true);
		        labels[row][col].setBackground(Color.GREEN);
		    } else {
		        System.out.println("Índice fuera de rango: (" + row + "," + col + ")");
		    }
		}
	}
	
	private void resetColors() {
		for (int i = 0; i< labels.length; i++) {
			for (int j = 0; j< labels[0].length; j++) {
				labels[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
}
