package week20.BOJ_1749_G4_점수따먹기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1749_G4_점수따먹기 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][M + 1];
		int[][] dp = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// (1,1)과 (i,j)를 꼭짓점으로 가지는 사각형의 부분합을 dp에 저장
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + map[i][j];
			}
		}

		int max = Integer.MIN_VALUE;
		// (i,j)와 (k,l)을 꼭짓점으로 가지는 사각형의 부분합 구하기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int k = i; k <= N; k++) {
					for (int l = j; l <= M; l++) {
						int sum = dp[k][l] - dp[k][j - 1] - dp[i - 1][l] + dp[i - 1][j - 1];
						max = Math.max(max, sum);
					}
				}
			}
		}

		System.out.println(max);
	}
}
