package model;

import java.lang.reflect.InvocationTargetException;

import tools.AbstractStrategyFactory;

public class Pieces implements IPieces {
	private Coord coordonnees;
	private Couleur couleur;
	private String name;
	private SIsMoveOk initialStrategie;
	
	private AbstractStrategyFactory factory;
	
	public Pieces(Coord coordonnees, Couleur couleur, String name, AbstractStrategyFactory factory) {
		super();
		this.coordonnees = coordonnees;
		this.couleur = couleur;
		this.name = name;
		this.factory = factory;
		try {
			try {
				this.initialStrategie = (SIsMoveOk) Class.forName("model.S" + this.name).getMethod("getInstance").invoke(null);
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		} catch (IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
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
		return this.factory.getStrategieDeplacement(this.coordonnees, this.initialStrategie).isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible, this.coordonnees, this.couleur);
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
	
}
