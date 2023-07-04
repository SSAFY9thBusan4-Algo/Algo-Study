package week19.BOJ_22945_G4_팀빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] xarr = new int[N];
		xarr = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		
		int l=0,r=N-1;
		
		int result = 0;
		
		while(l-r!=1) {
			
			int x = (r-l-1) * Math.min(xarr[l], xarr[r]);
			result = Math.max(result, x);
			
			if (xarr[l]<=xarr[r]) l++;
			else r--;
			
		}
		
		System.out.println(result);
		
	}
	
}
