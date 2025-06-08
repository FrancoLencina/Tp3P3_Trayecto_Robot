package view;

import javax.swing.JFrame;

import view.helper.FileChooser;
import view.helper.FileVerification;
import view.helper.GraphicBuilder;
import view.helper.PropMaker;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import controllers.ReaderController;
import controllers.TimerController;

public class ViewMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private PropMaker maker = new PropMaker();
	private FileChooser chooser = new FileChooser();
	private JButton btnRobotMode;
	private JButton btnGraphicMode;
	private TimerController timerC;
	private ReaderController rController = new ReaderController();
	private GraphicBuilder gb;

	public ViewMenu() {
		setUpFrame();
		addLabels();
		addButtons();
		openRobotMode();
		btnGraphicMode.addActionListener(e -> {
			verifyChooser();

		});
	}

	private void setUpFrame() {
		setTitle("Titulo");
		setBounds(300, 200, 400, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}

	private void addLabels() {
		JLabel lblWelcome = maker.createLabel(20, 30, 360, 40, "Optimización de la ruta del robot");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(lblWelcome);

		JLabel lblChoose = maker.createLabel(100, 80, 200, 40, "¿Qué querés hacer?");
		lblChoose.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoose.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblChoose);
	}

	private void addButtons() {
		btnRobotMode = maker.createButton(125, 130, 150, 25, "Modo Robot");
		btnRobotMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(btnRobotMode);

		btnGraphicMode = maker.createButton(125, 175, 150, 25, "Modo Gráfico");
		btnGraphicMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGraphicMode.setBounds(125, 175, 150, 25);
		getContentPane().add(btnGraphicMode);
	}

	private void openRobotMode() {
		btnRobotMode.addActionListener(e -> {
			new ViewJourney().setVisible(true);
			dispose();
		});
	}

	private void verifyChooser() {
		JOptionPane.showMessageDialog(this,
				"Por favor, seleccione entre 1 y 5 archivos para la creación del gráfico.\n"
						+ "Use la tecla Shift y el mouse para seleccionar varios archivos.",
				"Selección de archivos", JOptionPane.INFORMATION_MESSAGE);
		chooser.fileSelector(this);
		List<int[][]> matrixes = FileVerification.loadMatrixesFromFile(chooser,rController);
		if (matrixes == null)
			return;

		if (matrixes.size() > 5 ) {
			JOptionPane.showMessageDialog(this,
					"Has seleccionado más de 5 archivos. Por favor, selecciona entre 1 y 5.", "Cantidad excedida",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Map<String, Double> with = new HashMap();
		Map<String, Double> without = new HashMap();;
		for(int[][] matrix : matrixes) {
			gb = new GraphicBuilder(matrix);
			Map<String,Double> resWith=gb.getResultsWithPruning(matrix);
			Map<String,Double> resWithout=gb.getResultsWithoutPruning(matrix);
			for(Map.Entry<String, Double> entry : resWith.entrySet()) {
				with.put(entry.getKey(),entry.getValue());
			}
			for(Map.Entry<String, Double> entry : resWithout.entrySet()) {
				without.put(entry.getKey(),entry.getValue());
			}
		}
		
		ViewChart graphic = new ViewChart(with, without);
		graphic.setVisible(true);
		dispose();
	}
}
