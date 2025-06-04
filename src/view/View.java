package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import controllers.*;
import model.Solution;

public class View {

	private JFrame frame;
	private JTextField textRoute;
	
	private JButton btnLoad;
	private JButton btnGenerate;
    private JComboBox<String> comboBox;
    private JProgressBar progressBar;
    private JPanel solutionsPanel;
    private JPanel optionsPanel;
    
	private ReaderController rController = new ReaderController();
	private BruteForceController bfController;
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
		
		optionsPanel= createOptionsPanel();
		solutionsPanel = createSolutionsPanel();
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 180, 280, 30);
		optionsPanel.add(progressBar);
		
		frame.getContentPane().add(optionsPanel);
        frame.getContentPane().add(solutionsPanel);
        
        Listeners();
		
	}

	private JPanel createSolutionsPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		panel.setBounds(10, 11, 454, 419);
		
		return panel;
	}

	private void setUpFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	private JPanel createOptionsPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(475, 11, 219, 419);
		panel.setLayout(null);
		
		JLabel lblroute = new JLabel("Ingresa ruta del archivo:");
		lblroute.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblroute.setBounds(10, 24, 183, 29);
		panel.add(lblroute);
		
		textRoute = new JTextField();
		textRoute.setText("src/fileReader/exampleMatrix.json");
		textRoute.setBounds(10, 51, 125, 28);
		panel.add(textRoute);
		textRoute.setColumns(10);
		
		btnLoad = new JButton("Cargar");		
		btnLoad.setBounds(144, 54, 65, 23);
		panel.add(btnLoad);
		
		btnGenerate = new JButton("Generar Soluciones");
		btnGenerate.setEnabled(false);
		btnGenerate.setBounds(50, 119, 125, 56);
		panel.add(btnGenerate);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(10, 265, 199, 22);
		comboBox.setEnabled(false);
		//System.out.println(comboBox.isEnabled());
		panel.add(comboBox);
		
		return panel;
	}
	
	private void Listeners() {
		btnLoad.addActionListener(e-> { loadMatrix(); });
		
		btnGenerate.addActionListener(e -> { generateSolutions(); });
		
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
			String route = textRoute.getText();
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
		solutionHandler = new SolutionEventHandler(bfController, progressBar, comboBox, labels);
		solutionHandler.execute();
	
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
