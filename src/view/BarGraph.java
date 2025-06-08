package view;

import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JFrame;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class BarGraph extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFreeChart graph;
	private DefaultCategoryDataset data;

	public BarGraph(Map<String, Double> resultsWithOut, Map<String, Double> resultsWith) {
		setTitle("Gráfico de tiempos");
		setBounds(100, 100, 800, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		data = new DefaultCategoryDataset();

		for (Map.Entry<String, Double> entry : resultsWithOut.entrySet()) {
			String matrixSize = entry.getKey();
			Double time = entry.getValue();
			data.addValue(time, "Tiempo sin poda", matrixSize);
		}

		for (Map.Entry<String, Double> entry : resultsWith.entrySet()) {
			String matrixSize = entry.getKey();
			Double time = entry.getValue();
			data.addValue(time, "Tiempo con poda", matrixSize);
		}

		graph = ChartFactory.createBarChart("Tiempos de resolución por tamaño de matriz", "Tamaño de la matriz",
				"Tiempo (segundos)", data, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(graph);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(chartPanel, BorderLayout.CENTER);
	}
}
