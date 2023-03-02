package week4.BOJ_11052_S1_카드구매하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11052_S1_카드구매하기 {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] cardPacks = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < cardPacks.length; i++) {
			cardPacks[i] = Integer.parseInt(st.nextToken());
		}

		int[]dp = new int[N+1];
		
//		dp[1] = d[0] + cardPacks[1];
		
//		dp[2] = d[0] + cardPacks[2];
//		dp[2] = d[1] + cardPacks[1];
		
//		dp[3] = d[0] + cardPacks[3];
//		dp[3] = d[1] + cardPacks[2];
//		dp[3] = d[2] + cardPacks[1];
		
		for(int i = 1; i<N+1; i++) {
			for(int j= 1; j<=i; j++) {
				dp[i] = Math.max(dp[i], cardPacks[j] + dp[i-j]);
			}
		}

		System.out.println(dp[N]);
	}
}
