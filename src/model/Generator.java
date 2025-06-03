package model;

public interface Generator {
	boolean nextBoolean();
	int nextInt(int origin, int bound);
	int nextInt(int n);
}
