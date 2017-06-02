package model;

public class Reine extends AbstractPiece {

	public Reine(Coord coordonnees, Couleur couleur) {
		super(coordonnees, couleur);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if(xFinal == this.getX()  || yFinal == this.getY() || Math.abs(xFinal - this.getX()) == Math.abs(yFinal - this.getY())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
