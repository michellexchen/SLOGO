// This entire file is part of my masterpiece.
// Aaron Newman
//
//I chose to refactor the Tree Factory class rather than a portion of code that
//I had contributed to more significantly during the project duration because my
//contributions have been repeatedly refactored by myself and other team members
//to the point where there are few additionaly changes I could make. In contrast,
//I see multiple ways in which the Tree Factory could be improved.
//
//Purpose: This class is responsible for creating the command tree structure for a generic command
//and performing appropriate setup for the root node depending on its type
//
//Reasoning: I believe this class is well-designed because it effectively delegates responsibilities to
//other classes in order to maintain focus on its main function of producing command trees. Additionally,
//each method generally has only a single, well-defined function, and those with multiple functions are 
//clearly identified by their calling name. Most methods are concise and short in length. Code is very readable.
//In order to further improve this class, a tree interface could be implemented to access the trees that are 
//produced from the factory class.
//

package parser;

import java.util.ArrayList;
import java.util.List;

import commandnode.CommandNode;
import commandnode.CustomCommandNode;
import commandnode.CustomFunctionNode;
import commandnode.ListNode;
import commandnode.Node;
import commandnode.NumericNode;
import commandnode.TurtleCommand;
import commandnode.VariableCommand;
import exception.SLogoException;
import model.LanguageLoader;
import model.ResourceLoader;
import model.SLogoWorkspace;
import commandnode.VariableNode;

/**
 * SLogo's Tree Factory creates abstract syntax trees of Nodes for evaluation of commands
 * 
 * @author Adam Tache
 *
 */
public class TreeFactory {

    private ResourceLoader myResourceLoader;
    private LanguageLoader myLanguageLoader;
    private SLogoWorkspace myWorkspace;
    
    private final String LEADING_NUMBER = getResource("LeadingNumber");
    private final String INVALID_COMMAND = getResource("InvalidCommandTokens");
    private final String COMMAND_NODE = getResource("CommandNode");
    private final String NODE = getResource("Node");
    private final String COMMAND = getResource("Command");
    private final String IMPLEMENTED = getResource("Implemented");
    
    
    public TreeFactory (SLogoWorkspace ws) throws SLogoException {
        myResourceLoader = new ResourceLoader();
        myWorkspace = ws;
        myLanguageLoader = new LanguageLoader();
        myLanguageLoader.load(myWorkspace.getView().getLanguage());
    }
    
    /**
     * Creates tree structures as List<Node> of roots for evaluation
     * Uses createRoot helper method to create correct type of root
     * 
     * @param List<String> commandParts - 
     * a list of formated input parsed and ready to be used for creation of Nodes
     */
    public List<Node> createRootList(List<String> commandParts) throws SLogoException {
        List<Node> myRootList = new ArrayList<>();
        while (!commandParts.isEmpty()) {
            String myRootToken = commandParts.remove(0);
            Node myRoot = createRootAndSetWorkspace(myRootToken);
            if (isToCommand(myRootToken)) {
            	String customName = commandParts.remove(0);
            	myRoot.addChild(new CustomCommandNode(customName, myWorkspace));
            }
            if(isNumeric(myRootToken)){
            	throw new SLogoException(LEADING_NUMBER);
            }
            myRootList.add(createSubtree(myRoot, commandParts));
        }
        return myRootList;
    }

    /**
     * Create children of an already process Node
     * 
     * @param List<String> commandParts - a list of formated
     * input parsed and ready to be used for creation of Nodes
     */
    private Node createChild(List<String> commandParts) throws SLogoException {
        if (commandParts.isEmpty()) {
            return null;
        }
        String myChildToken = commandParts.remove(0);
        if (isOpenBracketOrParenthesis(myChildToken)) {
            List<String> innerCommands = createCommandList(commandParts);
            ListNode listNode = new ListNode(myWorkspace);
            listNode.setInnerCommands(innerCommands);
            return listNode;
        }
        if (isVariable(myChildToken)) {
            VariableNode myVar = new VariableNode(myChildToken);
            myVar.setWorkspace(myWorkspace);
            return myVar;
        } else {
            Node myChild = createRootAndSetWorkspace(myChildToken);
            return createSubtree(myChild, commandParts);
        }
    }
    
    /**
     * Adds immediate children to subtree root node
     * 
     * @param Node root - subtree root node
     * @param List<String> commands - a list of formated input parsed
     * and ready to be used for creation of Nodes
     * 
     * @return Node root - populated subtree
     */
    
    private Node createSubtree(Node root, List<String> commands) throws SLogoException {
    	while (root.numCurrentChildren() != root.numRequiredChildren()) {
    		Node nextChild = createChild(commands);
        	if(nextChild == null){
        		throw new SLogoException(INVALID_COMMAND);
        	}
        	root.addChild(nextChild);
        }
    	return root;
    }

    /**
     * Create necessary tree processing when user inputs a list contained 
     * within brackets
     * 
     * @param List<String> commandParts - a list of formated input parsed 
     * and ready to be used for creation of Nodes
     */
    
    private List<String> createCommandList(List<String> commandParts) throws SLogoException {
        List<String> innerCommands = new ArrayList<>();
        int openBrackets = 1;
        int closedBrackets = 0;
        String currCommand;
        while (openBrackets != closedBrackets) {
            currCommand = commandParts.remove(0);
            if (isOpenBracketOrParenthesis(currCommand)) {
                openBrackets++;
            }
            else if (isClosedBracketOrParenthesis(currCommand)) {
                closedBrackets++;
            }
            if(openBrackets != closedBrackets) {
                innerCommands.add(currCommand);
            }
        }
        return innerCommands;
    }
    
    /**
     * Creates root by looking at root token
     * 
     * @param String strNode - create the necessary node from the string passed
     */
    
    private Node createRootAndSetWorkspace(String rootToken) throws SLogoException {
        CommandNode node;
        String rootName = myLanguageLoader.getTranslation(rootToken.toLowerCase());
        if (isNumeric(rootName)) {
            return new NumericNode(Double.parseDouble(rootToken));
        } else if (isVariable(rootName)) {
            return new NumericNode(0);
        } else if (isCustom(rootName)) {
            CustomFunctionNode function = new CustomFunctionNode((myWorkspace.lookupCustomCommand(rootName)));
            function.setWorkspaceAndDependents(myWorkspace);
            return function;
        } else {
            try {
                node = (CommandNode) Class.forName(COMMAND_NODE + rootName + NODE).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new SLogoException(COMMAND + rootName + IMPLEMENTED);
            }
        }
        setWorkspace(node);
        return node;
    }
    
    private void setWorkspace(Node myNode) throws SLogoException {
    	if (myNode instanceof VariableCommand) {
            ((VariableCommand) myNode).setWorkspaceAndDependents(myWorkspace);
        }
    	else if (myNode instanceof TurtleCommand) {
            ((TurtleCommand) myNode).setWorkspace(myWorkspace);
        }
    }

    /**
     * Determine if the string inputed has a string regrex equal to numeric notation
     * 
     * @param String str - string to be compared
     */
    private boolean isNumeric(String str) {
        return str.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Determine if the string inputed is an open parenthesis
     * 
     * @param String str - string to be compared
     */
    private boolean isOpenBracketOrParenthesis(String str) {
        return str.equals("[") || str.equals("(");
    }

    /**
     * Determine if the string inputed is regrex equal to variable notation
     * 
     * @param String str - string to be compared
     */
    private boolean isVariable(String str) {
        return str.matches(":[a-zA-Z_]+");
    }

    /**
     * Determine if the string inputed is a closed bracket
     * 
     * @param String str - string to be compared
     */
    private boolean isClosedBracketOrParenthesis(String str) {
        return str.equals("]") || str.equals(")");
    }
    
    /**
     * Determine if the string inputed is a custom command in current workspace
     * 
     * @param String str - string to be compared
     */
    private boolean isCustom(String str){
    	return myWorkspace.lookupCustomCommand(str) != null;
    }
    
    private boolean isToCommand(String command){
    	return myLanguageLoader.getTranslation(command.toLowerCase()).equals(new ResourceLoader().getString("MakeUserInstruction"));
    }

    /**
     * @return the myResourceLoader
     */
    public String getResource (String code) {
        return myResourceLoader.getString(code);
    }

}