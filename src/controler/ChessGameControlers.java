package controler;

import java.util.List;

import model.Coord;

public interface ChessGameControlers {

	
	/**
	 * @param initCoord
	 * @param finalCoord
	 * @return true si le déplacement s'est bien passé
	 */
	public boolean move(Coord initCoord, Coord finalCoord);
	
	/**
	 * @return true si le déplacement s'est bien passé
	 */
	public boolean undoMove();

	/**
	 * @return message relatif aux déplacement, capture, etc.
	 */
	public String getMessage();
	
	/**
	 * @return true si fin de partie OK (echec et mat, pat, etc.)
	 */
	public boolean isEnd();

	/**
	 * @param initCoord
	 * @return une info dont la vue se servira 
	 * pour empêcher tout déplacement sur le damier
	 */
	public boolean isPlayerOK(Coord initCoord);
	
	/**
	 * 
	 * @return liste des positions possible pour le pion donn� en param�tre
	 */
	public List<Coord> getPossiblePositions(Coord coord);

}
