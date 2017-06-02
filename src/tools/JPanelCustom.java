package tools;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import model.Coord;

public class JPanelCustom extends JPanel {
	private static final long serialVersionUID = 1L;

	private int x, y;

	public JPanelCustom(LayoutManager lm, int x, int y) {
		super(lm);
		this.x = x;
		this.y = y;
	}
	
	public Coord getCoord() {
		return new Coord(this.x, this.y);
	}
}
