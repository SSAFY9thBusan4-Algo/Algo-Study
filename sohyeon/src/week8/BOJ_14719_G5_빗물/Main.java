package week8.BOJ_14719_G5_빗물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = in.readLine().split(" ");
		
		int result = 0;
		
		int H = Integer.parseInt(input[0]);
		int W = Integer.parseInt(input[1]);
		
		int[] list = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		
		for (int i=1; i<W-1; i++) {
			int right = 0;
			int left = 0;
			for (int r=i+1; r<W; r++) {
				right = Math.max(right, list[r]);
			}
			for (int l=0; l<i; l++) {
				left = Math.max(left, list[l]);
			}
			result += (left > list[i] && right >list[i]) 
					? Math.min(left, right)-list[i] : 0;
		}
		
		System.out.println(result);
		
	}
	
}
