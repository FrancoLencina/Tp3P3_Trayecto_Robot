package view;

import java.awt.BorderLayout;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ViewChart extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFreeChart graph;
	private DefaultCategoryDataset data;

	public ViewChart(Map<String, Double> resultsWith, Map<String, Double> resultsWithOut) {
		setTitle("Gráfico de tiempos");
		setBounds(100, 100, 800, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		data = new DefaultCategoryDataset();

		Set<String> allKeys = new HashSet<>();
	    allKeys.addAll(resultsWith.keySet());
	    allKeys.addAll(resultsWithOut.keySet());
	    
	    List<String> orderedKeys = allKeys.stream()
	            .sorted(Comparator.comparingInt(k -> {
	                try {
	                    return Integer.parseInt(k.toLowerCase().split("x")[0]);
	                } catch (Exception e) {
	                    return 0;
	                }
	            }))
	            .collect(Collectors.toList());
		
	    for (String matrixSize : orderedKeys) {
	        Double timeWithout = resultsWithOut.get(matrixSize);
	        Double timeWith = resultsWith.get(matrixSize);

	        if (timeWithout != null)
	            data.addValue(timeWithout, "Tiempo sin poda", matrixSize);
	        if (timeWith != null)
	            data.addValue(timeWith, "Tiempo con poda", matrixSize);
	    }

		graph = ChartFactory.createBarChart("Tiempos de resolución por tamaño de matriz", "Tamaño de la matriz",
				"Tiempo (segundos)", data, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(graph);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(chartPanel, BorderLayout.CENTER);
	}
}
