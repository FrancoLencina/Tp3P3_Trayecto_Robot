package controllers;

import fileReader.JsonReader;

public class ReaderController {

	private JsonReader jr = new JsonReader();

	public boolean readFile(String route) {
		return jr.readFile(route);
	}

	public int[] getMatrixAttributes() {
		return jr.getMatrixAttributes();
	}

	public int[][] getMatrix() {
		return jr.getMatrix();
	}
}
