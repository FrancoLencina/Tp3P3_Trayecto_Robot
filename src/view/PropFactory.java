package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class PropFactory {
	
	public static JPanel createPanel(int x, int y, int width, int height) {
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 454, 419);
		return panel;
	}
	
	public static JLabel createLabel(int x, int y, int width, int height, String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(x, y, width, height);
		return label;
	}
	
	public static JTextField createTextField(int x, int y, int width, int height, int columnAmount, String text) {
		JTextField textField = new JTextField(text);
		textField.setBounds(x, y, width, height);
		textField.setColumns(columnAmount);
		return textField;
	}
	
	public static JButton createButton(int x, int y, int width, int height, String text) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		return button;
	}
	
	public static JProgressBar createProgressBar(int x, int y, int width, int height) {
		JProgressBar bar = new JProgressBar();
		bar.setBounds(x, y, width, height);
		return bar;
	}
	
	public static JComboBox<String> createStringComboBox(int x, int y, int width, int height) {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(x, y, width, height);
		return comboBox;
	}
	
	public static void colorPanelAndBorder(Color color, JPanel panel) {
		panel.setBackground(color);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, color, null, null, null));
		
	}
	
}
