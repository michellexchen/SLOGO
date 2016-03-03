package commandNode;

public interface ControlNode {

	static final public String CONTROLTYPE = "Control";
	
	public abstract void addVarParam(String string);
	
	default public String grabType(){
		return CONTROLTYPE;
	}
	
}
