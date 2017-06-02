package model;

public interface Pieces {
	
	int getX();
	int getY();
	Couleur getCouleur();
	String getName();
	boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible);
	boolean move(int xFinal, int yFinal);
	boolean capture();
	
}
