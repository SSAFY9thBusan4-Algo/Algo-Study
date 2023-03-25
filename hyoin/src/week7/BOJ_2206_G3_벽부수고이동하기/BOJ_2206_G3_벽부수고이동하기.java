package week7.BOJ_2206_G3_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_G3_벽부수고이동하기 {

	public static int N;
	public static int M;
	public static char[][] map;
	public static boolean[][][] isVisited; // [][][0]일 때는 벽을 부수지 않고 방문했을 때, [][][1]일 때는 벽을 부수고 방문했을 때
	public static int[] dx = { -1, 1, -0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int result;

	public static StringBuilder sb = new StringBuilder();

	public static class Node {
		int x;
		int y;
		boolean isBreak;
		int depth;

		public Node(int x, int y, boolean isBreak, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.isBreak = isBreak;
			this.depth = depth;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
		}

		isVisited = new boolean[N][M][2];
		result = -1;
		bfs();

		sb.append(result);
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, false, 1));
		isVisited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int curX = curNode.x;
			int curY = curNode.y;
			boolean isBreak = curNode.isBreak;
			int count = curNode.depth;

			if (curX == N - 1 && curY == M - 1) { // 도착지에 도착했을 때
				result = count;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (!isBreak) { // 계속 벽을 부수지 않고 이동했을 때
						if (!isVisited[nx][ny][0] && map[nx][ny] == '0') { // 다음 길을 방문한 적이 없고 벽이 아닐때 갈 수 있음
							isVisited[nx][ny][0] = true;
							queue.offer(new Node(nx, ny, isBreak, count + 1));
						} else if (map[nx][ny] == '1') { // 다음 길이 벽이면 한번 부수고 갈 수 있음
							isVisited[nx][ny][1] = true;
							queue.offer(new Node(nx, ny, true, count + 1));
						}
					} else { // 한번이라도 벽을 부순적이 있을 때
						if (!isVisited[nx][ny][1] && (map[nx][ny] == '0')) {// 다음 길을 방문한 적이 없고 벽이 아닐때 갈 수 있음
							isVisited[nx][ny][1] = true;
							queue.offer(new Node(nx, ny, isBreak, count + 1));
						}
					}
				}
			}
		}
	}

}
