package controllers;
import fileReader.JsonReader;

public class ReaderController {
	
	private JsonReader jr = new JsonReader();
	
	public void readFile(String route) {
		jr.readFile(route);
	}
	
	public int[] getMatrixAttributes() {
		return jr.getMatrixAttributes();
	}
	
	public int[][] getMatrix(){
		return jr.getMatrix();
	}
}
