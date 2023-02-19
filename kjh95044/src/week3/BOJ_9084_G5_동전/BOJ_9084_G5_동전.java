package week3.BOJ_9084_G5_동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9084_G5_동전 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i< N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			
			int[] dp = new int[M+1];
			dp[0] = 1;
			for(int coin : arr) {
				for(int j = coin; j<=M; j++) {
					dp[j] += dp[j - coin];
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		System.out.println(sb);
	}
	
}
