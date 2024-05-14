
package PolynomialProject; 

import java.util.*; 

//public class Operations<Poly> implements Interface<Poly>{
public class Operations implements Interface<Poly>{


	// takes a polynomial and re-writes it in proper order using Merge Sort Algorithm
	// Example:  4x^2 + 2x + 8x^3   -->   8x^3 + 4x^2 + 2x
	public void order(Poly f){
		ArrayList<float[]> newPoly = mergeSort(f.getPoly());
		f.getPoly().clear();
		for (int i=0; i<newPoly.size(); i++){
			f.getPoly().add(newPoly.get(i));
		}
	}

	public ArrayList<float[]> mergeSort(ArrayList<float[]> array){
		if (array.size() <=1){
			return array;
		}
		int n = array.size();
		int mid = (int) n/2;

		ArrayList<float[]> left = new ArrayList<float[]>();
		for (int i=0; i< mid; i++){
			left.add(array.get(i));
		}
		ArrayList<float[]> right = new ArrayList<float[]>();
		for (int i=mid; i< n; i++){
			right.add(array.get(i));
		}

		left = mergeSort(left);
		right = mergeSort(right);

		return merge(left, right);
	}


	public ArrayList<float[]> merge(ArrayList<float[]> left, ArrayList<float[]> right){
		ArrayList<float[]> result = new ArrayList<float[]>();
		int leftIndex = 0;
		int rightIndex = 0;

		while (leftIndex< left.size() && rightIndex < right.size()){
			if (left.get(leftIndex)[1] > right.get(rightIndex)[1]){
				result.add(left.get(leftIndex));
				leftIndex += 1;
			}
			else {
				result.add(right.get(rightIndex));
				rightIndex += 1;
			}
		}
		while (leftIndex < left.size()){
			result.add(left.get(leftIndex));
			leftIndex += 1;
		}
		while (rightIndex < right.size()){
			result.add(right.get(rightIndex));
			rightIndex += 1;
		}
		return result;

	}


	// takes a polynomial and checks to see if components may be combined
	// Example: 5x^3 + 4x^2 + 3x^3 + 2x   -->   8x^3 + 4x^2 + 2x
	public void reduce(Poly f){

		// Checks if the polynomial is not zero
		boolean check = false;
		for (int i = 0; i < f.getPoly().size(); i++){
			if (f.getPoly().get(i)[0] != 0.0){
				check = true;
			}
		}
		
		if (check && f.getPoly().size() >= 2){
			//First order f, then reduce it.
			order(f);
			int n = f.getPoly().size();
			float prevexpo = f.getPoly().get(0)[1];

			for (int i=1; i<n; i++){
				if (prevexpo == f.getPoly().get(i)[1]){
					f.getPoly().get(i-1)[0] += f.getPoly().get(i)[0];
					f.getPoly().remove(i);
					n -= 1;
					i -=1;
				}
				else{
					prevexpo = f.getPoly().get(i)[1];
				}
			}
			for (int i = 0; i < f.getPoly().size(); i++){
				if (f.getPoly().get(i)[0] == 0.0){
					f.getPoly().remove(i);
				}
			}
		}


		else if (!check){
			float[] floatArray = {0.0f, 0.0f};
			f.getPoly().clear();
			f.getPoly().add(floatArray);
		}


		


	}


	// takes two polynomials and returns the sum
	public Poly add(Poly f, Poly g){

		String newString = f.toString() + g.toString();
		Poly newPoly = new Poly(newString);
		// reduce begins by ordering the polynomial, so we don't have to call both.
		reduce(newPoly);
		return newPoly;
	}


	// takes two polynomials and returns the difference
	public Poly subtract(Poly f, Poly g){
		Poly temp = new Poly(g.toString());
		for (int i = 0; i < temp.getPoly().size(); i++){
			temp.getPoly().get(i)[0] = 0 - temp.getPoly().get(i)[0];
		}
		Poly output = add(f, temp);
		reduce(output);

		return output;
	}


	// takes two polynomials and returns the product
	public Poly multiply(Poly f, Poly g){

		ArrayList<float[]> traverse = f.getPoly();

		// initialize return polynomial
		Poly newPoly = new Poly("0x^0");

		

		for(int i = 0; i < traverse.size(); i++){
			// values for multiplication
			float coeff = traverse.get(i)[0];
			float expon = traverse.get(i)[1];

			// temporary polynomial cloned from g
			Poly temp = new Poly(g.toString());
			int tempSize = temp.getPoly().size();

			// multiply each part of g by the selected part of f
			for (int j = 0; j < tempSize; j++){
				temp.getPoly().get(j)[0] = temp.getPoly().get(j)[0] * coeff;
				temp.getPoly().get(j)[1] += expon;
			}
			newPoly = add(temp, newPoly);
		}

		return newPoly;
	}


	// computes f/g = h + r, where r is the remainder following the division algorithm
	// The output is formatted as an array [h, r]
	// assume f is of equal or higher degree than g

	public Poly[] divideHelper(Poly f, Poly g, Poly h){

		if (g.getPoly().get(0)[1] <= f.getPoly().get(0)[1] && f.getPoly().get(0)[0] != 0){
			Poly x = new Poly("0x^0");
			x.getPoly().get(0)[0] =  f.getPoly().get(0)[0]/g.getPoly().get(0)[0];
			x.getPoly().get(0)[1] = f.getPoly().get(0)[1] - g.getPoly().get(0)[1];
			h = add(x, h);
			reduce(h);
			
			Poly newPoly = subtract(f, multiply(g, x));
			reduce(newPoly);

			return divideHelper(newPoly, g, h);
		}

		Poly[] output = {h, f};
		return output;

	}

	// f/g
	public Poly[] divide(Poly f, Poly g){


		// must have polynomials in reduced form. This ensures that
		reduce(f);
		reduce(g);

		Poly h = new Poly("0x^0");

		return divideHelper(f, g, h);

	}


	// checks for largest common factor by the Euclidean Algorithm
	// returns constant polynomial "1" if no common divisors.			 ** hard **
	public Poly commonDivisor(Poly f, Poly g){
		reduce(f);
		reduce(g);
		Poly a;
		Poly b;

		// defines rank
		if (f.getPoly().get(0)[1] <= g.getPoly().get(0)[1]){
			a = f;
			b = g;
		}
		else{
			a = g;
			b = f;
		}

		// Checks for zero polynomials
		if (b.getPoly().get(0)[0] == 0f){
			Poly h = new Poly("1x^0");
			return h;
		}
		else if (a.getPoly().get(0)[0] == 0f){
			return b;
		}

		Poly h = divide(b,a)[1];
		Poly j = divide(b,a)[0];
		reduce(h);
		if (h.getPoly().get(0)[0] == 0){
			ArrayList<Float> coeffs = new ArrayList<Float>();
			if (j.getPoly().size() > 1){

				for (int i = 0; i < a.getPoly().size(); i++){
					coeffs.add(a.getPoly().get(i)[0]);
				}
				float commonCoeff = coeffs.get(0);
				for (int i = 0; i < coeffs.size(); i++){
					commonCoeff = euclidean(commonCoeff, coeffs.get(i));
				}
				if(commonCoeff != 1f){
					String term = commonCoeff + "x^0";
					Poly commonPoly = new Poly(term);
					a = divide(a, commonPoly)[0];
					reduce(a);
				}
			}
			return a;
		}

		return commonDivisor(h,a);
	}	

	public float euclidean(float a, float b){
		if (b == 0f) return a;

		return euclidean(b, a%b);
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
		Poly r = new Poly(f.toString());
		for (int i=0; i<f.getPoly().size(); i++){
			r.getPoly().get(i)[1] +=1;
			r.getPoly().get(i)[0] = f.getPoly().get(i)[0]/r.getPoly().get(i)[1];
		}
		float[] newArray = new float[2];
		newArray[0] = f0;
		newArray[1] = 0;
		r.getPoly().add(newArray);

		return r;
	}







	// determines approxiamte x-values of relative maxima and minima		** these 3 will be challenging **
	public ArrayList<Float> extrema(Poly f){
//make sure that roots(f) spits out the roots in ascending order (sorted already).
		ArrayList<Float> extrema = roots(diff(f));
//		System.out.println("extrema of " + f.toString() + " is " + extrema.toString());

		for (int i=0; i<extrema.size(); i++){
			f.getExtrema().add(extrema.get(i));
		}

		return f.getExtrema();
	}









// uses extrema(f) to determine number of roots and a window to numerically approximate them
	public ArrayList<float[]> windowRoots(Poly f){
		//largeNum is used to test the value of f at positive and negative 'infinity'
		
		extrema(f);

		ArrayList<float[]> windows = new ArrayList<float[]>();
		
		//float largeNum = 0;
	/**	for(int i=0; i<f.getPoly().size(); i++){
			largeNum = largeNum * f.getPoly().get(i)[0];
		}
		largeNum = Math.abs(largeNum);
	*///	largeNum = 1000000;
//if f<0 at -infty, and first extrema is positive, then there is a zero before that extrema. similar for last extrema.
//if two neighboring extrema are of oposite sign, then there is a zero between them.
		float firstExpon = f.getPoly().get(0)[1];
		float firstCoeff = f.getPoly().get(0)[0];
		int parity = (int)firstExpon % 2; //0 if even, 1 if odd
		float direction = firstCoeff/Math.abs(firstCoeff); //1 if positive, -1 if negative
		float xtoNegInfty = 0;
		float xtoPosInfty = 0;
		if (parity == 0){
			if(direction == 1){
				xtoNegInfty = 1;
				xtoPosInfty = 1;
			}
			if(direction == -1){
				xtoNegInfty = -1;
				xtoPosInfty = -1;
			}
		}
		if (parity == 1){
			if(direction == 1){
				xtoNegInfty = -1;
				xtoPosInfty = 1;
			}
			if(direction == -1){
				xtoNegInfty = 1;
				xtoPosInfty = -1;
			}
		}
		

		try{
			float firstExtrema = f.getExtrema().get(0);
			if ((evaluate(f,firstExtrema))/Math.abs(evaluate(f,firstExtrema)) != xtoNegInfty){		//if they are different signs
				float[] window = new float[2];
				float spot = firstExtrema;
				while (evaluate(f, spot)/Math.abs(evaluate(f,spot)) != xtoNegInfty){
					spot = spot -100;
				}
				window[0] = spot;
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
			if ((evaluate(f,lastExtrema))/Math.abs(evaluate(f,lastExtrema)) != xtoPosInfty ){		//if they are different signs
				float[] window = new float[2];
				float spot = lastExtrema;
				while (evaluate(f, spot)/Math.abs(evaluate(f,spot)) != xtoPosInfty){
					spot = spot +100;
				}
				window[0] = lastExtrema;
				window[1] = spot;
				windows.add(window);
			}

			return windows;
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("\nThe derivatives are too stiff, please try another function");
			return null;
		}
	}




	// uses extrema(f) and numRoots to approximate the roots using Newton's method
	public ArrayList<Float> roots(Poly f){

//base case, if the degree of the polynomial is 1, find the root
		if (f.getPoly().get(0)[1]==1){
			if (f.getPoly().size() > 1){
				float root = -f.getPoly().get(1)[0]/f.getPoly().get(0)[0];
				f.getRoots().add(root);
				return f.getRoots();
			}
			else {
				float root = 0;
				f.getRoots().add(root);
				return f.getRoots();
			}
			
		}

		 ArrayList<float[]> windows = windowRoots(f);
		
		 String print = "[";
		 for (int i=0;i<windows.size();i++){
		 	print += "[" + String.valueOf(windows.get(i)[0]) + "," + String.valueOf(windows.get(i)[1] + "], ");
		 }
		 print+= "]";

//		 System.out.println("Windows of " + f.toString() + " is " + print);
		 ArrayList<Float> zeros = new ArrayList<Float>();
		 //Bisection METHOD TO APPROXIMATE THE ROOTS GIVEN THE WINDOWS

//f.getRoots().clear();
		for (int i=0; i< windows.size(); i++){
		 	
		 	float root = bisectionMethod(windows.get(i)[0],windows.get(i)[1], f, 0);
		 
		 	zeros.add(root);

 	f.getRoots().add(root);
		 }
//		 System.out.println("Roots of " + f.toString() + " are " + zeros.toString());
//		 System.out.println("___");
		 	//return zeros;
	return f.getRoots();
	}

	public String strRoots(Poly f){
		ArrayList<Float> zeros = roots(f);

		if (zeros.size() == 0){
			return "This polynomial has no roots.";
		}
		return "Roots of "+ f.toString() + " are: \n" +zeros.toString();

	}



	public float bisectionMethod(float a, float b, Poly f, int count){
		if (count ==9000) return (a+b)/2;
		if (Math.abs(evaluate(f, (a+b)/2)) < Math.pow(10,-8)){
			return (a+b)/2;
		}
		if (evaluate(f, (a+b)/2)/Math.abs(evaluate(f, (a+b)/2)) == evaluate(f, a)/Math.abs(evaluate(f,a)) ){
			return bisectionMethod((a+b)/2, b, f, count+1);
		}
		if (evaluate(f, (a+b)/2)/Math.abs(evaluate(f, (a+b)/2)) == evaluate(f, b)/Math.abs(evaluate(f,b)) ){
			return bisectionMethod(a, (a+b)/2, f, count+1);
		}
		return (a+b)/2;
	}






	public float evaluate(Poly f, float x){
		float result = 0;
		for (int i=0; i<f.getPoly().size(); i++){
			result += f.getPoly().get(i)[0]*(Math.pow(x, f.getPoly().get(i)[1]));
		}
		return result;
	}


	// plot a set of polynomials on a graph in different colors
	public void graph(ArrayList<Poly> functions){

	}


	// produces a minimal polynomial with specified roots
	public Poly findPolynomial(ArrayList<Float> roots){
		return null;
	}



	public static void main(String args[]){

	}


}




