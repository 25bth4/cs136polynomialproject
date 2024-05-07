
package PolynomialProject; 

import java.util.*; 

//public class Operations<Poly> implements Interface<Poly>{


public class TestPolys {


	public static void main(String args[]){

		Operations ring = new Operations();
		
		String testString = "37x^7 -2040x^2 +17x^-3";
		Poly f = new Poly(testString);

		System.out.println("Input string is: " + testString);

		System.out.println("ToString output is: " + f.toString());

		System.out.println("Derivative is: " + ring.diff(f).toString());







	}


}


// To Compile: 
// javac -d bin PolynomialProject/*


// To Run: 
// java -cp bin PolynomialProject.TestPolys

