package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;

import controllers.BruteForceController;
import model.Solution;
import model.Solver;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class View {

	private JFrame frame;
	private JTextField textRoute;
	private BruteForceController bfController = new BruteForceController();
	JLabel[][] labels;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(475, 11, 219, 419);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 454, 419);
		frame.getContentPane().add(panel_1);
		
		textRoute = new JTextField();
		textRoute.setText("src/fileReader/exampleMatrix.json");
		textRoute.setBounds(10, 51, 125, 28);
		panel.add(textRoute);
		textRoute.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingresa ruta del archivo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 24, 183, 29);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String route = textRoute.getText();
					bfController.readFile(route);
					int[] attributes = bfController.getMatrixAttributes();
					labels= new JLabel[attributes[0]][attributes[1]];
					setMatrixLayout(panel_1, bfController.getMatrix());
					
					
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(panel, "Insertar valores validos por favor", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(144, 54, 65, 23);
		panel.add(btnNewButton);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(e -> {
			resetColors();
			int index = comboBox.getSelectedIndex();
			List<Solution> solutions = bfController.getSolutions();
			for (int[] c : solutions.get(index).get_journey()) {
			    int row = c[1];
			    int col = c[0];
			    if (row < labels.length && col < labels[0].length) {
			        labels[row][col].setOpaque(true);
			        labels[row][col].setBackground(Color.GREEN);
			    } else {
			        System.out.println("Índice fuera de rango: (" + row + "," + col + ")");
			    }
			}
		});
		comboBox.setBounds(10, 265, 199, 22);
		comboBox.setEnabled(false);
		System.out.println(comboBox.isEnabled());
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Soluciones:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 240, 106, 14);
		lblNewLabel_1.setVisible(false);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Generar Soluciones");
		btnNewButton_1.addActionListener(e -> {
			bfController.solve();
			String[] solutions = new String[bfController.getAmountOfSolutions()];
			for (int i = 0; i<solutions.length;i++) {
				solutions[i] = "Solución " + (i+1);
				
			}
			comboBox.setModel(new DefaultComboBoxModel(solutions));
			comboBox.setEnabled(true);
			System.out.println(comboBox.isEnabled());
		});
		btnNewButton_1.setBounds(50, 119, 125, 56);
		panel.add(btnNewButton_1);
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
	
	private void resetColors() {
		for (int i = 0; i< labels.length; i++) {
			for (int j = 0; j< labels[0].length; j++) {
				labels[i][j].setBackground(Color.WHITE);
			}
		}
	}
}
