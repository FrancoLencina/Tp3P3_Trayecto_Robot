package view;

import javax.swing.JFrame;

import view.helper.FileChooser;
import view.helper.FileVerification;
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
			new View().setVisible(true);
			dispose();
		});
	}

	private void verifyChooser() {
		JOptionPane.showMessageDialog(this,
				"Por favor, seleccione entre 1 y 5 archivos para la creación del gráfico.\n"
						+ "Use la tecla Shift y el mouse para seleccionar varios archivos.",
				"Selección de archivos", JOptionPane.INFORMATION_MESSAGE);
		List<String> routes = null;
		do {
			chooser.fileSelector(this);
			routes = chooser.getRoutes();

			if (routes == null || routes.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No se seleccionaron archivos.", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (routes.size() > 5) {
				JOptionPane.showMessageDialog(this,
						"Has seleccionado más de 5 archivos. Por favor, selecciona entre 1 y 5.", "Cantidad excedida",
						JOptionPane.WARNING_MESSAGE);
			}

		} while (routes.size() > 5);

		Map<String, Double> resultsWithout = new HashMap<>();
		Map<String, Double> resultsWith = new HashMap<>();

		for (String route : routes) {
			boolean ready = rController.readFile(route);
			if (!ready) {
				JOptionPane.showMessageDialog(this, "No se pudo leer el archivo: " + route, "Error",
						JOptionPane.ERROR_MESSAGE);
				continue;
			}
			int[][] matrix = rController.getMatrix();
			if (matrix == null) {
				JOptionPane.showMessageDialog(this, "Error al obtener la matriz desde: " + route, "Error",
						JOptionPane.ERROR_MESSAGE);
				continue;
			}
			timerC = new TimerController(matrix);
			// REVISAR ESTO
			timerC.start();
			try {
				timerC.join(); // Espera a que termine el hilo
			} catch (InterruptedException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al esperar la ejecución del hilo.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			double timeWithout = timerC.getBruteForceTime();
			double timeWith = timerC.getPruningTime();
			String size = matrix[0].length + "x" + matrix.length;
			resultsWithout.put(size, timeWithout);
			resultsWith.put(size, timeWith);
			System.out.println("Tamano" + size);
			System.out.println("segudno sin " + timeWithout);
			System.out.println("segudno con " + timeWith);
		}
		BarGraph grafico = new BarGraph(resultsWithout, resultsWith);
		grafico.setVisible(true);
		dispose();
	}

}
