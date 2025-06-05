package view;

import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
	
	private JFileChooser fc = new JFileChooser();
	
	public void fileSelector(JTextField txtRoute, JFrame frame) {
		fc.setCurrentDirectory(new File("src/fileReader"));
		FileNameExtensionFilter filter= new FileNameExtensionFilter("Archivos JSON","json");
		fc.setFileFilter(filter);
		
		fc.setDialogTitle("Seleccionar archivo JSON de matriz");
		int returnVal= fc.showOpenDialog(frame);
		if(returnVal==JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			
			if(!file.getName().endsWith(".json")) {
				JOptionPane.showMessageDialog(null, "Solo se permiten archivos .json", "Archivo invalido",JOptionPane.ERROR_MESSAGE);
				return;
			}
			txtRoute.setText(file.getAbsolutePath());
		}
	}
}
