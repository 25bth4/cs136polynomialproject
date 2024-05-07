

package PolynomialProject; 

import java.util.*; 


public class Poly{
	private ArrayList<float[]> info;
	private ArrayList<Float> extrema;
	private ArrayList<Float> roots;

	// takes a polynomial inputted as a string in returns arraylist<int[]>
	// example: +5x^7 +4x^8 -3x^-4
	public Poly(String polynomial){
	
		ArrayList<float[]> info = new ArrayList<float[]>();
		ArrayList<Float> extrema = new ArrayList<Float>();
		ArrayList<Float> roots = new ArrayList<Float>();

		String[] array = polynomial.split(" ");

		for (int i=0; i< array.length; i++){
			String[] subArray = array[i].split("x");
			float coefficient = Float.valueOf(subArray[0]);
			float exponent = Float.valueOf(subArray[1].substring(1,subArray[1].length()));
			
			float[] combined = new float[2];
			combined[0] = coefficient;
			combined[1] = exponent;
			
			info.add(combined);
		}

		this.info = info;
		this.extrema = extrema;
		this.roots = roots;
	}


    public String toString() {
        
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

	public ArrayList<float[]> getPoly(){
		return this.info;
	}
	public ArrayList<Float> getExtrema(){
		return this.extrema;
	}
	public ArrayList<Float> getRoots(){
		return this.roots;
	}


}

// To Compile: javac -d bin PolynomialProject/*



