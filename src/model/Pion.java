package model;

public class Pion extends AbstractPiece {

	public Pion(Coord coordonnees, Couleur couleur) {
		super(coordonnees, couleur);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if(!isCatchOk) {
				if(this.getCouleur() == Couleur.BLANC) {
					if(this.getY() == 6) {
						if(this.getY() - yFinal <= 2 && this.getX() == xFinal) {
							return true;
						} else {
							return false;
						}
					} else {
						if(this.getY() - yFinal == 1 && this.getX() == xFinal) {
							return true;
						} else {
							return false;
						}
					}
				} else if(this.getCouleur() == Couleur.NOIR) {
					if(this.getY() == 1) {
						if(yFinal - this.getY() <= 2 && this.getX() == xFinal) {
							return true;
						} else {
							return false;
						}
					} else {
						if(yFinal - this.getY() == 1 && this.getX() == xFinal) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else {
				if(this.getCouleur() == Couleur.BLANC) {
					if(this.getY() == 6) {
						if((this.getY() - yFinal <= 2 && this.getX() == xFinal) || (Math.abs(xFinal - this.getX()) == 1 && yFinal == this.getY() - 1)) {
							return true;
						} else {
							return false;
						}
					} else {
						if((this.getY() - yFinal == 1 && this.getX() == xFinal) || (Math.abs(xFinal - this.getX()) == 1 && yFinal == this.getY() - 1)) {
							return true;
						} else {
							return false;
						}
					}
				} else if(this.getCouleur() == Couleur.NOIR) {
					if(this.getY() == 1) {
						if((yFinal - this.getY() <= 2 && this.getX() == xFinal) || (Math.abs(xFinal - this.getX()) == 1 && yFinal == this.getY() + 1)) {
							return true;
						} else {
							return false;
						}
					} else {
						if((yFinal - this.getY() == 1 && this.getX() == xFinal) || (Math.abs(xFinal - this.getX()) == 1 && yFinal == this.getY() + 1)) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

}
