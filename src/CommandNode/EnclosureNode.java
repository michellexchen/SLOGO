package CommandNode;
import java.util.List;

import Controller.Parser;
import Exception.SLogoException;
import javafx.util.Pair;
import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;

public class EnclosureNode extends UnaryNode {

	private String bracketContent;
	private SLogoWorkspace ws;
	private Parser parser;

	public void setBracketContent(String content){
		String removedBrackets = content.substring(1, content.length() - 1);
		String trimmedWhiteSpace = removedBrackets.trim();
		bracketContent = trimmedWhiteSpace;
		System.out.println("BRACKET CONTENT: "+bracketContent);
	}

	public void setWorkspace(SLogoWorkspace ws){
		this.ws = ws;
	}

	public double evaluate(SLogoCharacterState state) throws SLogoException {
//		parser = new Parser(ws);
//		List<String> nodeTextList = parser.createNodeTextList(bracketContent);
//		checkInnerEnclosures(nodeTextList, state);
//		bracketContent = reformat(nodeTextList);
//		return ws.readCommand(bracketContent);
		return 0;
	}
	
	public String reformat(List<String> nodeTextList){
		String toPrint = "";
		for(int x=0; x<nodeTextList.size(); x++){
			toPrint += nodeTextList.get(x)+" ";
		}
		return toPrint;
	}

	public void checkInnerEnclosures(List<String> nodeTextList, SLogoCharacterState state) throws SLogoException{
		for(int x=0; x<nodeTextList.size(); x++){
			String currNode = nodeTextList.get(x);
			if(isOpenEnclosure(currNode)){
				Pair<EnclosureNode, Integer> enclosureNodeAndEndIndex = null;//parser.setUpOutermostEnclosureNode(nodeTextList, x);
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