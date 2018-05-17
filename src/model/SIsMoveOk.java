package model;

public interface SIsMoveOk {
	
	boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible, Coord initialsCoord, Couleur color);
	
}
