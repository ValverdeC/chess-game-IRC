package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.PieceIHM;
import tools.ChessImageProvider;
import tools.JPanelCustom;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    JLabel piece;
    JPanel panel;
    JPanelCustom pieceClicked;
    int xAdjustment;
    int yAdjustment;
    ChessGameControler controler;
 
    public ChessGameGUI(String frameName, ChessGameControler chessGameControler, Dimension dim){
    	Dimension boardSize = dim;
        this.controler = chessGameControler;
        //  Use a Layered Pane for this this application
 
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
        this.addKeyListener(this);
        //Add a chess board to the Layered Pane 
 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int y = 0; y < 8; y++) {
        	
        	for(int x = 0; x < 8; x++) {
                JPanelCustom square = new JPanelCustom( new BorderLayout(), x, y );
                chessBoard.add( square );
                
                int row = y%2;
                if (row == 0)
                    square.setBackground( ((y*8)+x) % 2 == 0 ? Color.white : Color.black );
                else
                    square.setBackground( ((y*8)+x) % 2 == 0 ? Color.black : Color.white );
        	}
        }
    }
    
    private void coloredPossiblePositions(List<Coord> list) {
    	for(Coord coord : list) {
    		this.chessBoard.getComponent(8*coord.y + coord.x).setBackground(Color.blue);
    	}
    }
    
    private void reinitSquares() {
    	for (int y = 0; y < 8; y++) {
        	for(int x = 0; x < 8; x++) {
        		
                
                int row = y%2;
                if (row == 0)
                	this.chessBoard.getComponent((y*8)+x).setBackground(((y*8)+x) % 2 == 0 ? Color.white : Color.black );
                else
                	this.chessBoard.getComponent((y*8)+x).setBackground(((y*8)+x) % 2 == 0 ? Color.black : Color.white );
        	}
        }
    }
 
    public void mousePressed(MouseEvent e){
        
    	chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
                
        if (c instanceof JPanelCustom) 
        	return;
        this.pieceClicked = (JPanelCustom)c.getParent();
        
        if(this.controler.isPlayerOK(this.pieceClicked.getCoord())) {
        
	        this.coloredPossiblePositions(this.controler.getPossiblePositions(pieceClicked.getCoord()));
	
	        
	        Point parentLocation = c.getParent().getLocation();
	        xAdjustment = parentLocation.x - e.getX();
	        yAdjustment = parentLocation.y - e.getY();
	        chessPiece = (JLabel)c;
	        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
	        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
        }
    }
   
    //Move the chess piece around
    
    public void mouseDragged(MouseEvent me) {
        if(this.controler.isPlayerOK(this.pieceClicked.getCoord())) {

        	if (chessPiece == null) return;
        	chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     
        }
    }
     
  //Drop the chess piece back onto the chess board
 
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;
 
        chessPiece.setVisible(false);
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        
        this.reinitSquares();
        
        if (c instanceof JLabel){
        	if(this.controler.move(this.pieceClicked.getCoord(), ((JPanelCustom) c.getParent()).getCoord())) {
	            Container parent = (Container)c;
	            parent.add( chessPiece );
	            chessPiece.setVisible(true);
        	}
        }
        else {
        	if(this.controler.move(this.pieceClicked.getCoord(), ((JPanelCustom) c).getCoord())) {
	            Container parent = (Container)c;
	            parent.add( chessPiece );
	            chessPiece.setVisible(true);
        	}
        }
    }
 
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e) {
	
    }
 
    public static void main(String[] args) {
//        JFrame frame = new ChessGameGUI();
//        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
//        frame.pack();
//        frame.setResizable(true);
//        frame.setLocationRelativeTo( null );
//        frame.setVisible(true);
     }

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		
		for(int i = 0; i<8*8;i++){
			this.panel = (JPanel)chessBoard.getComponent(i);
			this.panel.removeAll();
		}
		
		List<PieceIHM> list = (List<PieceIHM>) arg;
		
		for(PieceIHM pieceIHM : list) {
        	this.piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(pieceIHM.getNamePiece(), pieceIHM.getCouleur())) );
        	this.panel = (JPanel)chessBoard.getComponent(8*pieceIHM.getY()+pieceIHM.getX());
        	this.panel.add(this.piece);
        }
	}

	@Override
	public void keyPressed(KeyEvent e) {
				
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            this.controler.undoMove();
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
