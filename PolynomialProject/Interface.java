package PolynomialProject; 

import java.util.*; 


public interface Interface<Poly>{


	// takes a polynomial and re-writes it in proper order using Merge Sort Algorithm
	// Example:  4x^2 + 2x + 8x^3   -->   8x^3 + 4x^2 + 2x
	public abstract Poly order(Poly f);


	// takes a polynomial and checks to see if components may be combined
	// Example: 5x^3 + 4x^2 + x^3 + 2x   -->   8x^3 + 4x^2 + 2x
	public abstract Poly reduce(Poly f);


	// takes two polynomials and returns the sum
	public abstract Poly add(Poly f, Poly g);


	// takes two polynomials and returns the difference
	public abstract Poly subtract(Poly f, Poly g);


	// takes two polynomials and returns the product
	public abstract Poly multiply(Poly f, Poly g);


	// computes f/g = h + r, where r is the remainder following the division algorithm
	// returns h and r
	public abstract Poly divide(Poly f, Poly g);


	// checks for largest common factor by the Euclidean Algorithm
	// returns constant polynomial "1" if no common divisors.			 ** hard **
	public abstract Poly commonDivisor(Poly f, Poly g);	


	// computes the derivative of a polynomial
	public abstract Poly differentiate(Poly f);


	// computes the integral of a polynomial given an intial condition for f(0)
	public abstract Poly integrate(Poly f, int f0);



	// determines approxiamte x-values of relative maxima and minima		** these 3 will be challenging **
	public abstract float[] extrema(Poly f);

	// uses extrema(f) to determine number of roots and a window to numerically approximate them
	public abstract float[] numRoots(Poly f);

	// uses extrema(f) and numRoots to approximate the roots using Newton's method
	public abstract float[] roots(Poly f);

}



