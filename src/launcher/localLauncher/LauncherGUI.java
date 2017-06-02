package launcher.localLauncher;

import java.awt.Dimension;
import java.util.Observer;

import javax.swing.JFrame;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.observable.ChessGame;
import vue.ChessGameGUI;



/**
 * @author francoise.perrin
 * Lance l'exécution d'un jeu d'échec en mode graphique.
 * La vue (ChessGameGUI) observe le modèle (ChessGame)
 * les échanges passent par le contrôleur (ChessGameControlers)
 * 
 */
public class LauncherGUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ChessGame chessGame;	
		ChessGameControler chessGameControler;
		JFrame frame;	
		Dimension dim;
	
		dim = new Dimension(700, 700);
		
		chessGame = new ChessGame();	
		chessGameControler = new ChessGameControler(chessGame);
		
		frame = new ChessGameGUI("Jeu d'�chec", chessGameControler,  dim);
		chessGame.addObserver((Observer) frame);
		frame.setTitle("Jeu d'�chec");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(600, 10);
		frame.pack();
		frame.setVisible(true);
	}
}
