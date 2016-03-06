package Model;

import java.util.Observable;

public class SLogoVariable extends Observable {

	private String myName;
	private double myValue;

	public SLogoVariable() {
		// class Pair {
		// String variable;
		// double value;
	}

	public SLogoVariable(String name, double value) {
		myName = name;
		myValue = value;
	}

	public void addListeners() {

	}

	//////////////////////////////
	///// Getters and Setters//////
	//////////////////////////////

	/**
	 * @return the myName
	 */
	public String getName() {
		return myName;
	}

	/**
	 * @param myName
	 *            the myName to set
	 */
	public void setName(String myName) {
		this.myName = myName;
	}

	/**
	 * @return the myValue
	 */
	public double getValue() {
		return myValue;
	}

	/**
	 * @param myValue
	 *            the myValue to set
	 */
	public void setValue(double myValue) {
		this.myValue = myValue;
	}

	// @Override
	// public void addListener(InvalidationListener arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void removeListener(InvalidationListener arg0) {
	// // TODO Auto-generated method stub
	//
	// }
}
