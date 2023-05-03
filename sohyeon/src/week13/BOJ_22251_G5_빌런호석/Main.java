package week13.BOJ_22251_G5_빌런호석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N,K,P,X, result;
	private static int[][] numbers = {
			{1,1,1,0,1,1,1},  
			{0,0,1,0,0,1,0},
			{1,0,1,1,1,0,1},
			{1,0,1,1,0,1,1},
			{0,1,1,1,0,1,0},
			{1,1,0,1,0,1,1},
			{1,1,0,1,1,1,1},
			{1,0,1,0,0,1,0},
			{1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1}	
	};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		int[] x_digit = to_digit(X);
//		System.out.println(Arrays.toString(x_digit));
		
		// 1~N까지 X에서 몇개를 반전시켜야 하는지 세고 P개 이하이면 result++
		for (int i=1; i<=N; i++) {
			if (i==X) continue;
			int[] target = to_digit(i);
			int cnt = 0;
			for (int j=0; j<K; j++) {
				for (int k=0; k<7; k++) {
					if (numbers[x_digit[j]][k] != numbers[target[j]][k]) {
						cnt++;
					}
				}
			}
			if (cnt <= P) result++;
		}
		
		System.out.println(result);
	}

	private static int[] to_digit(int x) {
		int[] digit = new int[K];
		for (int i=0; i<K; i++) {
			digit[i] = (int)(x/(Math.pow(10, K-i-1)));
			x %= (Math.pow(10, K-i-1));
		}
		return digit;
	}
}
