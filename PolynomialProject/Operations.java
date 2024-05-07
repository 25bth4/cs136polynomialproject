
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
	public void reduce(Poly f){
		return null;
	}


	// takes two polynomials and returns the sum
	public Poly add(Poly f, Poly g){

		/*String newString = f.toString() + " " + g.toString();
		Poly newPoly = new Poly(newString);
		reduce(newPoly);

		return newPoly;*/
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
	public ArrayList<Float> extrema(Poly f){
//make sure that roots(f) spits out the roots in ascending order (sorted already).
		ArrayList<Float> extrema = roots(diff(f));
		System.out.println("extrema: " + extrema.toString());
		f.getExtrema().clear();
		for (int i=0; i<extrema.size(); i++){
			f.getExtrema().add(extrema.get(i));
		}

//A: if (leading coeff >0 && leading exponent even) --> left of first >0, right of last >0
//B: if (leading coeff >0 && leading exponent odd) --> left of first <0, right of last >0
//C: if (leading coeff <0 && leading exponent even) --> left of first <0, right of last <0
//D: if (leading coeff <0 && leading exponent odd) --> left of first >0, right of last <0

		return f.getExtrema();
	}




// uses extrema(f) to determine number of roots and a window to numerically approximate them
	public ArrayList<float[]> windowRoots(Poly f){
		//largeNum is used to test the value of f at positive and negative 'infinity'
		
		extrema(f);

		ArrayList<float[]> windows = new ArrayList<float[]>();
		
		float largeNum = 0;
		for(int i=0; i<f.getPoly().size(); i++){
			largeNum = largeNum * f.getPoly().get(i)[0];
		}
		largeNum = Math.abs(largeNum)+4;
		largeNum = 1000;
//if f<0 at -infty, and first extrema is positive, then there is a zero before that extrema. similar for last extrema.
//if two neighboring extrema are of oposite sign, then there is a zero between them.
		

		float firstExtrema = f.getExtrema().get(0);
		if ((evaluate(f,firstExtrema))/Math.abs(evaluate(f,firstExtrema)) != evaluate(f,-largeNum)/Math.abs(evaluate(f,-largeNum)) ){		//if they are different signs
			float[] window = new float[2];
			window[0] = -largeNum;
			window[1] = firstExtrema;
			windows.add(window);			
		}
		int n = f.getExtrema().size();
		for (int i=1; i<n; i++){
			float right = f.getExtrema().get(i);
			float left = f.getExtrema().get(i-1);
			if ((evaluate(f,left))/Math.abs(evaluate(f,left)) !=  (evaluate(f,right))/Math.abs(evaluate(f,right))){
				float[] window = new float[2];
				window[0] = left;
				window[1] = right;
				windows.add(window);
			}
		}
		float lastExtrema = f.getExtrema().get(n-1);
		if ((evaluate(f,lastExtrema))/Math.abs(evaluate(f,lastExtrema)) != evaluate(f,largeNum)/Math.abs(evaluate(f,largeNum)) ){		//if they are different signs
			float[] window = new float[2];
			window[0] = lastExtrema;
			window[1] = largeNum;
			windows.add(window);
		}

		return windows;
	}

	// uses extrema(f) and numRoots to approximate the roots using Newton's method
	public ArrayList<Float> roots(Poly f){

//base case, if the degree of the polynomial is 1, find the root
		if (f.getPoly().get(0)[1]==1){
			float root = -f.getPoly().get(1)[0]/f.getPoly().get(0)[0];
			f.getRoots().add(root);
			return f.getRoots();
		}

		 ArrayList<float[]> windows = windowRoots(f);
		 ArrayList<Float> zeros = new ArrayList<Float>();
		 //Bisection METHOD TO APPROXIMATE THE ROOTS GIVEN THE WINDOWS

//f.getRoots().clear();
		 for (int i=0; i< windows.size(); i++){
		 	
		 	float root = bisectionMethod(windows.get(i)[0],windows.get(i)[1], f, 0);
		 
		 	zeros.add(root);
// 	f.getRoots().add(root);
		 }

		 	return zeros;
//	return f.getRoots();
	}

	public float bisectionMethod(float a, float b, Poly f, int count){
		if (count ==1000) return (a+b)/2;
		if (Math.abs(evaluate(f, (a+b)/2)) < Math.pow(10,-8)){
			return (a+b)/2;
		}
		if (evaluate(f, (a+b)/2)/Math.abs(evaluate(f, (a+b)/2)) == evaluate(f, a)/Math.abs(evaluate(f,a)) ){
			return bisectionMethod((a+b)/2, b, f, count+1);
		}
		return bisectionMethod(a, (a+b)/2, f, count +1);
	}



	public float evaluate(Poly f, float x){
		float result = 0;
		for (int i=0; i<f.getPoly().size(); i++){
			result += f.getPoly().get(i)[0]*(Math.pow(x, f.getPoly().get(i)[1]));
		}
		return result;
	}



	public static void main(String args[]){

	}



}




