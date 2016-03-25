// This class is part of my masterpiece. This is a class that I created during the analysis in order to abstract code from TreeFactory.
// Used to allow the creation then execution of custom commands on the same line by creating then executing a root before the next.
// CommandController is used to control the execution of commands start to finish within the Model.
// Controls calls to SLogoParser, SLogoTreeFactory, and TreeEvaluator classes for evaluation and updating data.

package controller;

import java.util.ArrayList;
import java.util.List;

import commandnode.Node;
import exception.SLogoException;
import model.SLogoWorkspace;
import parser.SLogoParser;
import parser.TreeFactory;

/**
 * CommandController is used to control the execution of commands start to finish within the Model.
 * Controls calls to SLogoParser, SLogoTreeFactory, and TreeEvaluator classes for evaluation and updating data.
 *
 */
public class CommandController {

	private SLogoWorkspace myWorkspace;
	private TreeFactory myTreeFactory;

	public CommandController(SLogoWorkspace ws) throws SLogoException{
		this.myWorkspace = ws;
		initialize();
	}

	/**
	 * Initializes TreeFactory using workspace
	 *
	 */
	private void initialize() throws SLogoException{
		myTreeFactory = new TreeFactory(myWorkspace);
	}

	/**
	 * @param command, the command user typed into command prompt
	 * @return Command tokens, parsed by SLogoParser
	 *
	 */
	public List<String> getCommandTokens(String command) throws SLogoException{
		return new SLogoParser().readCommand(command);
	}

	/**
	 * @param tokens
	 * Evaluates tokens using executeRoot helper method.
	 * @return list of evaluations, one evaluation for each command part of tokens.
	 *
	 */
	public List<Double> evaluateTokens(List<String> tokens) throws SLogoException{
		List<Double> evaluations = new ArrayList<>();
		while (!tokens.isEmpty()) {
			Node myRoot = myTreeFactory.createRoot(tokens);
			evaluations.add(executeRoot(myRoot));
		}
		return evaluations;
	}
	
	/**
	 * @param Node myRoot: root of command tree to execute
	 * Uses corresponding TreeEvaluator to current workspace to execute command tree
	 */
	private double executeRoot(Node myRoot) throws SLogoException{
		return myWorkspace.getTreeEvaluator().evaluate(myRoot);
	}
}
