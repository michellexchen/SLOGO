package Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import Controller.SLogoException;

public class MathNode extends CommandNode{

	private ArrayList<Node> children = new ArrayList<Node>();
	private String commandType;
	private CharacterState state;
	private double pi = 3.141592653589793238462643383279;
	
	public void setType(String type){
		commandType = type;
	}
	
	public double evaluate(CharacterState state) throws SLogoException{
		this.state = state;
		Class thisClass = null;
		try {
			thisClass = Class.forName("Model.MathNode");
		} catch (ClassNotFoundException e) {
			throw new SLogoException("Class not implemented");
		}
		Method method = null;
		try {
			method = thisClass.getMethod(commandType, new Class[] {});
		} catch (NoSuchMethodException | SecurityException e) {
			throw new SLogoException("Method " + commandType+ " not implemented");
		}
		try {
			return (double) method.invoke(this, new Object[] {});
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new SLogoException("Math method not implemented");
		}
	}
	
	public double sum() throws SLogoException{
		double result = 0;
		for(int x=0; x<children.size(); x++){
			result += children.get(x).evaluate(state);
		}
		return result;
	}
	
	public double difference() throws SLogoException{
		double result = children.get(0).evaluate(state);
		for(int x=1; x<children.size(); x++){
			result -= children.get(x).evaluate(state);
		}
		return result;
	}
	
	public double product() throws SLogoException{
		double result = 1;
		for(int x=0; x<children.size(); x++){
			result += children.get(x).evaluate(state);
		}
		return result;
	}
	
	public double quotient() throws SLogoException{
		double result = children.get(0).evaluate(state);
		for(int x=0; x<children.size(); x++){
			result /= children.get(x).evaluate(state);
		}
		return result;
	}
	
	public double remainder() throws SLogoException{
		double result = children.get(0).evaluate(state);
		for(int x=0; x<children.size(); x++){
			result %= children.get(x).evaluate(state);
		}
		return result;
	}
	
	public double minus() throws SLogoException{
		return -1*sum();
	}
	
	public double random() throws SLogoException{
		double result = children.get(0).evaluate(state);
		if(result < 0){
			return 0;
		}
		return result;
	}
	
	private double degToRad(double degrees){
		return degrees*pi/180;
	}
	
	private double sinTrigApprox(double degrees){
		// converts degrees to angle between -2 pi and 2 pi
		return degrees % (2 * pi);
	}
	
	double sinTaylorApprox(double degrees){
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
	
	double cosTaylorApprox(double degrees){
		double cosToSinDegrees = degrees + 90;
		return sinTaylorApprox(cosToSinDegrees);
	}
	
	public double sin() throws SLogoException{
		// for 1 < X < 1, sin(x) = x - x^3/3! + x^5/5! - x^7/7! + ...
		double degrees = children.get(0).evaluate(state);
		return sinTaylorApprox(degrees);
	}
	
	public double cos() throws SLogoException{
		double degrees = children.get(0).evaluate(state);
		return cosTaylorApprox(degrees);
	}
	
	public double tan() throws SLogoException{
		double degrees = children.get(0).evaluate(state);
		return sinTaylorApprox(degrees) / cosTaylorApprox(degrees);
	}
	
	public double atan() throws SLogoException{
		// For for -2pi < X < 2pi, atan(x) = x - x^3/3 + x^5/5 - x^7/7 + ...
		double radians = degToRad(children.get(0).evaluate(state));
		if(radians<1 || radians>1){
			throw new SLogoException("Arctan(x) domain is [-1, 1]");
		}
		double taylorSeriesSum = 0;
		for(int i=1; i>0; i++){
			double taylorApproxTerm = 1;
			double prod = 1;
			for(int x=0; x<i; x++){
				prod *= -1;
			}
			taylorApproxTerm *= prod;
			prod = 1;
			for(int x=0; x<2*i+1; x++){
				prod *= radians;
			}
			taylorApproxTerm *= prod;
			taylorApproxTerm /= (2*i+1);
			taylorSeriesSum += taylorApproxTerm;
		}
		return taylorSeriesSum;
	}
	
	private ArrayList<Integer> primeFactorize(double num){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i=2; i*i <= num; i++){
			while(num % i == 0){
				primes.add(i);
				num = num / i;
			}
		}
		return primes;
	}
	
	private HashMap<Integer, Double> getLnApproximations(){
		HashMap<Integer, Double> lnPrimeMap = new HashMap<Integer, Double>();
		lnPrimeMap.put(1, 0.0);
		lnPrimeMap.put(2, 0.6931);
		lnPrimeMap.put(3, 1.0986);
		lnPrimeMap.put(5, 1.6094);
		lnPrimeMap.put(7, 1.9459);
		lnPrimeMap.put(9, 2.1972);
		lnPrimeMap.put(11, 2.3978);
		lnPrimeMap.put(13, 2.5649);
		lnPrimeMap.put(15, 2.708);
		lnPrimeMap.put(17, 2.8332);
		lnPrimeMap.put(19, 2.9444);
		lnPrimeMap.put(23, 3.13549);
		lnPrimeMap.put(27, 3.2958);
		return lnPrimeMap;
	}
	
	private double logPrimeApprox(double num){
		HashMap<Integer, Double> lnPrimeMap = getLnApproximations();
		ArrayList<Integer> primeList = primeFactorize(num);
		if(primeList.size() == 0){
			while(primeList.size() == 0){
				num++;
				primeList = primeFactorize(num);
			}
		}
		double sum = 0;
		for(int x=0; x<primeList.size(); x++){
			sum += lnPrimeMap.get(primeList.get(x));
		}
		return sum;
	}
	
	public double log() throws SLogoException{
		double num = children.get(0).evaluate(state);
		String numToStr = num+"";
		int decimalIndex = numToStr.indexOf(".");
		int toSubtract = 1;
		if(decimalIndex != -1){
			toSubtract = 10 * (numToStr.length() - decimalIndex - 1);
			num *= toSubtract;
		}
		return logPrimeApprox(num) - logPrimeApprox(toSubtract);
	}
	
	public double pi() throws SLogoException{
		return pi;
	}
	
	public double pow() throws SLogoException{
		double base = children.get(0).evaluate(state);
		double pow = 1;
		double exponent = children.get(1).evaluate(state);
		for(int x=0; x<exponent; x++){
			pow *= base;
		}
		return pow;
	}
	
	public String toString(){
		return commandType;
	}
}