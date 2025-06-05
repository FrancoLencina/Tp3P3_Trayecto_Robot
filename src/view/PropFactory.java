package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class PropFactory {
	
	public JPanel createPanel(int x, int y, int width, int height) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, width, height);
		panel.setLayout(null);
		return panel;
	}
	
	public JLabel createLabel(int x, int y, int width, int height, String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(x, y, width, height);
		return label;
	}
	
	public JTextField createTextField(int x, int y, int width, int height, String text) {
		JTextField textField = new JTextField(text);
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(x, y, width, height);
		textField.setColumns(10);
		return textField;
	}
	
	public JButton createButton(int x, int y, int width, int height, String text) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		return button;
	}
	
	public JProgressBar createProgressBar(int x, int y, int width, int height) {
		JProgressBar bar = new JProgressBar();
		bar.setBounds(x, y, width, height);
		return bar;
	}
	
	public JComboBox<String> createStringComboBox(int x, int y, int width, int height) {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(x, y, width, height);
		return comboBox;
	}
	
	public void colorPanelAndBorder(Color color, JPanel panel) {
		panel.setBackground(color);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, color, null, null, null));
		
	}
	
}
