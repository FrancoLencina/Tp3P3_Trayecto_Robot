package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DataTable extends JTable{
	private static final long serialVersionUID = 1L;

	public DataTable() {
		setLayout(new BorderLayout());
		
		String[] colum = {"Descripción","Valor"};
		Object[][] datos= {
				{"Tamaño de matriz",""},
				{"Tiempo sin poda", ""},
				{"Tiempo con poda",""},
				{"Caminos recorridos",""}
		};
	
		JTable table= new JTable(datos,colum);
		table.setEnabled(false);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setShowGrid(false);
		table.setTableHeader(null);
		
		JScrollPane panelData = new JScrollPane(table);
		panelData.setBorder(BorderFactory.createEmptyBorder());
        add(panelData, BorderLayout.CENTER);
	}
	
}
