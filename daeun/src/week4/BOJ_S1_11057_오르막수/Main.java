package week4.BOJ_S1_11057_오르막수;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[N+1][10];
		for(int i=0;i<10;i++) { //한 자리
			dp[0][i] = 1;
		}
		
		for(int i=1;i<N+1;i++) { //자리
			for(int j=0;j<10;j++) { //10개까지인데 
				for(int k=j;k<10;k++) { //오르막 수만 해야 하니까
					dp[i][j] += dp[i-1][k];
					dp[i][j] %= 10007;
				}
			}
		}
		
		System.out.println(dp[N][0]);
	}
}