// This is part of my code masterpiece.
// This class was created in order for TreeFactory to adhere to the closed/open principle for creation of new commands.
// This class eliminates the need for specific if statements for setting the current workspace for commands.
// This class also extends CommandNode and is extended by various classes (such as TurtleCommand, VariableCommand) so is core to the command hierarchy.
// Provides access to factories for subclasses to use if necessary (reduces duplicate code)

package commandnode;

import controller.CommandController;
import exception.SLogoException;
import model.SLogoTurtleFactory;
import model.SLogoWorkspace;
import parser.TreeEvaluator;
import parser.TreeFactory;

public abstract class WorkspaceNode extends CommandNode{

	private SLogoWorkspace myWorkspace;
    private TreeEvaluator myTreeEvaluator;
    private TreeFactory myTreeFactory;
    private SLogoTurtleFactory myTurtleFactory;
    private CommandController myCommandController;

    public void initialize(SLogoWorkspace ws) throws SLogoException{
        this.myWorkspace = ws;
        setup();
    }
    
    private void setup() throws SLogoException{
    	myTreeEvaluator = new TreeEvaluator(myWorkspace);
        myTreeFactory = new TreeFactory(myWorkspace);
        myTurtleFactory = new SLogoTurtleFactory(myWorkspace);
        myCommandController = new CommandController(myWorkspace);
    }
    
    public SLogoWorkspace getWorkspace(){
        return myWorkspace;
    }
    
    public TreeFactory getTreeFactory(){
        return myTreeFactory;
    }

    public TreeEvaluator getTreeEvaluator(){
        return myTreeEvaluator;
    }
    
    public SLogoTurtleFactory getTurtleFactory(){
    	return myTurtleFactory;
    }
    
    public CommandController getCommandController(){
    	return myCommandController;
    }
    
}