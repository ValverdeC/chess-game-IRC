package model;

public class SReine implements SIsMoveOk {

	private volatile static SReine single;
	
	public SReine() {

	}
	
	public static SReine getInstance() {
		if(single == null) {
	    	synchronized(SReine.class) {
				if(single == null)
					single = new SReine();
	      	}
    	}      
    	return single;
	}

	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible, Coord initialsCoord, Couleur color) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if(xFinal == initialsCoord.getX()  || yFinal == initialsCoord.getY() || Math.abs(xFinal - initialsCoord.getX()) == Math.abs(yFinal - initialsCoord.getY())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
