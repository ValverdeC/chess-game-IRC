package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Echiquier implements BoardGames {
	
	private Jeu jeuBlanc;
	private Jeu jeuNoir;
	private Jeu jeuCourant, jeuOppose;
	private String message;	
	
	public Echiquier() {
		super();
		this.jeuBlanc = new Jeu(Couleur.BLANC);
		this.jeuNoir = new Jeu(Couleur.NOIR);
		this.message = "Le jeu blanc commence la partie";
		this.jeuCourant = this.jeuBlanc;
		this.jeuOppose = this.jeuNoir;
	}
	
	/*L'algo est le suivant : 
	s'il n'existe pas de piece du jeu courant aux coordonn�es initiales --> false, 
	si les coordonn�es finales ne sont pas valides ou �gales aux initiales --> false, 
	si position finale ne correspond pas � algo de d�placement piece --> false, 
	s'il existe une pi�ce interm�diaire sur la trajectoire --> false (sauf cavalier), 
	s'il existe une pi�ce positionn�es aux coordonn�es finales :
	si elle est de la m�me couleur --> false ou tentative roque du roi, 
	sinon : prendre la pi�ce interm�diaire (vigilance pour le cas du pion) et d�placer la pi�ce -->true,
	sinon d�placer la pi�ce -->true*/
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean moveOk = false;
		boolean isCatch = isCatchOk(xFinal, yFinal);
		String mess = "[Joueur " + jeuCourant.getCouleur() + "] : Mouvement impossible";

		if(jeuCourant.isPieceHere(xInit, yInit)) {
			if(Coord.coordonnees_valides(xFinal, xFinal)) {
				if(!jeuCourant.isPieceHere(xFinal, yFinal)) {
					if(!somethingOnRoad(xInit, yInit, xFinal, yFinal)) {
						moveOk = jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal, isCatch, true);
						mess = "[Joueur " + jeuCourant.getCouleur() + "] : Mouvement effectu�";
					}
				}
			}
		}
		
		setMessage(mess);
		return moveOk;
	}
	
	private boolean isCatchOk(int xFinal, int yFinal) {
		boolean isCatch = false;
		if(jeuOppose.isPieceHere(xFinal, yFinal)) {
			isCatch = true;
		}
		return isCatch;
	}
	
	public List<PieceIHMs> getPiecesIHM() {
		List<PieceIHMs> list = new LinkedList<>();
		list.addAll(jeuBlanc.getPiecesIHM());
		list.addAll(jeuNoir.getPiecesIHM());
		return list;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		if(isCatchOk(xFinal, yFinal)) {
			jeuOppose.capture(xFinal, yFinal);
		}
		return jeuCourant.move(xInit, yInit, xFinal, yFinal);

	}
	
	private boolean somethingOnRoad(int xInit, int yInit, int xFinal, int yFinal) {
		boolean thereIsOne = false;
		
		//Si on est pas un cavalier
		if(!jeuCourant.getPieceName(xInit, yInit).equals("Cavalier")) {
			//D�placement horizontal
			if(yInit == yFinal && xInit != xFinal) {
				if(xInit < xFinal) {
					for(int i = 1; i < xFinal - xInit; i++) {
						if(jeuCourant.isPieceHere(xInit + i, yFinal) || jeuOppose.isPieceHere(xInit + i, yFinal)) {
							thereIsOne = true;
						}
					}
				} else {
					for(int i = 1; i < xInit - xFinal; i++) {
						if(jeuCourant.isPieceHere(xFinal + i, yFinal) || jeuOppose.isPieceHere(xFinal + i, yFinal)) {
							thereIsOne = true;
						}
					}
				}
			} else if(yInit != yFinal && xInit == xFinal) { //D�placement vertical
				if(yInit < yFinal) {
					for(int i = 1; i < yFinal - yInit; i++) {
						if(jeuCourant.isPieceHere(xFinal, yInit + i) || jeuOppose.isPieceHere(xFinal, yInit + i)) {
							thereIsOne = true;
						}
					}
				} else {
					for(int i = 1; i < yInit - yFinal; i++) {
						if(jeuCourant.isPieceHere(xFinal, yFinal + i) || jeuOppose.isPieceHere(xFinal, yFinal + i)) {
							thereIsOne = true;
						}
					}
				}
				//Test particulier pour pion
				if((jeuCourant.getPieceName(xInit, yInit).equals("Pion")) && (jeuOppose.isPieceHere(xFinal, yFinal))) {
					thereIsOne = true;
				}
			} else {
				//D�placement diagonale restant uniquement
				if(xFinal - xInit > 0 && yFinal - yInit < 0) {
					for(int i = 1; i < xFinal - xInit; i++) {
						if(jeuCourant.isPieceHere(xInit + i, yInit - i) || jeuOppose.isPieceHere(xInit + i, yInit - i)) {
							thereIsOne = true;
						}
					}
				} else if(xFinal - xInit < 0 && yFinal - yInit < 0) {
					for(int i = 1; i < xInit - xFinal; i++) {
						if(jeuCourant.isPieceHere(xInit - i, yInit - i) || jeuOppose.isPieceHere(xInit - i, yInit - i)) {
							thereIsOne = true;
						}
					}
				} else if(xFinal - xInit < 0 && yFinal - yInit > 0) {
					for(int i = 1; i < xInit - xFinal; i++) {
						if(jeuCourant.isPieceHere(xInit - i, yInit + i) || jeuOppose.isPieceHere(xInit - i, yInit + i)) {
							thereIsOne = true;
						}
					}
				} else {
					for(int i = 1; i < xFinal - xInit; i++) {
						if(jeuCourant.isPieceHere(xInit + i, yInit + i) || jeuOppose.isPieceHere(xInit + i, yInit + i)) {
							thereIsOne = true;
						}
					}
				}
			}
		}
		
		
		return thereIsOne;
	}
	
	public List<Coord> getPossiblePositions(Coord coord) {
		List<Coord> list = new ArrayList<>();
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(jeuCourant.isMoveOk(coord.x, coord.y, x, y, isCatchOk(x, y), false)) {
					list.add(new Coord(x, y));
				}
			}
		}
		return list;
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	private void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		return jeuCourant.getCouleur();
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		return this.jeuCourant.getPieceCouleur(x, y);
	}
	
	public void switchJoueur() {
		if(this.jeuCourant == this.jeuBlanc) {
			this.jeuCourant = this.jeuNoir;
			this.jeuOppose = this.jeuBlanc;
		} else {
			this.jeuOppose = this.jeuNoir;
			this.jeuCourant = this.jeuBlanc;
		}
	}

	@Override
	public String toString() {
		return "Echiquier:\n" + jeuBlanc + "\n" + jeuNoir + "]";
	}
	
	public static void main(String[] args) {
		Echiquier monEchiquier = new Echiquier();
		System.out.println(monEchiquier);
	}
}
