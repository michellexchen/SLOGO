package model;
import java.util.ArrayList;
import java.util.List;

import commandnode.ListNode;

public class SLogoCustomCommand{

	private String myName;
	private ListNode myVariables;
	private ListNode myCommands;

	public SLogoCustomCommand(String myName, ListNode myVariables, ListNode myCommands){
		this.myName = myName;
		this.myVariables = myVariables;
		this.myCommands = myCommands;
	}

	public ListNode getMyVariables(){
		return myVariables;
	}

	public ListNode getMyCommands(){
		return myCommands;
	}

	public void setMyCommands(ListNode myCommands){
		this.myCommands = myCommands;
	}

	public void setMyVariables(ListNode myVariables){
		this.myVariables = myVariables;
	}

	public List<String> getVariableNames(){
		List<String> varNames = new ArrayList<String>();
		myVariables.getInnerCommands().stream().forEach(var -> varNames.add(var.toString()));
		return varNames;
	}

	public String getName(){
		return myName;
	}

	public void setName(String myName){
		this.myName = myName;
	}

	public double run() {
		// TODO Auto-generated method stub
		return 0;
	}

}