package week10.BOJ_2573_G4_빙산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_G4_빙산 {

	public static int N;
	public static int M;
	public static int[][] map;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static class Node {
		int x;
		int y;
		int blank; // 인접한 바다의 개수

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int blank) {
			super();
			this.x = x;
			this.y = y;
			this.blank = blank;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		while (true) {
			// 녹을 수 있는 빙산 list에 넣기
			List<Node> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) { // 빙산일 때
						int blankCo = 0; // 인접한 바다 개수
						for (int k = 0; k < 4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];

							if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
								if (map[nx][ny] == 0) { // 인접한 위치가 바다일 때
									blankCo++;
								}
							}
						}
						list.add(new Node(i, j, blankCo));
					}
				}
			}

			// 빙산녹이기
			int count = 0; // 현재 남아있는 빙산 개수
			int icebergX = 0; // 빙산 좌표
			int icebergY = 0;
			for (int i = 0; i < list.size(); i++) {
				Node cur = list.get(i);
				// 빙산 녹이기(빙산 높이의 최저는 0으로 설정)
				map[cur.x][cur.y] = map[cur.x][cur.y] - cur.blank >= 0 ? map[cur.x][cur.y] - cur.blank : 0;
				if (map[cur.x][cur.y] != 0) { // 녹인 후 여전히 빙산일 때
					count++;
					icebergX = cur.x;
					icebergY = cur.y;
				}
			}

			result++; // 시간++

			if (count == 0) { // 현재 남아있는 빙산이 0개라면 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않는 경우
				result = 0;
				break;
			}

			int connectNo = bfs(icebergX, icebergY); // 아무 빙산에서 연결된 빙산들의 개수
			if (connectNo != count) { // 빙산에 연결된 개수가 총 빙산 개수와 다르면 여러 덩어리로 분리된 것이므로 종료
				break;
			}

		}
		System.out.println(result);
	}

	private static int bfs(int x, int y) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N][M];
		queue.offer(new Node(x, y));
		isVisited[x][y] = true;
		int count = 1; // 연결된 빙산의 개수

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !isVisited[nx][ny]) {
					if (map[nx][ny] != 0) {
						queue.offer(new Node(nx, ny));
						isVisited[nx][ny] = true;
						count++;
					}
				}
			}
		}
		return count;
	}
}
