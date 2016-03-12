package parser;

import java.util.ArrayList;
import java.util.List;

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
 * SLogo's Tree Factory that creates abstract syntax tree of Nodes Creates
 * Nodes, that can either be CommandNodes (if command) or NumericNode (if
 * numerical)
 * 
 * @author Adam Tache
 *
 */
public class TreeFactory {

    private ResourceLoader myResourcesLoader;
    private LanguageLoader myLanguageLoader;
    private SLogoWorkspace myWorkspace;

    public TreeFactory(SLogoWorkspace ws) throws SLogoException {
        myResourcesLoader = new ResourceLoader();
        myWorkspace = ws;
        myLanguageLoader = new LanguageLoader();
        myLanguageLoader.load(myWorkspace.getView().getLanguage());
    }

    /**
     * Create our tree structure and necessary nodes 
     * with the list of parsed and formatted user input
     * 
     * @param List<String> commandParts - 
     * a list of formated input parsed and ready to be used for creation of Nodes
     */
    public List<Node> createNodes(List<String> commandParts) throws SLogoException {
        List<Node> myRoots = new ArrayList<Node>();
        while (!commandParts.isEmpty()) {
            String currCommand = commandParts.remove(0);
            Node myNode = createNode(currCommand);
            while (myNode.numCurrentChildren() != myNode.numRequiredChildren()) {
                myNode.addChild(createChild(commandParts));
            }
            myRoots.add(myNode);
        }
        return myRoots;
    }

    private List<String> listCopy(List<String> list){
        List<String> copy = new ArrayList<String>();
        copy.addAll(list);
        return copy;
    }

    /**
     * Create children of an already process Node
     * 
     * @param List<String> commandParts - a list of formated
     * input parsed and ready to be used for creation of Nodes
     */
    private Node createChild(List<String> commandParts) throws SLogoException {
        if(commandParts.isEmpty()) return null;
        String currCommand = commandParts.remove(0);
        if(isOpenBracket(currCommand) || isOpenParenthesis(currCommand)) {
            List<String> innerCommands = createCommandList(commandParts);
            List<String> innerCommandsCopy = listCopy(innerCommands);
            ListNode listNode;
            listNode = new ListNode(createNodes(innerCommands));
            listNode.setInnerCommands(innerCommandsCopy);
            return listNode;
        }
        if(isVariable(currCommand)) {
            VariableNode myVar = new VariableNode(currCommand);
            myVar.setWorkspace(myWorkspace);
            return myVar;
        } else{
            Node myChild = createNode(currCommand);
            while (myChild.numCurrentChildren() != myChild.numRequiredChildren()) {
                myChild.addChild(createChild(commandParts));
            }
            return myChild;
        }
    }


    /**
     * Create necessary tree processing when user inputs a list contained 
     * within brackets
     * 
     * @param List<String> commandParts - a list of formated input parsed 
     * and ready to be used for creation of Nodes
     */
    private List<String> createCommandList(List<String> commandParts) 
            throws SLogoException {
        List<String> innerCommands = new ArrayList<String>();
        int openBrackets = 1;
        int closedBrackets = 0;
        String currCommand = "";
        while (openBrackets != closedBrackets) {
            currCommand = commandParts.remove(0);
            if (isOpenBracket(currCommand) || isOpenParenthesis(currCommand))
                openBrackets++;
            else if (isClosedBracket(currCommand) || isClosedParenthesis(currCommand))
                closedBrackets++;
            if(openBrackets != closedBrackets)
                innerCommands.add(currCommand);
        }
        return innerCommands;
    }
    /**
     * Create node
     * 
     * @param String strNode - create the necessary node from the string passed
     */
    private Node createNode(String strNode) throws SLogoException {
        Node node = null;
        String commandName = myLanguageLoader.getTranslation(strNode.toLowerCase());
        if (isNumeric(strNode))
            return new NumericNode(Double.parseDouble(strNode));
        if(isVariable(commandName)){
            return new NumericNode(0);
        }
        else
            try {
                node = (Node) Class.forName(
                                            myResourcesLoader.getString("CommandNode") + commandName 
                                            + myResourcesLoader.getString("Node")).newInstance();
            } catch (ClassNotFoundException | InstantiationException 
                    | IllegalAccessException e) {
                throw new SLogoException(myResourcesLoader.getString("Command") 
                                         + commandName + myResourcesLoader.getString("Implemented"));
            }
        if(node instanceof VariableCommand){
            ((VariableCommand)node).setWorkspace(myWorkspace);
        }
        if(node instanceof TurtleCommand){
            ((TurtleCommand)node).setWorkspace(myWorkspace);
        }
        return node;
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
    private boolean isOpenBracket(String str) {
        return str.equals("[");
    }

    /**
     * Determine if the string inputed is an open parenthesis
     * 
     * @param String str - string to be compared
     */
    private boolean isOpenParenthesis(String str) {
        return str.equals("(");
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
    private boolean isClosedBracket(String str) {
        return str.equals("]");
    }

    /**
     * Determine if the string inputed is a closed parenthesis
     * 
     * @param String str - string to be compared
     */
    private boolean isClosedParenthesis(String currCommand) {
        return currCommand.equals(")");
    }

}