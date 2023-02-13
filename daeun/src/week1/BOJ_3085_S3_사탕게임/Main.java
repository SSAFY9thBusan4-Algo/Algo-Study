package week1.BOJ_3085_S3_사탕게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		String[][] map = new String[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().split("");
		}

		String temp;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j + 1 < N) { // 우
					temp = map[i][j];
					map[i][j] = map[i][j + 1];
					map[i][j + 1] = temp;

					solve(map);

					temp = map[i][j];
					map[i][j] = map[i][j + 1];
					map[i][j + 1] = temp;

				}

				if (i + 1 < N) { // 하
					temp = map[i][j];
					map[i][j] = map[i + 1][j];
					map[i + 1][j] = temp;
					
					solve(map);

					temp = map[i][j];
					map[i][j] = map[i + 1][j];
					map[i + 1][j] = temp;

				}
			}
		}

		System.out.println(max);
	}

	private static void solve(String[][] map) {
		for (int x = 0; x < N; x++) {
			int count = 1;
			for (int y = 0; y < N - 1; y++) {
				if (map[x][y].equals(map[x][y + 1])) {
					count++;
				} else {
					count = 1;
				}
				max = Math.max(count, max);
			}
		}

		for (int y = 0; y < N; y++) {
			int count = 1;
			for (int x = 0; x < N - 1; x++) {
				if (map[x][y].equals(map[x + 1][y])) {
					count++;
				} else {
					count = 1;
				}
				max = Math.max(count, max);
			}
		}

	}
}