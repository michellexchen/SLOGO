package commandnode;

import java.util.ArrayList;

import exception.SLogoException;
import model.ColorLoader;
import model.SLogoCharacterState;

public class SetPaletteNode extends QuaternionNode{
	
	ColorLoader myColorLoader;
	ArrayList<Node> myChildren;
	
	public SetPaletteNode() throws SLogoException {
		myColorLoader = new ColorLoader();
		myChildren = getChildren();
	}
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		myColorLoader.addRGB((int) evaluateChild(0, state),
				(int) evaluateChild(1, state),
				(int) evaluateChild(2, state),
				(int) evaluateChild(3, state));
		return evaluateChild(0, state);
	}
}