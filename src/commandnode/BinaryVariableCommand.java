package commandnode;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that serves as a superclass for nodes
 * requiring two children
 *
 */
public abstract class BinaryVariableCommand extends VariableCommand{

	private int NUM_CHILDREN = 2;

	public BinaryVariableCommand(){
		super();
		setNumChildren(NUM_CHILDREN);
	}
	
	public List<String> listCopy(List<String> list){
		List<String> copy = new ArrayList<String>();
		copy.addAll(list);
		return copy;
	}

}