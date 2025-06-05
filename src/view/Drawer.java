package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Solution;

public class Drawer {

	static void setMatrixLayout(JPanel panel, int[][] readMatrix, JLabel[][] labels) {
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
	
	static void showSolutionPath(List<Solution> solutionList, int solutionIndex,JLabel[][] labels) {
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
	
	static void resetColors(JLabel[][] labels) {
		for (int i = 0; i< labels.length; i++) {
			for (int j = 0; j< labels[0].length; j++) {
				labels[i][j].setBackground(Color.WHITE);
			}
		}
	}
}
