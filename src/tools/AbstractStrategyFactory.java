package tools;

import model.Coord;
import model.SIsMoveOk;

public interface AbstractStrategyFactory {
	SIsMoveOk getStrategieDeplacement(Coord coord, SIsMoveOk initialStrategy);
}
