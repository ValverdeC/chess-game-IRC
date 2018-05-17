package model;

public class SPion implements SIsMoveOk {

	private volatile static SPion single;
	
	public SPion() {

	}
	
	public static SPion getInstance() {
		if(single == null) {
	    	synchronized(SPion.class) {
				if(single == null)
					single = new SPion();
	      	}
    	}      
    	return single;
	}
	
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible, Coord initialsCoord, Couleur color) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if(!isCatchOk) {
				if(color == Couleur.BLANC) {
					if(initialsCoord.getY() == 6) {
						if(initialsCoord.getY() - yFinal <= 2 && initialsCoord.getX() == xFinal) {
							return true;
						} else {
							return false;
						}
					} else {
						if(initialsCoord.getY() - yFinal == 1 && initialsCoord.getX() == xFinal) {
							return true;
						} else {
							return false;
						}
					}
				} else if(color == Couleur.NOIR) {
					if(initialsCoord.getY() == 1) {
						if(yFinal - initialsCoord.getY() <= 2 && initialsCoord.getX() == xFinal) {
							return true;
						} else {
							return false;
						}
					} else {
						if(yFinal - initialsCoord.getY() == 1 && initialsCoord.getX() == xFinal) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else {
				if(color == Couleur.BLANC) {
					if(initialsCoord.getY() == 6) {
						if((initialsCoord.getY() - yFinal <= 2 && initialsCoord.getX() == xFinal) || (Math.abs(xFinal - initialsCoord.getX()) == 1 && yFinal == initialsCoord.getY() - 1)) {
							return true;
						} else {
							return false;
						}
					} else {
						if((initialsCoord.getY() - yFinal == 1 && initialsCoord.getX() == xFinal) || (Math.abs(xFinal - initialsCoord.getX()) == 1 && yFinal == initialsCoord.getY() - 1)) {
							return true;
						} else {
							return false;
						}
					}
				} else if(color == Couleur.NOIR) {
					if(initialsCoord.getY() == 1) {
						if((yFinal - initialsCoord.getY() <= 2 && initialsCoord.getX() == xFinal) || (Math.abs(xFinal - initialsCoord.getX()) == 1 && yFinal == initialsCoord.getY() + 1)) {
							return true;
						} else {
							return false;
						}
					} else {
						if((yFinal - initialsCoord.getY() == 1 && initialsCoord.getX() == xFinal) || (Math.abs(xFinal - initialsCoord.getX()) == 1 && yFinal == initialsCoord.getY() + 1)) {
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
