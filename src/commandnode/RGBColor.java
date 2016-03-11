package commandnode;

import java.util.Optional;

import exception.SLogoException;
import parser.InstructionLoader;

/**
 * Node representation of RGB Value with bounds error checking, used for SetPalette creation
 */
public class RGBColor {

	private int red;
	private int green;
	private int blue;
	private int colorIndex;

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param colorIndex in color map
	 * @throws SLogoException for correcting values
	 */
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

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @return true when input RGB values fit range 0-255
	 * @throws SLogoException for correcting values by user
	 * Uses myNewInput, user input grabbed from SLogoException typed by user in text popup
	 */
	public boolean boundsCheck(int red, int green, int blue) throws SLogoException{
		InstructionLoader instructLoader = new InstructionLoader();
		if((red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255)){
			SLogoException e = new SLogoException(instructLoader.getString("RGBColor"), 3);
			Optional<String> myNewInput = e.getNewInput();
			checkInput(myNewInput);
		}
		return true;
	}

	/**
	 * @param Unparsed user input (in form "R G B")
	 * @return integer array {R, G, B}
	 */
	public int[] grabRGB(String input){
		String[] rgbStr = input.split(" ");
		int[] rgb = { Integer.parseInt(rgbStr[0]), Integer.parseInt(rgbStr[1]), Integer.parseInt(rgbStr[2])};
		return rgb;
	}

	/**
	 * @param myNewInput, user input entered in SLogoException text popup
	 * Calls boundsCheck to ensure proper user input
	 * @throws SLogoException
	 */
	public void checkInput(Optional<String> myNewInput) throws SLogoException{
		int[] rgb = grabRGB(myNewInput.get());
		boundsCheck(rgb[0], rgb[1], rgb[2]);
	}

}