package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam
 *
 */
public class Workspace {

	private List<DisplayData> myDataList;
	private List<String> myCommandHistory;
	private List<Character> myCharacters;
	
	public Workspace(){
		myDataList = new ArrayList<DisplayData>();
		myCommandHistory = new ArrayList<String>();
		myCharacters = new ArrayList<Character>();		
	}
	
	public void createTurtle(){
		Turtle myTurtle = new Turtle("OG", 0, 0, true, 0, false);
		myCharacters.add(myTurtle);
	}
	
	public List<DisplayData> getDataList(){
		return myDataList;
	}
	
	public List<String> getCommandHistory(){
		return myCommandHistory;
	}
	
	public List<Character> getCharacterList(){
		return myCharacters;
	}
	
	public void addNewCharacter(Character character){
		myCharacters.add(character);
	}
	
	public void removeCharacter(Character character){
		myCharacters.remove(character);
	}
	
	public void addNewHistoryCommand(String command){
		myCommandHistory.add(command);
	}
	
	public void addDisplayData(DisplayData displayData){
		myDataList.add(displayData);
	}
}