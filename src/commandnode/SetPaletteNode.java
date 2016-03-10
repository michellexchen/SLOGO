package commandnode;
import exception.SLogoException;
import model.ColorLoader;
import model.SLogoCharacterState;

public class SetPaletteNode extends QuaternionNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		ColorLoader myColorLoader = new ColorLoader();
		int index = (int) evaluateChild(0, state);
		int red = (int) evaluateChild(1, state);
		int green = (int) evaluateChild(2, state);
		int blue = (int) evaluateChild(3, state);
		RGBColor colorNode = new RGBColor(red, green, blue, index);
		myColorLoader.addRGB(colorNode);
		return index;
	}
	
}