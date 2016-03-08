package algorithms;

public class EncodingVars implements Strategy{

	public EncodingVars() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double encodeDecodeAlgorithm(String name) {
		// TODO Auto-generated method stub
		String datagram = "";
		int encodingBitStuff = 99;
		for(int i = 1; i < name.length(); i++){
			if(i > 7) break;
			char ch = name.charAt(i);
			if(ch == '_') System.out.println("used underscore");
			int asciiRepOfVariable = Character.getNumericValue(ch);
			System.out.println(ch + " translates to letter " + asciiRepOfVariable);
//			asciiRepOfVariable -= 44;
//			if(i == name.length()/2) datagram += ".";
			datagram += asciiRepOfVariable;
			System.out.println("datagram : " + datagram);
		}
		datagram += encodingBitStuff;
		double i = Double.parseDouble(datagram);
		return i;
	}

}
