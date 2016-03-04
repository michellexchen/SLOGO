package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import CommandNode.NodeFactory;
import Exception.SLogoException;
import Model.Workspace;
import javafx.util.Pair;
import CommandNode.EnclosureNode;
import CommandNode.Node;

public class Parser {

	private NodeFactory nf;
	private Workspace ws;

	public Parser(Workspace ws) throws SLogoException{
		nf = new NodeFactory();
		this.ws = ws;
	}

	public List<String> format(String text) throws SLogoException {
		return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public List<String> createNodeTextList(String text) throws SLogoException{
		if (text == null)
			throw new SLogoException("Did not input correct command");
		return format(text);
	}

	public List<Node> parse(String text) throws SLogoException{
		List<String> nodeTextList = createNodeTextList(text);
		List<Node> nodeList = new ArrayList<Node>();
		nodeList = setUpNodeList(nodeTextList, nodeList);
		System.out.println(nodeList);
		return nodeList;
	}
	
	public List<Node> setUpNodeList(List<String> nodeTextList, List<Node> nodeList) throws SLogoException{
		for(int x=0; x<nodeTextList.size(); x++){
			String currNode = nodeTextList.get(x);
			if(isOpenEnclosure(currNode)){
				Pair<EnclosureNode, Integer> enclosureNodeAndEndIndex = setUpOutermostEnclosureNode(nodeTextList, x);
				EnclosureNode en = enclosureNodeAndEndIndex.getKey();
				int closedEnclosureIndex = enclosureNodeAndEndIndex.getValue();
				nodeList.add(en);
				x = closedEnclosureIndex;
			}
			else{
				nodeList.add(nf.createNode(nodeTextList.get(x)));
			}
		}
		return nodeList;
	}
	
	public Pair<EnclosureNode, Integer> setUpOutermostEnclosureNode(List<String> nodeTextList, int openEnclosureIndex){
		int openEnclosures = 1;
		int closedEnclosures = 0;
		String currNode = "";
		Pair<EnclosureNode, Integer> tuple = null;
		for(int y=openEnclosureIndex+1; y<nodeTextList.size(); y++){
			currNode = nodeTextList.get(y);
			if(isClosedEnclosure(currNode)){
				closedEnclosures++;
				if(openEnclosures == closedEnclosures){
					System.out.println("NODE TEXT LIST: " + nodeTextList);
					EnclosureNode en = nf.createEnclosureNode(openEnclosureIndex, y, nodeTextList, ws);
					tuple = new Pair<EnclosureNode, Integer>(en, y);
					break;
				}
			}
			else{
				if(isOpenEnclosure(currNode)){
					openEnclosures++;
				}
			}
		}
		return tuple;
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

}