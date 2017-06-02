package model;

import java.util.List;

public abstract class AbstractPiece implements Pieces {
	private Coord coordonnees;
	private Couleur couleur;
	private String name;
	
	public AbstractPiece(Coord coordonnees, Couleur couleur) {
		super();
		this.coordonnees = coordonnees;
		this.couleur = couleur;
		this.name = getClass().getSimpleName();
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
	public abstract boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible);

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
	
	public static void main(String[] args) {
		Pieces maTour = new Tour(new Coord(0,0), Couleur.BLANC);
		
		System.out.println(maTour);
		System.out.println(maTour.getName());
		if(maTour.move(0, 4)) {
			System.out.println(maTour);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(maTour.move(4, 5)) {
			System.out.println(maTour);
		} else {
			System.out.println("Déplacement impossible");
		}
		maTour.capture();
		System.out.println(maTour);
		if(maTour.move(4, 5)) {
			System.out.println(maTour);
		} else {
			System.out.println("Déplacement impossible");
		}
		
		Pieces monFou = new Fou(new Coord(0,0), Couleur.BLANC);
		
		System.out.println(monFou);
		System.out.println(monFou.getName());
		if(monFou.move(0, 4)) {
			System.out.println(monFou);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monFou.move(5, 5)) {
			System.out.println(monFou);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monFou.move(7, 3)) {
			System.out.println(monFou);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monFou.move(7, 5)) {
			System.out.println(monFou);
		} else {
			System.out.println("Déplacement impossible");
		}
		monFou.capture();
		System.out.println(monFou);
		if(monFou.move(4, 5)) {
			System.out.println(monFou);
		} else {
			System.out.println("Déplacement impossible");
		}
		
		Pieces monCavalier = new Cavalier(new Coord(0,0), Couleur.BLANC);
		
		System.out.println(monCavalier);
		System.out.println(monCavalier.getName());
		if(monCavalier.move(1, 2)) {
			System.out.println(monCavalier);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monCavalier.move(1, 5)) {
			System.out.println(monCavalier);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monCavalier.move(4, 5)) {
			System.out.println(monCavalier);
		} else {
			System.out.println("Déplacement impossible");
		}
		monCavalier.capture();
		System.out.println(monCavalier);
		if(monCavalier.move(3, 1)) {
			System.out.println(monCavalier);
		} else {
			System.out.println("Déplacement impossible");
		}
		
		Pieces monRoi = new Roi(new Coord(0,0), Couleur.BLANC);
		
		System.out.println(monRoi);
		System.out.println(monRoi.getName());
		if(monRoi.move(1, 1)) {
			System.out.println(monRoi);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monRoi.move(4, 4)) {
			System.out.println(monRoi);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monRoi.move(1, 2)) {
			System.out.println(monRoi);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monRoi.move(4, 2)) {
			System.out.println(monRoi);
		} else {
			System.out.println("Déplacement impossible");
		}
		monRoi.capture();
		System.out.println(monRoi);
		if(monRoi.move(3, 1)) {
			System.out.println(monRoi);
		} else {
			System.out.println("Déplacement impossible");
		}
		
		Pieces maReine = new Reine(new Coord(0,0), Couleur.BLANC);
		
		System.out.println(maReine);
		System.out.println(maReine.getName());
		if(maReine.move(1, 1)) {
			System.out.println(maReine);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(maReine.move(3, 5)) {
			System.out.println(maReine);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(maReine.move(1, 7)) {
			System.out.println(maReine);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(maReine.move(7, 1)) {
			System.out.println(maReine);
		} else {
			System.out.println("Déplacement impossible");
		}
		maReine.capture();
		System.out.println(maReine);
		if(maReine.move(3, 1)) {
			System.out.println(maReine);
		} else {
			System.out.println("Déplacement impossible");
		}
		
		Pieces monPion = new Pion(new Coord(0,6), Couleur.BLANC);
		
		System.out.println(monPion);
		System.out.println(monPion.getName());
		if(monPion.move(1, 5)) {
			System.out.println(monPion);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monPion.move(0, 4)) {
			System.out.println(monPion);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monPion.move(0, 3)) {
			System.out.println(monPion);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monPion.move(0, 1)) {
			System.out.println(monPion);
		} else {
			System.out.println("Déplacement impossible");
		}
		if(monPion.move(1, 2)) {
			System.out.println(monPion);
		} else {
			System.out.println("Déplacement impossible");
		}
		/*monPion.capture();
		System.out.println(monPion);
		if(monPion.move(3, 1)) {
			System.out.println(monPion);
		} else {
			System.out.println("Déplacement impossible");
		}*/
	}
}
