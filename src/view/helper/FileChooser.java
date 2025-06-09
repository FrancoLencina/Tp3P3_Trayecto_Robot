package view.helper;

import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	private JFileChooser fc = new JFileChooser();
	private List<String> routes = new ArrayList<String>();
	private String route;

	public void fileSelector(JTextField txtRoute, JFrame frame) {
		routes.clear();
		route = null;
		txtRoute.setText("");
		fc.setCurrentDirectory(new File("src/fileReader"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
		fc.setFileFilter(filter);
		fc.setDialogTitle("Seleccionar archivo JSON de matriz");

		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (!file.getName().endsWith(".json")) {
				JOptionPane.showMessageDialog(null, "Solo se permiten archivos .json", "Archivo inválido",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			route = file.getAbsolutePath();
			txtRoute.setText(route);
		}
	}

	public void fileSelector(JFrame frame) {
		routes.clear();
		route = null;
		fc.setCurrentDirectory(new File("src/fileReader"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(true);
		fc.setDialogTitle("Seleccionar archivos JSON de matriz");

		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File[] files = fc.getSelectedFiles();
			for (File file : files) {
				if (!file.getName().endsWith(".json")) {
					JOptionPane.showMessageDialog(null, "Todos los archivos deben tener extensión .json",
							"Archivo inválido", JOptionPane.ERROR_MESSAGE);
					return;
				}
				routes.add(file.getAbsolutePath());
			}
		}
	}

	public String getRoute() {
		if (route != null && !route.isEmpty()) {
			return route;
		}
		return null;
	}

	public List<String> getRoutes() {
		if (routes.isEmpty()) {
			return null;
		}
		return new ArrayList<>(routes);
	}
}
