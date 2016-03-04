package CommandNode;
import java.util.List;

import Controller.Parser;
import Exception.SLogoException;
import Model.CharacterState;
import Model.Workspace;
import javafx.util.Pair;

public class EnclosureNode extends CommandNode {

	private int NUM_CHILDREN = 0;
	private String bracketContent;
	private Workspace ws;
	private Parser parser;

	public EnclosureNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public void setBracketContent(String content){
		String removedBrackets = content.substring(1, content.length() - 1);
		System.out.println(removedBrackets);
		String trimmedWhiteSpace = removedBrackets.trim();
		bracketContent = trimmedWhiteSpace;
		System.out.println("BRACKET CONTENT: "+bracketContent);
	}

	public void setWorkspace(Workspace ws){
		this.ws = ws;
	}

	public double evaluate(CharacterState state) throws SLogoException {
		parser = new Parser(ws);
		List<String> nodeTextList = parser.createNodeTextList(bracketContent);
		checkInnerEnclosures(nodeTextList, state);
		bracketContent = reformat(nodeTextList);
		return ws.readCommand(bracketContent);
	}
	
	public String reformat(List<String> nodeTextList){
		String toPrint = "";
		for(int x=0; x<nodeTextList.size(); x++){
			toPrint += nodeTextList.get(x)+" ";
		}
		return toPrint;
	}

	public void checkInnerEnclosures(List<String> nodeTextList, CharacterState state) throws SLogoException{
		for(int x=0; x<nodeTextList.size(); x++){
			String currNode = nodeTextList.get(x);
			if(isOpenEnclosure(currNode)){
				Pair<EnclosureNode, Integer> enclosureNodeAndEndIndex = parser.setUpOutermostEnclosureNode(nodeTextList, x);
				EnclosureNode en = enclosureNodeAndEndIndex.getKey();
				int closedEnclosureIndex = enclosureNodeAndEndIndex.getValue();
				String evaluation = en.evaluate(state)+"";
				nodeTextList.add(closedEnclosureIndex+1, evaluation);
				for(int y=x; y<closedEnclosureIndex-1; y++){
					nodeTextList.remove(y);
				}
			}
		}
	}
	
	public boolean isOpenEnclosure(String node){
		if(node.equals("(") || node.equals("["))
			return true;
		return false;
	}

	public boolean isClosedEnclosure(String node){
		if(node.equals(")") || node.equals("]"))
			return true;
		return false;
	}
	
	public String getBracketContent(){
		return bracketContent;
	}

}