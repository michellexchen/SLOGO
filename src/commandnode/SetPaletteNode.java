package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Adds a new color (r, g, b) to list of colors
 */

public class SetPaletteNode extends QuaternionNode{
	
	/**
	 * @param state
	 * @return index of new color
	 */
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int index = (int) evaluateChild(0, state);
		int red = (int) evaluateChild(1, state);
		int green = (int) evaluateChild(2, state);
		int blue = (int) evaluateChild(3, state);
		RGBColor colorNode = new RGBColor(red, green, blue, index);
		//myColorLoader.addRGB(colorNode);
		return index;
	}
	
}