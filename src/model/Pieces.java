package model;

import java.lang.reflect.InvocationTargetException;

public class Pieces implements IPieces {
	private Coord coordonnees;
	private Couleur couleur;
	private String name;
	private SIsMoveOk initialStrategie;
	private SIsMoveOk strategieDeplacement;
	
	public Pieces(Coord coordonnees, Couleur couleur, String name) {
		super();
		this.coordonnees = coordonnees;
		this.couleur = couleur;
		this.name = name;
		try {
			try {
				this.initialStrategie = (SIsMoveOk) Class.forName("model.S" + this.name).getMethod("getInstance").invoke(null);
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		} catch (IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
		this.strategieDeplacement = this.getStrategieDeplacement(coordonnees);
	}

	@Override
	public int getX() {
		return coordonnees.x;
	}

	@Override
	public int getY() {
		return coordonnees.y;
	}

	@Override
	public Couleur getCouleur() {
		return couleur;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override	
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
		this.strategieDeplacement = this.getStrategieDeplacement(this.coordonnees);
		return this.strategieDeplacement.isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible, this.coordonnees, this.couleur);
	}

	@Override
	public boolean move(int xFinal, int yFinal) {
		this.coordonnees.x = xFinal;
		this.coordonnees.y = yFinal;
		return true;
	}

	@Override
	public boolean capture() {
		this.coordonnees.x = -1;
		this.coordonnees.y = -1;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractPiece [coordonnees=" + coordonnees + ", name=" + name + "]";
	}
	
	private SIsMoveOk getStrategieDeplacement(Coord coord) {
		switch(coord.getX()) {
			case 0:
			case 7:
				return STour.getInstance();
				
			case 1:
			case 6:
				return SCavalier.getInstance();
				
			case 2:
			case 5:
				return SFou.getInstance();
				
			default:
				return this.initialStrategie;
		}
	}
	
}
