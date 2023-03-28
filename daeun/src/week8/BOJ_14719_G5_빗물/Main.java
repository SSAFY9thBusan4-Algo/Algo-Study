package week8.BOJ_14719_G5_빗물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String[] split = br.readLine().split(" ");
	    int W = Integer.parseInt(split[1]);
	    int[] rain = new int[W];
	    split = br.readLine().split(" ");
	    for(int i=0;i<W;i++) {
	 	   rain[i] = Integer.parseInt(split[i]);
	    }
	    
	    int left, right, result = 0;
	    for(int i=1;i<W-1;i++) {
	 	   left = 0;
	 	   right = 0;
	 	   for(int j=0;j<i;j++) {
	 		   left = Math.max(rain[j], left);
	 	   }
	 	   
	 	   for(int j=i;j<W;j++) {
	 		   right = Math.max(rain[j], right);
	 	   }
	 	   
	 	   if(rain[i]<left && rain[i]<right) { //작아야 물이 고인다.
	 		   result += Math.min(left, right) - rain[i];
	 	   }
	    }
	    
	    System.out.println(result);
	}
}