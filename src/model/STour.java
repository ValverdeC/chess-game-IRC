package model;

public class STour implements SIsMoveOk {

	private volatile static STour single;
	
	public STour() {

	}
	
	public static STour getInstance() {
		if(single == null) {
	    	synchronized(STour.class) {
				if(single == null)
					single = new STour();
	      	}
    	}      
    	return single;
	}

	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible, Coord initialsCoord, Couleur color) {
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			if(xFinal == initialsCoord.getX()  || yFinal == initialsCoord.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
