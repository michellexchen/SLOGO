package Model;

import Controller.SLogoException;

public class BooleanNode extends ReflectNode{

	public void setType(String type) {
		super.setCommandType(type);
		super.setClassType("Boolean");
	}
	
	public double less() throws SLogoException{
		double expr1 = getChildren().get(0).evaluate(getState());
		for(int x=1; x<getChildren().size(); x++){
			if(expr1 > getChildren().get(x).evaluate(getState()))
				return 0;
		}
		return 1;
	}
	
	public double greater() throws SLogoException{
		double expr1 = getChildren().get(0).evaluate(getState());
		for(int x=1; x<getChildren().size(); x++){
			if(expr1 < getChildren().get(x).evaluate(getState()))
				return 0;
		}
		return 1;
	}
	
	public double equal() throws SLogoException{
		double expr1 = getChildren().get(0).evaluate(getState());
		for(int x=1; x<getChildren().size(); x++){
			if(expr1 != getChildren().get(x).evaluate(getState()))
				return 0;
		}
		return 1;
	}
	
	public double notequal() throws SLogoException{
		double expr1 = getChildren().get(0).evaluate(getState());
		for(int x=1; x<getChildren().size(); x++){
			if(expr1 == getChildren().get(x).evaluate(getState()))
				return 0;
		}
		return 1;
	}
	
	public double and() throws SLogoException{
		for(int x=0; x<getChildren().size(); x++){
			double expr = getChildren().get(x).evaluate(getState());
			if(expr == 0)
				return 1;
		}
		return 0;
	}
	
	public double or() throws SLogoException{
		for(int x=0; x<getChildren().size(); x++){
			double expr = getChildren().get(x).evaluate(getState());
			if(expr != 0)
				return 1;
		}
		return 0;
	}
	
	public double not() throws SLogoException{
		for(int x=0; x<getChildren().size(); x++){
			double expr = getChildren().get(x).evaluate(getState());
			if(expr != 0)
				return 0;
		}
		return 1;
	}

}