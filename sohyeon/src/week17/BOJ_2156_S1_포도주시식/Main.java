package week17.BOJ_2156_S1_포도주시식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		
		int[] list = new int[n+1];
		for (int i=1; i<=n; i++) {
			list[i] = Integer.parseInt(in.readLine());
		}
		
		int[] dp = new int[n+1];
		for (int i=1; i<=n; i++) {
			if (i==1 || i==2) {
				dp[i] = dp[i-1]+list[i];
			}
			else {
				dp[i] = Math.max(dp[i-1], Math.max(dp[i-3]+list[i-1]+list[i], dp[i-2]+list[i]));
			}
		}
		
		System.out.println(dp[n]);
		
	}
}
