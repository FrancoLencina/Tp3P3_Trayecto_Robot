package model;
import java.util.Random;

public class RandomGenerator implements Generator {

	private Random _random;
	
	public RandomGenerator() {
		_random = new Random();
	}
	
	@Override
	public boolean nextBoolean() {
		return _random.nextBoolean();
	}

	@Override
	public int nextInt(int origin, int bound ) {
		return _random.nextInt(origin, bound);
	}
}
