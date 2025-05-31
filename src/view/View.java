package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;

import model.Solution;
import model.Solver;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class View {

	private JFrame frame;
	private JTextField textField;
	int[][] matrix = {{1, -1, -1, -1}, {-1, 1, 1, -1}, {1, 1, 1, -1}};
	//int[][] matrix = {{1, -1, 1, 1}, {-1, -1, -1, -1}, {1, 1, -1, -1}, {-1, 1, 1, -1}, {-1, -1, -1, 1}};
	Solver solver = new Solver(matrix);
	JLabel[][] labels = new JLabel[matrix.length][matrix[0].length];

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
		
		textField = new JTextField();
		textField.setBounds(10, 51, 125, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingresa ruta del archivo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 24, 183, 29);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(144, 54, 65, 23);
		panel.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 265, 199, 22);
		comboBox.setVisible(false);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Soluciones:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 240, 106, 14);
		lblNewLabel_1.setVisible(false);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Generar Soluciones");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				solver.solve();
				List<Solution> solutions = solver.get_solutions();
				for (int[] c : solutions.get(0).get_journey()) {
				    int row = c[1];
				    int col = c[0];
				    if (row < matrix.length && col < matrix[0].length) {
				        labels[row][col].setOpaque(true);
				        labels[row][col].setBackground(Color.GREEN);
				    } else {
				        System.out.println("Índice fuera de rango: (" + row + "," + col + ")");
				    }
					
				}
			}
		});
		btnNewButton_1.setBounds(50, 119, 125, 56);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 454, 419);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(matrix.length, matrix[0].length, 0, 0));
		
		for (int i = 0; i< matrix.length; i++) {
			for (int j = 0; j< matrix[0].length; j++) {
				JLabel text = new JLabel("" + matrix[i][j]);
				labels[i][j] = text;
				text.setBackground(Color.WHITE);
				text.setForeground(Color.BLACK);
				text.setFont(new Font("Arial", 0, 25));
				text.setHorizontalAlignment(JTextField.CENTER);
				panel_1.add(text);
			}
		}
	}
}
