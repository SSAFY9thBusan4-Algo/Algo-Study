package week5.SWEA_1953_탈주범검거;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {

	private static int N;
	private static int M;
	private static int L;
	private static int[][] map;
	private static boolean[][] isVisited;
	private static int result;

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/1953_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");

			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			result = 1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			isVisited = new boolean[N][M];
			bfs(R, C);

			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs(int startX, int startY) {

		Queue<int[]> queue = new ArrayDeque<>();

		isVisited[startX][startY] = true;
		queue.offer(new int[] { startX, startY, 1 });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curX = cur[0];
			int curY = cur[1];
			int curCount = cur[2];

			if (curCount == L) {
				break;
			}

			int mode = map[curX][curY]; // 파이프의 종류

			int[] dx = { -1, 1, 0, 0 }; // 상하좌우
			int[] dy = { 0, 0, -1, 1 };
			switch (mode) {
			case 1:
				for (int i = 0; i < 4; i++) { // 상하좌우의 파이프가 연결되어있는지 탐색
					int nx = curX + dx[i];
					int ny = curY + dy[i];

					if (check(nx, ny, i)) {
						queue.offer(new int[] { nx, ny, curCount + 1 });

					}
				}
				break;
			case 2:
				for (int i = 0; i < 2; i++) { // 상하 탐색
					int nx = curX + dx[i];
					int ny = curY + dy[i];

					if (check(nx, ny, i)) {
						queue.offer(new int[] { nx, ny, curCount + 1 });
					}
				}
				break;
			case 3:
				for (int i = 2; i < 4; i++) { // 좌우 탐색
					int nx = curX + dx[i];
					int ny = curY + dy[i];

					if (check(nx, ny, i)) {
						queue.offer(new int[] { nx, ny, curCount + 1 });
					}
				}
				break;
			case 4:
				for (int i = 0; i < 4; i += 3) { // 상우 탐색
					int nx = curX + dx[i];
					int ny = curY + dy[i];

					if (check(nx, ny, i)) {
						queue.offer(new int[] { nx, ny, curCount + 1 });
					}
				}
				break;
			case 5:
				for (int i = 1; i < 4; i += 2) { // 하우 탐색
					int nx = curX + dx[i];
					int ny = curY + dy[i];

					if (check(nx, ny, i)) {
						queue.offer(new int[] { nx, ny, curCount + 1 });
					}
				}
				break;
			case 6:
				for (int i = 1; i < 3; i++) { // 하좌 탐색
					int nx = curX + dx[i];
					int ny = curY + dy[i];

					if (check(nx, ny, i)) {
						queue.offer(new int[] { nx, ny, curCount + 1 });
					}
				}
				break;
			case 7:
				for (int i = 0; i < 4; i += 2) { // 상좌 탐색
					int nx = curX + dx[i];
					int ny = curY + dy[i];

					if (check(nx, ny, i)) {
						queue.offer(new int[] { nx, ny, curCount + 1 });
					}
				}
				break;
			}
		}
	}

	/*
	 * 연결할 수 있는 파이프
	 * 상 : 1, 2, 5, 6 
	 * 하 : 1, 2, 4, 7 
	 * 좌 : 1, 3, 4, 5 
	 * 우 : 1, 3, 6, 7
	 */
	// 파이프가 연결되어있는지 탐색하는 메소드
	private static boolean check(int nx, int ny, int i) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
			if (!isVisited[nx][ny] && map[nx][ny] > 0) {
				if (i == 0) { // 상
					if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
						isVisited[nx][ny] = true;
						result++;
						return true;

					}
				} else if (i == 1) { // 하
					if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
						isVisited[nx][ny] = true;
						result++;
						return true;

					}
				} else if (i == 2) { // 좌
					if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
						isVisited[nx][ny] = true;
						result++;
						return true;

					}
				} else if (i == 3) { // 우
					if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
						isVisited[nx][ny] = true;
						result++;
						return true;
					}
				}
			}
		}
		return false;
	}
}
