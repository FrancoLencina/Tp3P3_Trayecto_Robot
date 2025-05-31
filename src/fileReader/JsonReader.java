package fileReader;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;


public class JsonReader {
	private int[][] matrix;
	
	public JsonReader() {
		this.matrix = null;
	}
	
	public void readFile(String route) {
		Gson gson = new Gson();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(route));
			matrix = gson.fromJson(br, int[][].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int[][] getMatrix(){
		return this.matrix;
	}
	
	public int[] getMatrixAttributes(){
		int[] attributes = new int[2];
		attributes[0] = matrix.length;
		attributes[1] = matrix[0].length;
		return attributes;
	}
}
