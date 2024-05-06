
package PolynomialProject; 

import java.util.*; 

//public class Operations<Poly> implements Interface<Poly>{
public class Operations implements Interface<Poly>{

	// takes a polynomial and returns the string represetnation
	public String toString(Poly f){
		String result = "";
		for (int i=0; i< f.getPoly().size(); i++){
			String coeff = String.valueOf(f.getPoly().get(i)[0]);
			String expon = String.valueOf(f.getPoly().get(i)[1]);
			if (f.getPoly().get(i)[0]>0) coeff = "+"+coeff;
			expon = "^"+expon;
			if (f.getPoly().get(i)[1]==1) result += coeff +"x";
			else {
				result += coeff +"x" +expon;
			}
			result += " ";
		}
		return result;
	}


	// takes a polynomial and re-writes it in proper order using Merge Sort Algorithm
	// Example:  4x^2 + 2x + 8x^3   -->   8x^3 + 4x^2 + 2x
	public Poly order(Poly f){
		return null;
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


	// computes the derivative of a polynomial
	public Poly differentiate(Poly f){
		return null;
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




