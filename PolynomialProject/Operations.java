
package PolynomialProject; 

import java.util.*; 

//public class Operations<Poly> implements Interface<Poly>{
public class Operations implements Interface<Poly>{


	// takes a polynomial and re-writes it in proper order using Merge Sort Algorithm
	// Example:  4x^2 + 2x + 8x^3   -->   8x^3 + 4x^2 + 2x
	public Poly order(Poly f){
		f = reduce(f);

		//code for Merge Sort

		return f;
	}


	// takes a polynomial and checks to see if components may be combined
	// Example: 5x^3 + 4x^2 + 3x^3 + 2x   -->   8x^3 + 4x^2 + 2x
	public Poly reduce(Poly f){
		return null;
	}


	// takes two polynomials and returns the sum
	public Poly add(Poly f, Poly g){
		return null;
	}


	// takes two polynomials and returns the difference
	public Poly subtract(Poly f, Poly g){
		return null;
	}


	// takes two polynomials and returns the product
	public Poly multiply(Poly f, Poly g){
		return null;
	}


	// computes f/g = h + r, where r is the remainder following the division algorithm
	// returns h and r
	public Poly divide(Poly f, Poly g){
		return null;
	}


	// checks for largest common factor by the Euclidean Algorithm
	// returns constant polynomial "1" if no common divisors.			 ** hard **
	public Poly commonDivisor(Poly f, Poly g){
		return null;
	}	


	public String toString(ArrayList<float[]> info) {
    	String result = "";
		for (int i=0; i< info.size(); i++){

			String coeff = String.valueOf(info.get(i)[0]);
			String expon = String.valueOf(info.get(i)[1]);

			if (info.get(i)[0] == (int)info.get(i)[0]) coeff = String.valueOf((int) info.get(i)[0]);
			if (info.get(i)[1] == (int)info.get(i)[1]) expon = String.valueOf((int) info.get(i)[1]);

			if (info.get(i)[0]>0) coeff = "+"+coeff;
			expon = "^"+expon;
			result += coeff +"x" +expon;
			result += " ";
		}
		return result;
    }

	// computes the derivative of a polynomial
	public Poly diff(Poly f){
	//	f = order(f);
		ArrayList<float[]> result = new ArrayList<float[]>();
		
		for(int i=0; i<f.getPoly().size(); i++){
			float[] newArray = new float[2];
			newArray[0] = f.getPoly().get(i)[0]*f.getPoly().get(i)[1];
			newArray[1] = f.getPoly().get(i)[1] - 1;
			result.add(newArray);
		}
		Poly fprime = new Poly(toString(result));

		return fprime;
	}


	// computes the integral of a polynomial given an intial condition for f(0)
	public Poly integrate(Poly f, float f0){
		return null;
	}



	// determines approxiamte x-values of relative maxima and minima		** these 3 will be challenging **
	public float[] extrema(Poly f){
		return null;
	}

	// uses extrema(f) to determine number of roots and a window to numerically approximate them
	public float[] numRoots(Poly f){
		return null;
	}

	// uses extrema(f) and numRoots to approximate the roots using Newton's method
	public float[] roots(Poly f){
		return null;
	}



	public static void main(String args[]){

	}



}




