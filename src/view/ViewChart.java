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
		setUpFrame();
		buildDataSet(resultsWith, resultsWithOut);
		buildChart();
		ChartPanel chartPanel = new ChartPanel(graph);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(chartPanel, BorderLayout.CENTER);
	}

	private void setUpFrame() {
		setTitle("Gráfico de tiempos");
		setBounds(100, 100, 800, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void buildDataSet(Map<String, Double> resultsWith, Map<String, Double> resultsWithOut) {
		data = new DefaultCategoryDataset();

		Set<String> allKeys = new HashSet<>();
		for (String key : resultsWith.keySet()) {
		    allKeys.add(key);
		}
	    
	    List<String> orderedKeys = allKeys.stream()
	            .sorted(Comparator.comparingInt(k -> {
	                try {
	                	String[] key = k.split("x");
	                    return Integer.parseInt(key[0]);
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
	}
	
	private void buildChart() {
		graph = ChartFactory.createBarChart(
				"Tiempos de resolución por tamaño de matriz", 
				"Tamaño de la matriz",
				"Tiempo (segundos)", 
				data, PlotOrientation.VERTICAL, 
				true, true, false);
	}

}
