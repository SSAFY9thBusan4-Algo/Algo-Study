package week21.BOJ_2448_G4_별찍기11;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		String[] stars = new String[n];
		stars[0] = "  *  ";
		stars[1] = " * * ";
		stars[2] = "*****";
		
		for (int k=1; 3*Math.pow(2, k)<=n; k++) {
			makeStars(k-1, stars);
		}
		
		for (int i=0; i<n; i++) {
			System.out.println(stars[i]);
		}
	}

	private static void makeStars(int i, String[] stars) {
		
		int p = (int) (3*Math.pow(2, i));
		for (int r=p; r<p*2; r++) {
			stars[r] = stars[r-p]+" "+stars[r-p];
		}
		
		String s = "";
		for (int j=0; j<p; j++) s += " "; 
		
		for (int j=0; j<p; j++) {
			stars[j] = s+stars[j]+s;
		}
	}
	
}
