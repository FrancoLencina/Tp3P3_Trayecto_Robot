package view.helper;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import model.Solution;

public class SolutionVisualizer {

	private JLabel[][] matrixVisuals;
	private DataTable dataDisplay;
	private JProgressBar solutionProgressVisualizer;

	public SolutionVisualizer(JProgressBar progressBar, DataTable table) {
		this.solutionProgressVisualizer = progressBar;
		this.dataDisplay = table;
	}

	public void drawMatrix(JPanel panel, int[][] matrix) {
		panel.removeAll();
		panel.setLayout(new GridLayout(matrix.length, matrix[0].length, 3, 3));
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
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

	public void showSolutionPath(Solution solution) {
		for (int[] c : solution.get_journey()) {
			int row = c[1];
			int col = c[0];
			if (row < matrixVisuals.length && col < matrixVisuals[0].length) {
				matrixVisuals[row][col].setOpaque(true);
				matrixVisuals[row][col].setBackground(Color.GREEN);
			}
		}
	}

	public void resetMatrixColors() {
		for (int i = 0; i < matrixVisuals.length; i++) {
			for (int j = 0; j < matrixVisuals[0].length; j++) {
				matrixVisuals[i][j].setBackground(Color.WHITE);
			}
		}
	}

	public void displayDataTable(Map<Integer, String> data) {
		int position = 0;
		for (String s : data.values()) {
			dataDisplay.updateValue(position, s);
			position++;
		}
		dataDisplay.setVisible(true);
	}

	public void setMatrixLabel(JLabel[][] labels) {
		matrixVisuals = labels;
	}

	public void setProgressBarIndeterminate() {
		solutionProgressVisualizer.setIndeterminate(true);
	}

	public void stopProgressBar() {
		solutionProgressVisualizer.setIndeterminate(false);
	}
}
