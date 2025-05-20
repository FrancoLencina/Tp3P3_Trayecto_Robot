package model;

public class Cell {

	int charge; 
	int row;
	int column;
	
	public Cell(int charge, int row, int column) {
		this.charge = charge;
		this.row = row;
		this.column = column;
	}

	public int getCharge() {
		return charge;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}
