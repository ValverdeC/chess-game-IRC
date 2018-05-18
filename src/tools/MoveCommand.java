package tools;

import controler.ChessGameControlers;
import model.Coord;

public class MoveCommand implements ICommand {
	ChessGameControlers ctrl;
	Coord initCoord;
	Coord finalCoord;
	
	public MoveCommand(ChessGameControlers ctrl) {
		this.ctrl = ctrl;
		this.initCoord = null;
		this.finalCoord = null;
	}
	
	public MoveCommand(ChessGameControlers ctrl, Coord initCoord, Coord finalCoord) {
		this.ctrl = ctrl;
		this.initCoord = initCoord;
		this.finalCoord = finalCoord;
	}

	@Override
	public boolean faire() {
		return ctrl.move(this.initCoord, this.finalCoord);
	}
	
	@Override
	public boolean defaire() {
		return ctrl.undoMove();
	}

}
