package week10.BOJ_14925_G4_목장건설하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14925_G4_목장건설하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] map = new int[M+1][N+1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[M+1][N+1]; // 현재좌표가 정사각형 오른쪽 아래의 꼭지점일 때 최대 변의 길이

		int result = 0;
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 0) { // 현재 좌표가 0일 때만 정사각형의 가능성 체크 가능
					// 현재 좌표의 위쪽dp, 왼쪽dp, 왼쪽대각선위dp을 통해 정사각형(0)의 범위를 알 수 있음
					// 해당 3개의 dp의 겹치는 부분(min)이 현재 꼭지점을 제외한 정사각형을 구성하고 있음(min+1이 현재 정사각형 최대 한변의 길이)
					int max = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
					max = Math.min(max, dp[i][j - 1]);
					max += 1;
					
					dp[i][j] = max;

					result = Math.max(result, dp[i][j]);
				}
			}
		}

		System.out.println(result);
	}
}
