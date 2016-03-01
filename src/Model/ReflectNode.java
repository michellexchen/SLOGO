package Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import Controller.SLogoException;

public class ReflectNode extends CommandNode{
	
	private String classType;
	private String commandType;
	private CharacterState state;
	private ArrayList<Node> children;
	
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	public void setState(CharacterState state){
		this.state = state;
	}
	
	public CharacterState getState(){
		return state;
	}

	public double evaluate(CharacterState state) throws SLogoException{
		this.state = state;
		Class thisClass = null;
		try {
			thisClass = Class.forName("Model."+classType+"Node");
		} catch (ClassNotFoundException e) {
			throw new SLogoException(classType+" not implemented");
		}
		Method method = null;
		try {
			method = thisClass.getMethod(commandType, new Class[] {});
		} catch (NoSuchMethodException | SecurityException e) {
			throw new SLogoException("Method " + commandType+ " not implemented");
		}
		try {
			return (double) method.invoke(this, new Object[] {});
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new SLogoException("Method " + commandType+ " not implemented in "+thisClass);
		}
	}

	
	public String getCommandType(){
		return commandType;
	}
	
	public String getClassType(){
		return classType;
	}
	
	public void setCommandType(String type){
		this.commandType = type;
	}
	
	public void setClassType(String type){
		this.classType = type;
	}
	
}
