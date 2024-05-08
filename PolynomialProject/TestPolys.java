
package PolynomialProject; 

import java.util.*; 

//public class Operations<Poly> implements Interface<Poly>{


public class TestPolys {


	public static void main(String args[]){

		Operations ring = new Operations();
		
		Scanner scanner = new Scanner(System.in);

        
	    int command = 0;
	    while(command != 9){
	    	System.out.println("What would you like to do? Please type a number: \n1: Find Roots \n2: Differentiate Polynomial \n3: Integrate Polynomial  \n5: Multiply Polynomials \n6: Divide Polynomials \n3: Add polynomials \n4: Subtract Polynomials \n7: reduce polynomial \n9: exit");
	        String answer = scanner.nextLine();
	        command = Integer.valueOf(answer);
	        if (command == 9){
	        	break;
	        }
	        if (command != 9 && command != 1 && command != 2){
	        	System.out.println("Not implemented yet, sorry\n");
	        	continue;
	        }
	        if (command == 1){
	        	System.out.println("Input polynomial: (example: 1x^7 +3x^2 -4x^1 -1x^0)");
	        	String polynomial = scanner.nextLine();
	        	Poly f = new Poly(polynomial);
	        	System.out.println(ring.strRoots(f));
	        	System.out.println("\n0: continue \n9: exit");
	        	answer = scanner.nextLine();
	        	command = Integer.valueOf(answer);
	        	continue;
	        }
	        if (command == 2){
	        	System.out.println("Input polynomial: (example: 1x^7 +3x^2 -4x^1 -1x^0)");
	        	String polynomial = scanner.nextLine();
	        	Poly f = new Poly(polynomial);
	        	System.out.println("f(x) = " + f.toString() + "\nf'(x) = " + ring.diff(f).toString());
	        	System.out.println("\n0: continue \n9: exit");
	        	answer = scanner.nextLine();
	        	command = Integer.valueOf(answer);
	        	continue;
	        }
	    }

        scanner.close();


		//String testString = "+1x^4 +1x^2 +2x^1";
		//String testString = "+15x^4 -36x^2 +2x^1";
		//String testString = "+5x^4 -6x^2 +2x^1";
		
/**		String testString = "1x^6 -21x^5 +20.05x^4 +3x^3 -.25x^2 +4x^1 -12x^0";
		Poly f = new Poly(testString);
		System.out.println(ring.strRoots(f));
*/

/**		String testString = "+5x^4 -6x^2 +2x^1 +1x^4 +1x^2 +2x^1";
		String testString = "6x^2 +2x^1 +1x^4 +1x^3";
		Poly f = new Poly(testString);

		System.out.println(f.toString());
		ring.order(f);
		System.out.println(f.toString());
		ring.reduce(f);
		System.out.println(f.toString());
*/

        Poly testPoly1 = new Poly("3x^2 -6x^1 +3x^0");
        Poly testPoly2 = new Poly("2x^3 +9x^1 -20x^0");
        Poly zeroPoly = new Poly("0x^0");
        Poly testPoly3 = new Poly("1x^0");
        Poly testPoly4 = new Poly("3x^2 -2x^1 +1x^0");

        ring.reduce(zeroPoly);
        Poly[] output = ring.divide(testPoly4, testPoly3);
        System.out.println(output[0].toString());
        System.out.println("penis");
        System.out.println(output[1].toString());
	}


}


// To Compile: 
// javac -d bin PolynomialProject/*


// To Run: 
// java -cp bin PolynomialProject.TestPolys

