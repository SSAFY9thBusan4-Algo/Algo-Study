package week18.BOJ_17836_G5_공주님을구해라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17836_G5_공주님을구해라 {
	public static int N;
	public static int M;
	public static int T;
	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };
	public static int[][] map;
	public static int result;

	public static class Node {
		int x;
		int y;
		int time; // 움직인 시간
		int hasGram; // 그람 여부

		public Node(int x, int y, int time, int hasGram) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.hasGram = hasGram;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", time=" + time + ", hasGram=" + hasGram + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean isSucces = bfs();
		if (isSucces) {
			System.out.println(result);
		} else {
			System.out.println("Fail");
		}
	}

	private static boolean bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[N][M][2]; // 그람여부에따라 체크
		queue.offer(new Node(0, 0, 0, 0));
		isVisited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curTime = cur.time;
			int curHasGram = cur.hasGram;

			if (curTime > T) { // T시간을 초과할 때
				return false;
			}

			if (curX == N - 1 && curY == M - 1) { // 공주님을 만났을 때
				result = curTime;
				return true;
			}

			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					int nHasGram = curHasGram; // 다음 그람 여부
					if (map[nx][ny] == 2) { // 다음에 그람이 존재할 때
						nHasGram = 1;
						queue.offer(new Node(nx, ny, curTime + 1, nHasGram));
						isVisited[nx][ny][nHasGram] = true;
					}

					if (!isVisited[nx][ny][curHasGram]) {
						if (map[nx][ny] == 0) { // 빈공간일 때
							queue.offer(new Node(nx, ny, curTime + 1, nHasGram));
							isVisited[nx][ny][nHasGram] = true;
						} else if (map[nx][ny] == 1) {// 벽일 때
							if (curHasGram == 1) { // 그람을 가지고있으면 큐에 추가
								queue.offer(new Node(nx, ny, curTime + 1, nHasGram));
								isVisited[nx][ny][nHasGram] = true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
