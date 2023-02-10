package week2.BOJ_11727_S3_2xn타일링2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		

		/*
		 * 풀이 시작 
		 * n=1일 때, 경우의 수 = 1 
		 * n=2일 때, 경우의 수 = 3 
		 * n번째 직사각형의 경우의 수 = n-1 + 2*(n-2)
		 */
		int[] dp = new int[n + 2];
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= n; i++) { // n이 3일 때 부터 점화식 적용
			dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
		}

		System.out.println(dp[n]);
	}
}
