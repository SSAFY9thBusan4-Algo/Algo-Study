package week18.BOJ_2666_G5_벽장문의이동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2666_G5_벽장문의이동 {

	public static int n;
	public static int len;
	public static int[] input;
	public static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());
		int open1 = Integer.parseInt(st.nextToken());
		int open2 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine());
		len = Integer.parseInt(st.nextToken()); // 사용할 벽장 순서
		input = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			st = new StringTokenizer(in.readLine());
			input[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[len + 1][n + 1][n + 1]; // dp[len][open1][open2] : len번째 단계에서 방 open1,open2를 여는 최소 횟수
		for (int i = 0; i <= len; i++) {
			for (int j = 0; j <= n; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(dfs(1, open1, open2));
	}

	private static int dfs(int cnt, int open1, int open2) {
		// 더이상 사용할 벽장이 없을 때
		if (cnt > len) {
			return 0;
		}

		// dp가 -1이 아니면 최소값이 저장된 상태이므로 dp 반환
		if (dp[cnt][open1][open2] != -1) {
			return dp[cnt][open1][open2];
		}

		// 열려있는 문 open1과 open2의 모든 경우의 수를 계산해서 더 작은 값을 dp에 저장
		return dp[cnt][open1][open2] = Math.min(Math.abs(open2 - input[cnt]) + dfs(cnt + 1, open1, input[cnt]),
				Math.abs(open1 - input[cnt]) + dfs(cnt + 1, open2, input[cnt]));
	}
}
