package model;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private List<int[]> _journey;
	private int _charge;	
	
	public Solution() {
		_journey = new ArrayList<>();
		setCharge(0);
	}
	
	public void addStep(int x, int y, int charge) {
		_journey.add(new int[] {x,y});
		setCharge(getCharge() + charge);
	}
	
	public void removeLastStep(int x, int y, int charge) {
	    if (!_journey.isEmpty()) {
	    	_journey.remove(_journey.size() - 1);
            setCharge(getCharge() - charge);
        }
	}

	public int getCharge() {
		return _charge;
	}

	public void setCharge(int _charge) {
		this._charge = _charge;
	}
	
	public Solution clone() {
		
        Solution clone = new Solution();
        clone._journey = new ArrayList<>(_journey);
        clone.setCharge(_charge);
        return clone;
    }
}
