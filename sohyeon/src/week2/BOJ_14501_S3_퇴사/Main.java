package week2.BOJ_14501_S3_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int[] dp = new int[N+1];		
		
		for (int i = 1; i<=N; i++) {
			String[] str = in.readLine().split(" ");
			int T = Integer.parseInt(str[0]);
			int P = Integer.parseInt(str[1]);
			if (i+T-1 > N) continue;
			int lastsum = dp[i-1];
			for (int j = i; j<=N; j++) {
				if (j == i+T-1) {
					dp[i+T-1] = Math.max(dp[i+T-1], lastsum+P);
					continue;
				}
				dp[j] = Math.max(dp[j-1], dp[j]);
				
			}			
			
		}
		System.out.println(dp[N]);
		
	}
	
}
