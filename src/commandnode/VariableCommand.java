package commandnode;

import exception.SLogoException;
import model.SLogoWorkspace;
import parser.RootEvaluator;
import parser.TreeFactory;

public abstract class VariableCommand extends CommandNode{
	private SLogoWorkspace myWorkspace;
	private TreeFactory myTreeFactory;
	private RootEvaluator myRootEvaluator;
	
	public VariableCommand(){
		try {
			myTreeFactory = new TreeFactory(getWorkspace());
		} catch (SLogoException e) {
			new SLogoException(getResource("TreeFactory"));
		}
		myRootEvaluator = new RootEvaluator(getWorkspace());
	}

	public SLogoWorkspace getWorkspace(){
		return myWorkspace;
	}

	public void setWorkspace(SLogoWorkspace ws){
		myWorkspace = ws;
	}
	
	public TreeFactory getTreeFactory(){
		return myTreeFactory;
	}
	
	public RootEvaluator getRootEvaluator(){
		return myRootEvaluator;
	}

}