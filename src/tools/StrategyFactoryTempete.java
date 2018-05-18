package tools;

import model.Coord;
import model.SCavalier;
import model.SFou;
import model.SIsMoveOk;
import model.STour;

public class StrategyFactoryTempete implements AbstractStrategyFactory {

	@Override
	public SIsMoveOk getStrategieDeplacement(Coord coord, SIsMoveOk initialStrategy) {
		switch(coord.getX()) {
			case 0:
			case 7:
				return STour.getInstance();
				
			case 1:
			case 6:
				return SCavalier.getInstance();
				
			case 2:
			case 5:
				return SFou.getInstance();
				
			default:
				return initialStrategy;
		}
	}

}
