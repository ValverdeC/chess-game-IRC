package model;

import java.util.LinkedList;
import java.util.List;

import tools.ChessPiecesFactory;

public class Jeu implements Game {
	
	private List<Pieces> pieces;
	private Couleur couleur;
	// private boolean castling;

	public Jeu(Couleur couleur) {
		pieces = ChessPiecesFactory.newPieces(couleur);
		this.couleur = couleur;
		// this.castling = false;
	}

	@Override
	public boolean isPieceHere(int x, int y) {
		if(findPiece(x, y) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
		Pieces piece = findPiece(xInit, yInit);
		
		if(piece != null) {
			return piece.isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible);
		} else {
			return false;
		}
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		Pieces piece = findPiece(xInit, yInit);
		
		if(piece != null) {
			return piece.move(xFinal, yFinal);
		} else {
			return false;
		}
	}

	@Override
	public boolean capture(int xCatch, int yCatch) {
		Pieces piece = findPiece(xCatch, yCatch);
		return piece.capture();
	}

	public List<PieceIHMs> getPiecesIHM() {
		PieceIHMs newPieceIHM = null;
		List<PieceIHMs> list = new LinkedList<PieceIHMs>();
		
		for(Pieces piece : pieces) {
			if(piece.getX() != -1) {
				newPieceIHM = new PieceIHM(piece);
				list.add(newPieceIHM);
			}
		}
		return list;
	}
	
	@Override
	public String toString() {
		return "Jeu de couleur " + this.couleur +" [pieces=" + pieces + "]";
	}
	
	private Pieces findPiece(int x, int y) {
		for(Pieces piece : pieces) {
			if(piece.getX() == x && piece.getY() == y) {
				return piece;
			}
		}
		return null;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public Couleur getPieceCouleur(int x, int y) {
		Pieces piece = findPiece(x, y);
		Couleur couleur = null;
		
		if(piece != null) {
			couleur = piece.getCouleur();
		}
		
		return couleur;
	}
	
	public String getPieceName(int x, int y) {
		Pieces piece = findPiece(x, y);
		String nom = null;
		
		if(piece != null) {
			nom = piece.getName();
		}
		
		return nom;
	}
	
	/*public void setCastling() {
		this.castling = true;
	}*/

	public static void main(String[] args) {
		Jeu monJeu = new Jeu(Couleur.BLANC);
		System.out.println(monJeu);
		System.out.println(monJeu.move(0, 6, 0, 4));
		System.out.println(monJeu.isPieceHere(0, 4));
		System.out.println(monJeu.isMoveOk(0, 4, 0, 5, true, true));
	}
}
