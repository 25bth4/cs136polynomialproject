
package PolynomialProject; 

import java.util.*; 

//public class Operations<Poly> implements Interface<Poly>{


public class TestPolys {


	public static void main(String args[]){
		System.out.println("Test");
		
		String testString = "2x^5 +4x^3 +17x^1";
		Poly f = new Poly(testString);

		String result = "";
		for (int i=0; i< f.getPoly().size(); i++){
			String mini = "";
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
		System.out.println(result);

		System.out.println(f.toString());







	}


}


// To Compile: 
// javac -d bin PolynomialProject/*


// To Run: 
// java -cp bin PolynomialProject.TestPolys

