package model;

public class SRoi implements SIsMoveOk {

	private volatile static SRoi single;
	
	public SRoi() {

	}
	
	public static SRoi getInstance() {
		if(single == null) {
	    	synchronized(SRoi.class) {
				if(single == null)
					single = new SRoi();
	      	}
    	}      
    	return single;
	}

	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible, Coord initialsCoord, Couleur color) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if(Math.abs(xFinal - initialsCoord.getX()) <= 1 && Math.abs(yFinal - initialsCoord.getY()) <= 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
