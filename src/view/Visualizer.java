package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Solution;

public class Visualizer {
	
	private JLabel[][] matrixVisuals;
	private JTextField time1Text; // Tiempo sin poda
	private JTextField time2Text; // Tiempo con poda
	
	public Visualizer() {
//		this.time1Text = textField1;
//		this.time2Text = textField2;
	}

	public void drawMatrix(JPanel panel, int[][] matrix) {
		panel.setLayout(new GridLayout(matrix.length, matrix[0].length, 3, 3));
		for (int i = 0; i< matrix.length; i++) {
			for (int j = 0; j< matrix[0].length; j++) {
				JLabel text = new JLabel("" + matrix[i][j]);
				matrixVisuals[i][j] = text;
				text.setBackground(Color.WHITE);
				text.setForeground(Color.BLACK);
				text.setFont(new Font("Arial", 0, 25));
				text.setHorizontalAlignment(JTextField.CENTER);
				matrixVisuals[i][j].setOpaque(true);
				panel.add(matrixVisuals[i][j]);
			}
		}
	}
	
	void showSolutionPath(Solution solution) {
		for (int[] c : solution.get_journey()) {
		    int row = c[1];
		    int col = c[0];
		    if (row < matrixVisuals.length && col < matrixVisuals[0].length) {
		    	matrixVisuals[row][col].setOpaque(true);
		    	matrixVisuals[row][col].setBackground(Color.GREEN);
		    } else {
		        System.out.println("Índice fuera de rango: (" + row + "," + col + ")");
		    }
		}
	}
	
	public void resetMatrixColors() {
		for (int i = 0; i< matrixVisuals.length; i++) {
			for (int j = 0; j< matrixVisuals[0].length; j++) {
				matrixVisuals[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
	void showTime(double time1, double time2) {
		String time1String = Double.toString(time1);
		String time2String = Double.toString(time2);
		//ACA se SETEA 
		time1Text.setText(time1String);
		time2Text.setText(time2String);
		time1Text.setVisible(true);
		time2Text.setVisible(true);
	}
	
	public void hideTime() {
		time1Text.setVisible(false);
		time2Text.setVisible(false);
	}

	public void setMatrixLabel(JLabel[][] labels) {
		matrixVisuals = labels;
	}
}
