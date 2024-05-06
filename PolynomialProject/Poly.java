

package PolynomialProject; 

import java.util.*; 


public class Poly{
	private ArrayList<int[]> info;
	private ArrayList<Integer> coefficients;
	private ArrayList<Integer> exponents;

	// takes a polynomial inputted as a string in returns arraylist<int[]>
	// example: +5x7 +4x8 -3x-4
	public Poly(String polynomial){
	
		ArrayList<int[]> info = new ArrayList<int[]>();
		ArrayList<Integer> coefficients = new ArrayList<Integer>();
		ArrayList<Integer> exponents = new ArrayList<Integer>();

		String[] array = polynomial.split(" ");

		for (int i=0; i< array.length; i++){
			String[] subArray = array[i].split("x");
			int coefficient = Integer.valueOf(subArray[0]);
			int exponent = Integer.valueOf(subArray[1]);
			
			coefficients.add(coefficient);
			exponents.add(exponent);
			
			int[] combined = new int[2];
			combined[0] = coefficient;
			combined[1] = exponent;
			
			info.add(combined);
		}

		this.info = info;
		this.coefficients = coefficients;
		this.exponents = exponents;
	}
	public ArrayList<int[]> Polynomial(){
		return this.info;
	}
	public ArrayList<Integer> getCoeffs(){
		return this.coefficients;
	}
	public ArrayList<Integer> getExpos(){
		return this.exponents;
	}


}

// javac -d bin PolynomialProject/Poly.java


