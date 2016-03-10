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
		myColorLoader.addRGB((int) myChildren.get(0).evaluate(state),
				(int) myChildren.get(1).evaluate(state),
				(int) myChildren.get(2).evaluate(state),
				(int) myChildren.get(3).evaluate(state));
		return myChildren.get(0).evaluate(state);
	}
}
