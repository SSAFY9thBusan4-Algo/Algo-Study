package week4.BOJ_11052_S1_카드구매하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
				
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		String str = in.readLine();
		StringTokenizer stk = new StringTokenizer(str);		
		int[] dp = new int[N+1];				
		for (int i = 1; i <= N; i++) {
			dp[i] = Integer.parseInt(stk.nextToken());
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= (int)i/2; j++) {
				dp[i] = Math.max(dp[i], dp[j]+dp[i-j]);
			}
		}
		
		System.out.println(dp[N]);
		
	}

}
