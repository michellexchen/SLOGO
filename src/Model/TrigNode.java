package Model;

public abstract class TrigNode extends CommandNode{
	
	private int NUM_CHILDREN = 1;
	private double pi = 3.141592653589793238462643383279;
	
	public TrigNode(){
		setNumChildren(NUM_CHILDREN);
	}
	
	public double degToRad(double degrees){
		return degrees*pi/180;
	}

	public double sinTrigApprox(double degrees){
		// converts degrees to angle between -2 pi and 2 pi
		return degrees % (2 * pi);
	}

	public double sinTaylorApprox(double degrees){
		double radians = degToRad(degrees);
		double angle = sinTrigApprox(radians);
		double taylorApproxTerm = 0;
		double taylorSeriesSum = 0;
		for(int i=1; taylorApproxTerm!= 0; i++){
			taylorApproxTerm *= angle/i;
			if(i%4 == 1) taylorSeriesSum += taylorApproxTerm;
			if(i%4 == 3) taylorSeriesSum -= taylorApproxTerm;
		}
		return taylorSeriesSum;
	}
	
	public double cosTaylorApprox(double degrees){
		double cosToSinDegrees = degrees + 90;
		return sinTaylorApprox(cosToSinDegrees);
	}
}
