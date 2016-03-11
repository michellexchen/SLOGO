package commandnode;

import java.util.Optional;

import exception.SLogoException;
import parser.InstructionLoader;

public class RGBColor {

	private int red;
	private int green;
	private int blue;
	private int colorIndex;

	public RGBColor(int red, int green, int blue, int colorIndex) throws SLogoException{
		boundsCheck(red, green, blue);
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.colorIndex = colorIndex;
	}

	public int getR(){
		return red;
	}

	public int getG(){
		return green;
	}

	public int getB(){
		return blue;
	}

	public int getIndex(){
		return colorIndex;
	}

	public boolean boundsCheck(int red, int green, int blue) throws SLogoException{
		InstructionLoader instructLoader = new InstructionLoader();
		if((red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255)){
			SLogoException e = new SLogoException(instructLoader.getString("RGBColor"), 3);
			Optional<String> myNewInput = e.getNewInput();
			checkInput(myNewInput);
		}
		return true;
	}

	public int[] grabRGB(String input){
		String[] rgbStr = input.split(" ");
		int[] rgb = { Integer.parseInt(rgbStr[0]), Integer.parseInt(rgbStr[1]), Integer.parseInt(rgbStr[2])};
		return rgb;
	}

	public void checkInput(Optional<String> myNewInput) throws SLogoException{
		int[] rgb = grabRGB(myNewInput.get());
		boundsCheck(rgb[0], rgb[1], rgb[2]);
	}

}