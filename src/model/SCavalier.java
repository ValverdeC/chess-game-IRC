package model;

public class SCavalier implements SIsMoveOk {
	
	private volatile static SCavalier single;


	public SCavalier() {

	}
	
	public static SCavalier getInstance() {
		if(single == null) {
	    	synchronized(SCavalier.class) {
				if(single == null)
					single = new SCavalier();
	      	}
    	}      
    	return single;
	}

	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible, Coord initialsCoord, Couleur color) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if((Math.abs(xFinal - initialsCoord.getX()) + Math.abs(yFinal - initialsCoord.getY())) == 3) {
				if(Math.abs(xFinal - initialsCoord.getX()) < 3 && Math.abs(yFinal - initialsCoord.getY()) < 3) {
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
