package week18.BOJ_1937_G3_욕심쟁이판다;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1937_G3_욕심쟁이판다 {

	public static int n;
	public static int[][] map;
	public static int[][] dp; // 현재좌표에서 가장 많이 이동가능한 횟수 저장
	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[n][n];

		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int max = dfs(i, j);
				if (result < max) {
					result = max;
				}
			}
		}
		System.out.println(result);
	}

	private static int dfs(int x, int y) {
		if (dp[x][y] != 0) { // dp에 저장된 값이 존재하면 해당 값이 최대값
			return dp[x][y];
		}

		dp[x][y] = 1; // 초기값은 1
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
				if (map[nx][ny] > map[x][y]) { // 판다가 이동가능할 때
					// 상하좌우 중 가장 많이 이동가능 한 횟수를 dp에 저장
					dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
				}
			}
		}
		return dp[x][y];
	}
}
