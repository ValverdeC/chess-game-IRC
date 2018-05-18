package tools;

public class CommandInvocator {
	ICommand lastCommand = null;

	public CommandInvocator() {
		
	}
	
	public boolean perform(ICommand command) {
		this.lastCommand = command;
		return command.faire();
	}
	
	public boolean unperform(ICommand command) {
		return command.defaire();
	}
	
	public void reperform() {
		if (this.lastCommand != null) {
			this.lastCommand.faire();
		}
	}
}
