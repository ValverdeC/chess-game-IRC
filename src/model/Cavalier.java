package model;

public class Cavalier extends AbstractPiece {

	public Cavalier(Coord coordonnees, Couleur couleur) {
		super(coordonnees, couleur);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if((Math.abs(xFinal - this.getX()) + Math.abs(yFinal - this.getY())) == 3) {
				if(Math.abs(xFinal - this.getX()) < 3 && Math.abs(yFinal - this.getY()) < 3) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
