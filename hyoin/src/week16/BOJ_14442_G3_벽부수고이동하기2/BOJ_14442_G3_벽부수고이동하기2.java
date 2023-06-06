package week16.BOJ_14442_G3_벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442_G3_벽부수고이동하기2 {
	public static int N;
	public static int M;
	public static int K;
	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };

	public static int[][] map;
	public static boolean[][][] isVisited;
	public static int result;

	public static class Node {
		int x;
		int y;
		int depth; // 움직인 거리
		int breakCo; // 벽을 깬 횟수

		public Node(int x, int y, int depth, int breakCo) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.breakCo = breakCo;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", depth=" + depth + ", breakCo=" + breakCo + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String str = in.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j - 1);
			}
		}

		isVisited = new boolean[N + 1][M + 1][K + 1]; // 해당 좌표에 K번 벽을 깨면서 도착했는지 확인하는 배열
		bfs(1, 1);

		if (result == 0) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	private static void bfs(int x, int y) {
		isVisited[x][y][0] = true;
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(x, y, 1, 0));

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			int curX = node.x;
			int curY = node.y;
			int depth = node.depth;
			int breakCo = node.breakCo;

			if (curX == N && curY == M) { // 종료조건
				result = depth;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];

				if (nx >= 1 && nx <= N && ny >= 1 && ny <= M) {
					if (map[nx][ny] == '1') { // 이동할 곳이 벽일 때
						if (breakCo >= K) { // 벽을 깰 수 있는 횟수를 초과했을 때
							continue;
						}
						if (!isVisited[nx][ny][breakCo + 1]) { // 벽을 깨면서 이동가능할 때
							isVisited[nx][ny][breakCo + 1] = true;
							queue.offer(new Node(nx, ny, depth + 1, breakCo + 1));
						}
					} else { 
						if (!isVisited[nx][ny][breakCo]) { // 벽을 깨지 않고 이동가능할 때
							isVisited[nx][ny][breakCo] = true;
							queue.offer(new Node(nx, ny, depth + 1, breakCo));
						}
					}
				}
			}
		}
	}
}
