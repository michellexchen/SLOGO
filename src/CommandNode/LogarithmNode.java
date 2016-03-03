package commandNode;

import java.util.ArrayList;
import java.util.HashMap;

import exception.SLogoException;
import model.CharacterState;

public class LogarithmNode extends CommandNode{
	
	private int NUM_CHILDREN = 1;
	
	public LogarithmNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double num = getChildren().get(0).evaluate(state);
		String numToStr = num+"";
		int decimalIndex = numToStr.indexOf(".");
		int toSubtract = 1;
		if(decimalIndex != -1){
			toSubtract = 10 * (numToStr.length() - decimalIndex - 1);
			num *= toSubtract;
		}
		return logPrimeApprox(num) - logPrimeApprox(toSubtract);
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

}