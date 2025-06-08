package view.helper;

import javax.swing.*;

import controllers.ReaderController;

public class FileVerification {

	public static int[][] loadMatrixFromFile(JFrame frame, FileChooser chooser, ReaderController rController) {
		String route = chooser.getRoute();
		if (route == null || route.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un archivo válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		boolean ready = rController.readFile(route);
		if (!ready) {
			JOptionPane.showMessageDialog(null, "No se pudo leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		int[][] matrix = rController.getMatrix();
		if (matrix == null) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar la matriz del archivo.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return matrix;
	}
}
