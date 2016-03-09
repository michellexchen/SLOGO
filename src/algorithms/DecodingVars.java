package algorithms;

public class DecodingVars implements Strategy{

	public DecodingVars() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double encodeDecodeAlgorithm(String name) {
		
		while(true){
			String stringRepresentation = "" + Double.parseDouble(name);
			System.out.println(stringRepresentation);
			if(stringRepresentation.contains("E")) stringRepresentation = stringRepresentation.substring(0,1) + stringRepresentation.substring(2,stringRepresentation.length());
			String result = "";
			for(int i = 0; i < stringRepresentation.length()-2;i+=2){
				String numRepOfChar = stringRepresentation.substring(i, i+2);
				if(numRepOfChar.equals("99")) break;
				char ch = Character.forDigit(Integer.parseInt(numRepOfChar), 36);
				System.out.println(ch);
				result += ch;
			}
		}
	}

}
