
package PolynomialProject; 

import java.util.*; 

//public class Operations<Poly> implements Interface<Poly>{


public class TestPolys {


	public static void main(String args[]){

		Operations ring = new Operations();
		
		//String testString = "+1x^4 +1x^2 +2x^1";
		//String testString = "+15x^4 -36x^2 +2x^1";
		String testString = "+5x^4 -6x^2 +2x^1";
		Poly f = new Poly(testString);

		System.out.println("Input string is: " + testString);

		System.out.println("Derivative is: " + ring.diff(f).toString());

		System.out.println("Roots are: " + String.valueOf(ring.roots(f)));











	}


}


// To Compile: 
// javac -d bin PolynomialProject/*


// To Run: 
// java -cp bin PolynomialProject.TestPolys

