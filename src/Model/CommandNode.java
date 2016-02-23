package Model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class CommandNode {

	private CommandsDataStruct commandsClass;
	
	public CommandNode(){
		commandsClass = new CommandsDataStruct();
	}
	
	public abstract void evaluate(String myCommand);
	
	public abstract void intermediateCanvasUpdate();
	
	public String determineTypeNArgs(String command){
		String noOfArgsNType = commandsClass.getInstance().grabPossibleCommands(command);
		int splitByIdx = noOfArgsNType.indexOf(":");
		String type = noOfArgsNType.substring(0, splitByIdx);
		int args = Integer.parseInt(noOfArgsNType.substring(splitByIdx, noOfArgsNType.length()));
			
		
			
			
			
	}

}
