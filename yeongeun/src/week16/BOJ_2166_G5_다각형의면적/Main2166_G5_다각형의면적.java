package src.week16.BOJ_2166_G5_다각형의면적;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2166_G5_다각형의면적 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] list = new int[N][2];
		for(int i = 0 ; i < N ; i++) {
			String[] input = br.readLine().split(" ");
			list[i][0] = Integer.parseInt(input[0]);
			list[i][1] = Integer.parseInt(input[1]);
		}
		
		long sum = 0;
		for(int i = 2 ; i < N ; i++) {
			sum += ex(list[0], list[i-1], list[i]);
		}
		if(sum < 0) sum*= -1; 
		System.out.printf("%.1f",(sum/2.0));
		
	}

	// a벡터 b벡터 외적
	private static long ex(int[] s, int[] a, int[] b) {
		return (long)(a[0]-s[0])*(b[1]-s[1]) - (long)(a[1]-s[1])*(b[0]-s[0]);
	}
}
