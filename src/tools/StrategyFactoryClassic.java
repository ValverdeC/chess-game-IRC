package tools;

import model.Coord;
import model.SIsMoveOk;

public class StrategyFactoryClassic implements AbstractStrategyFactory {

	@Override
	public SIsMoveOk getStrategieDeplacement(Coord coord, SIsMoveOk initialStrategy) {
		return initialStrategy;
	}

}
