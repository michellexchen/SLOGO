package commandNode;

import java.util.List;

import controller.CommandDriver;
import controller.LanguageDriver;
import exception.SLogoException;

/**
 * SLogo's Node Factory that creates and returns root node with subnodes
 * 
 * @author Adam Tache
 *
 */

public class NodeFactory {

	private CommandDriver CommandsDriver;
	private LanguageDriver langDriver = new LanguageDriver();

	public NodeFactory() throws SLogoException {
		CommandsDriver = new CommandDriver();
		langDriver = new LanguageDriver();
		langDriver.load("English"); // To remove
	}

	public Node createNode(List<String> nodeList) throws SLogoException {
		sanitate(nodeList);
		String englishCommand = nodeList.get(0);
		Node node = null;
		if (!langDriver.getLanguage().equals("English")) {
			// englishCommand = langDriver.getTranslation(myNode);
			// TODO: Implement proper translation (switch value and key in
			// current languages files)
		}
		if (isNumeric(englishCommand))
			return new NumericNode(Double.parseDouble(englishCommand));
		String commandName = CommandsDriver.getString(englishCommand);
		if (commandName == null)
			throw new SLogoException("The command \"" + englishCommand + "\" is illegal!");
		else
			try {
				node = (Node) Class.forName("CommandNode." + commandName + "Node").newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException("Command " + commandName + " is not yet implemented");
			}
		System.out.println("NODE CREATED " + node.toString());
		return node;
	}

	public void sanitate(List<String>nodeList){
		int idxOfMake = nodeList.indexOf("make");
		if(idxOfMake > 0){
			
		}
	}
	
	public void addChildren() {

	}

	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}