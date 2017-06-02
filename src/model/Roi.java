package model;

public class Roi extends AbstractPiece {

	public Roi(Coord coordonnees, Couleur couleur) {
		super(coordonnees, couleur);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if(Math.abs(xFinal - this.getX()) <= 1 && Math.abs(yFinal - this.getY()) <= 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
