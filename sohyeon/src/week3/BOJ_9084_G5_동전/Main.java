package week3.BOJ_9084_G5_동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			
			int N = Integer.parseInt(in.readLine());
			String[] str = in.readLine().split(" ");
			int M = Integer.parseInt(in.readLine());
			int[] coins = new int[N];
			for (int i=0; i<N; i++) {
				coins[i] = Integer.parseInt(str[i]);
			}
			
			int[] dp = new int[M+1];
			dp[0] = 1;
			for (int coin : coins) {
				for (int i = coin; i <= M; i++) {
					dp[i] += dp[i-coin];
				}
			}
			
			System.out.println(dp[M]);
			
		}
		
	}

}
