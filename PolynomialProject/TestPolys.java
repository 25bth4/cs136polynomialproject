
package PolynomialProject; 

import java.util.*; 

//public class Operations<Poly> implements Interface<Poly>{


public class TestPolys {


	public static void main(String args[]){

		Operations ring = new Operations();
		


		//String testString = "+1x^4 +1x^2 +2x^1";
		//String testString = "+15x^4 -36x^2 +2x^1";
		//String testString = "+5x^4 -6x^2 +2x^1";
		
/**		String testString = "1x^6 -21x^5 +20.05x^4 +3x^3 -.25x^2 +4x^1 -12x^0";
		Poly f = new Poly(testString);
		System.out.println(ring.strRoots(f));
*/

		String testString = "+5x^4 -6x^2 +2x^1 +1x^4 +1x^2 +2x^1";
		//String testString = "6x^2 +2x^1 +1x^4 +1x^3";
		Poly f = new Poly(testString);

		System.out.println(f.toString());
		ring.order(f);
		System.out.println(f.toString());
		ring.reduce(f);
		System.out.println(f.toString());












	}


}


// To Compile: 
// javac -d bin PolynomialProject/*


// To Run: 
// java -cp bin PolynomialProject.TestPolys

