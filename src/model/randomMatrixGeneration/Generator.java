package model.randomMatrixGeneration;

public interface Generator {
	boolean nextBoolean();
	int nextInt(int origin, int bound);
}
