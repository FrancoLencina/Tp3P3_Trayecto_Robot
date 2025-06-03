package model;

public class PresetGenerator implements Generator {

	private boolean _charge;
	private int _n;
	private int _m;
	private boolean _retN;

	public PresetGenerator(int n, int m, boolean retN) {
		_n = n;
		_m = m;
		_retN = retN;
	}
	
	public PresetGenerator(boolean charge) {
		_charge = charge;
	}
	
	@Override
    public boolean nextBoolean() {
		return _charge;
	}

	@Override
	public int nextInt(int n, int m) {
		return _retN ? _n : _m;
	}
}
