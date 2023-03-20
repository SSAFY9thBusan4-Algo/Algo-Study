package week7.BOJ_2665_G4_미로만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_2665_G4_미로만들기 {

	public static int n;
	public static char[][] map;
	public static int[][] distances; // 해당 좌표까지 부순 벽의 최소값
	public static int[] dx = { -1, 1, -0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static StringBuilder sb = new StringBuilder();

	public static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		map = new char[n][n];
		distances = new int[n][n];

		for (int i = 0; i < n; i++) {
			map[i] = in.readLine().toCharArray();
			Arrays.fill(distances[i], Integer.MAX_VALUE);
		}

		bfs();

		sb.append(distances[n-1][n-1]);
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0));
		distances[0][0] = 0;

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int curX = curNode.x;
			int curY = curNode.y;

			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];

				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					if(distances[nx][ny]>distances[curX][curY]) { // 현재 이동경로에서 부순벽의 수가 다음 경로의 부순벽 수 보다 작으면 최소값 업데이트 
						if(map[nx][ny]=='0') { // 검은방일 때 distance+1
							distances[nx][ny]=distances[curX][curY]+1;
							queue.offer(new Node(nx, ny));
						}
						else { // 흰방일 때
							distances[nx][ny]=distances[curX][curY];
							queue.offer(new Node(nx, ny));

						}
					}
				}
			}
		}
	}

}
