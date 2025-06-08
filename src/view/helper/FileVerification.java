package view.helper;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import controllers.ReaderController;

public class FileVerification {

	public static int[][] loadMatrixFromFile(FileChooser chooser, ReaderController rController) {
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
	
	public static List<int[][]> loadMatrixesFromFile(FileChooser chooser, ReaderController rController) {
		List<int[][]> ret = new ArrayList<int[][]>();
		List<String> routes = chooser.getRoutes();
		if (routes == null || routes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un archivo válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		for (String route : routes) {
			boolean ready = rController.readFile(route);
			if (!ready) {
				JOptionPane.showMessageDialog(null, "No se pudo leer el archivo: " + route, "Error",
						JOptionPane.ERROR_MESSAGE);
				continue;
			}
			int[][] matrix = rController.getMatrix();
			if (matrix == null) {
				JOptionPane.showMessageDialog(null, "Error al obtener la matriz desde: " + route, "Error",
						JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			ret.add(matrix);
			
			
		}
		
		return ret;
	}
}
