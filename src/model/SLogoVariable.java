package model;

import java.util.Observable;

/**
 * SLogo Variable class that represents variable objects
 *
 */

public class SLogoVariable extends Observable {

	private String myName;
	private double myValue;

	public SLogoVariable(String myName, double myValue) {
		this.myName = myName;
		this.myValue = myValue;
	}

	public void addListeners() {

	}

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
	 * @param myValue the myValue to set
	 */
	public void setValue(double myValue) {
		this.myValue = myValue;
	}

}