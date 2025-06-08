package fileReader;

import com.google.gson.Gson;
import java.io.*;

public class JsonReader {
	private int[][] matrix;

	public JsonReader() {
		this.matrix = null;
	}

	public boolean readFile(String route) {
		Gson gson = new Gson();
		try {
			BufferedReader br = new BufferedReader(new FileReader(route));
			matrix = gson.fromJson(br, int[][].class);
			return matrix != null;
		} catch (Exception e) {
			e.printStackTrace();
			matrix = null;
			return false;
		}
	}

	public int[][] getMatrix() {
		return this.matrix;
	}

	public int[] getMatrixAttributes() {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return null;
		}
		int[] attributes = new int[2];
		attributes[0] = matrix.length;
		attributes[1] = matrix[0].length;
		return attributes;
	}
}
