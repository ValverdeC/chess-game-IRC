package model;

public class SFou implements SIsMoveOk {
	
	private volatile static SFou single;

	public SFou() {

	}	
	
	public static SFou getInstance() {
		if(single == null) {
	    	synchronized(SFou.class) {
				if(single == null)
					single = new SFou();
	      	}
    	}      
    	return single;
	}

	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible, Coord initialsCoord, Couleur color) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if(Math.abs(xFinal - initialsCoord.getX()) == Math.abs(yFinal - initialsCoord.getY())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
