

package PolynomialProject; 

import java.util.*; 


public class Poly{
	private ArrayList<float[]> info;
	private ArrayList<Float> coefficients;
	private ArrayList<Float> exponents;

	// takes a polynomial inputted as a string in returns arraylist<int[]>
	// example: +5x7 +4x8 -3x-4
	public Poly(String polynomial){
	
		ArrayList<float[]> info = new ArrayList<float[]>();
		ArrayList<Float> coefficients = new ArrayList<Float>();
		ArrayList<Float> exponents = new ArrayList<Float>();

		String[] array = polynomial.split(" ");

		for (int i=0; i< array.length; i++){
			String[] subArray = array[i].split("x");
			float coefficient = Float.valueOf(subArray[0]);
			float exponent = Float.valueOf(subArray[1]);
			
			coefficients.add(coefficient);
			exponents.add(exponent);
			
			float[] combined = new float[2];
			combined[0] = coefficient;
			combined[1] = exponent;
			
			info.add(combined);
		}

		this.info = info;
		this.coefficients = coefficients;
		this.exponents = exponents;
	}
	public ArrayList<float[]> Polynomial(){
		return this.info;
	}
	public ArrayList<Float> getCoeffs(){
		return this.coefficients;
	}
	public ArrayList<Float> getExpos(){
		return this.exponents;
	}


}

// To Compile: javac -d bin PolynomialProject/*


