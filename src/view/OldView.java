package view;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import controllers.*;
import model.Solution;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JProgressBar;

public class OldView {

	private JFrame frame;
	private JTextField textRoute;
	private ReaderController rController = new ReaderController();
	private BruteForceController bfController;
	JLabel[][] labels;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OldView window = new OldView();
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
	public OldView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(475, 11, 300, 420);
		frame.getContentPane().add(optionsPanel);
		optionsPanel.setLayout(null);
		
		JPanel solutionsPanel = new JPanel();
		solutionsPanel.setBackground(new Color(0, 0, 0));
		solutionsPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		solutionsPanel.setBounds(10, 11, 454, 420);
		frame.getContentPane().add(solutionsPanel);
		
		textRoute = new JTextField();
		textRoute.setText("src/fileReader/exampleMatrix.json");
		textRoute.setBounds(10, 35, 200, 25);
		optionsPanel.add(textRoute);
		textRoute.setColumns(10);
		
		JLabel lblroute = new JLabel("Ingresa ruta del archivo:");
		lblroute.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblroute.setBounds(10, 10, 183, 29);
		optionsPanel.add(lblroute);
		
		JLabel lblsolutions = new JLabel("Soluciones:");
		lblsolutions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblsolutions.setBounds(10, 240, 106, 20);
		lblsolutions.setVisible(false);
		optionsPanel.add(lblsolutions);

		JButton btnload = new JButton("Cargar");		
		btnload.setBounds(225, 35, 65, 25);
		optionsPanel.add(btnload);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(e -> {
			resetColors();
			int index = comboBox.getSelectedIndex();
			List<Solution> solutions = bfController.getSolutions();
			showSolutionPath(solutions, index);
		});
		comboBox.setBounds(10, 265, 199, 25);
		comboBox.setEnabled(false);
		System.out.println(comboBox.isEnabled());
		optionsPanel.add(comboBox);
		
		JButton btngenerate = new JButton("Generar Soluciones");
		btngenerate.setEnabled(false);
		btngenerate.setBounds(70, 130, 160, 30);
		optionsPanel.add(btngenerate);
		
		btngenerate.addActionListener(e -> {
			bfController.solve();
			String[] solutions = new String[bfController.getAmountOfSolutions()];
			for (int i = 0; i<solutions.length;i++) {
				solutions[i] = "Solución " + (i+1);
				
			}
			comboBox.setModel(new DefaultComboBoxModel(solutions));
			comboBox.setEnabled(true);
			
			JProgressBar progressBar = new JProgressBar();
			progressBar.setBounds(10, 180, 280, 30);
			optionsPanel.add(progressBar);
			
			textField = new JTextField();
			textField.setBounds(10, 356, 100, 25);
			optionsPanel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(194, 356, 100, 25);
			optionsPanel.add(textField_1);
			
			JLabel lblNewLabel = new JLabel("Sin poda");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel.setBounds(10, 333, 100, 20);
			optionsPanel.add(lblNewLabel);
			
			JLabel lblConPoda = new JLabel("Con poda");
			lblConPoda.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblConPoda.setBounds(194, 333, 100, 20);
			optionsPanel.add(lblConPoda);
			
			JLabel lblNewLabel_1 = new JLabel("Tiempo total:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel_1.setBounds(10, 307, 100, 20);
			optionsPanel.add(lblNewLabel_1);
			
			JButton btnNewButton = new JButton("Cargar Matriz Aleatoria");
			btnNewButton.setBounds(75, 70, 140, 25);
			optionsPanel.add(btnNewButton);
			System.out.println(comboBox.isEnabled());
			
			resetColors();
			showSolutionPath(bfController.getSolutions(), 0);
		});

		btnload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					solutionsPanel.removeAll();
					String route = textRoute.getText();
					rController.readFile(route);
					bfController = new BruteForceController(rController.getMatrix());
					int[] attributes = rController.getMatrixAttributes();
					labels= new JLabel[attributes[0]][attributes[1]];
					setMatrixLayout(solutionsPanel, rController.getMatrix());
					btngenerate.setEnabled(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(optionsPanel, "Insertar valores validos por favor", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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
		    int row = c[1];
		    int col = c[0];
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