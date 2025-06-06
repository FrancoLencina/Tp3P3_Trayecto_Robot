package controllers;

import java.awt.GridLayout;
import javax.swing.*;
import view.*;

public class MatrixController {

	private JPanel solutionsPanel;
    private JTextField txtRoute;
    private JTextField txtWithoutPruning;
    private JTextField txtWithPruning;
    private JButton btnGenerate;
    private JComboBox<String> comboBox;
    private JProgressBar progressBar;
    private ReaderController rController;
    private RandomController randController;
    private BruteForceController bfController;
    private Visualizer drawer;
    private TimerController tController;
    private SolutionEventHandler solutionHandler;
    private JLabel[][] labels;

    public MatrixController(JPanel solutionsPanel, JTextField txtRoute,
                            JTextField txtWithoutPruning, JTextField txtWithPruning,
                            JButton btnGenerate, JComboBox<String> comboBox,
                            JProgressBar progressBar,
                            ReaderController rController, RandomController randController) {
    	this.solutionsPanel = solutionsPanel;
        this.txtRoute = txtRoute;
        this.txtWithoutPruning = txtWithoutPruning;
        this.txtWithPruning = txtWithPruning;
        this.btnGenerate = btnGenerate;
        this.comboBox = comboBox;
        this.progressBar = progressBar;
        this.rController = rController;
        this.randController = randController;
    }

    public void loadMatrix() {
        try {
        	solutionsPanel.removeAll();
            if (drawer != null) drawer.hideTime();

            String route = txtRoute.getText();
            if (route == null || route.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            rController.readFile(route);
            int[][] matrix = rController.getMatrix();

            if (matrix == null) {
                JOptionPane.showMessageDialog(null, "No se pudo cargar la matriz del archivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            bfController = new BruteForceController(matrix);
            int[] attributes = rController.getMatrixAttributes();
            labels = new JLabel[attributes[0]][attributes[1]];
            solutionsPanel.setLayout(new GridLayout(attributes[0], attributes[1], 3, 3));

            drawer = new Visualizer(labels, txtWithoutPruning, txtWithPruning);
            drawer.drawMatrix(solutionsPanel, matrix);

            solutionsPanel.revalidate();
            solutionsPanel.repaint();
            comboBox.setEnabled(false);
            btnGenerate.setEnabled(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(solutionsPanel, "Insertar valores válidos por favor", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadRandomMatrix() {
        try {
            solutionsPanel.removeAll();
            if (drawer != null) drawer.hideTime();

            int[][] matrix = randController.getMatrix();
            bfController = new BruteForceController(matrix);

            labels = new JLabel[matrix.length][matrix[0].length];
            solutionsPanel.setLayout(new GridLayout(matrix.length, matrix[0].length, 3, 3));

            drawer = new Visualizer(labels, txtWithoutPruning, txtWithPruning);
            drawer.drawMatrix(solutionsPanel, matrix);

            solutionsPanel.revalidate();
            solutionsPanel.repaint();
            comboBox.setEnabled(false);
            btnGenerate.setEnabled(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(solutionsPanel, "Insertar valores válidos por favor", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generateSolutions() {
        if (drawer != null) drawer.resetMatrixColors();

        tController = new TimerController();

        solutionHandler = new SolutionEventHandler(bfController, progressBar, comboBox, drawer, tController);
        solutionHandler.execute();
    }

    public BruteForceController getBruteForceController() {
        return bfController;
    }

    public Visualizer getDrawer() {
        return drawer;
    }
}