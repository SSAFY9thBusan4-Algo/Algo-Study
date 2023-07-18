package week18.BOJ_16197_G4_두동전;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16197_G4_두동전 {
	public static int N;
	public static int M;
	public static char[][] map;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };
	public static int result;

	public static class Node {
		int[] coin1;
		int[] coin2;
		int depth; // 움직인 거리

		public Node(int[] coin1, int[] coin2, int depth) {
			super();
			this.coin1 = coin1;
			this.coin2 = coin2;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [coin1=" + Arrays.toString(coin1) + ", coin2=" + Arrays.toString(coin2) + ", depth=" + depth
					+ "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ArrayList<int[]> coins = new ArrayList<>();
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'o') {
					coins.add(new int[] { i, j });
					map[i][j] = '.';
				}
			}
		}

		result = -1;
		bfs(coins);
		System.out.println(result);
	}

	private static void bfs(ArrayList<int[]> coins) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(coins.get(0), coins.get(1), 0));
		boolean[][][][] isVisited = new boolean[N][M][N][M]; // coin1좌표, coin2좌표 방문체크
		isVisited[coins.get(0)[0]][coins.get(0)[1]][coins.get(1)[0]][coins.get(1)[1]]  = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int[] coin1 = cur.coin1; // coin1의 좌표
			int[] coin2 = cur.coin2; // coin2의 좌표
			int depth = cur.depth; // 움직인 횟수

			if (depth >= 10) { // 움직인 횟수가 10회 이상일 때 종료
				return;
			}

			for (int i = 0; i < 4; i++) {
				int coin1nx = coin1[0] + dx[i];
				int coin1ny = coin1[1] + dy[i];
				int coin2nx = coin2[0] + dx[i];
				int coin2ny = coin2[1] + dy[i];

				boolean coin1Out = false; // coin1이 범위를 벗어났는지
				boolean coin2Out = false; // coin2이 범위를 벗어났는지
				if (coin1nx < 0 || coin1nx >= N || coin1ny < 0 || coin1ny >= M) {
					coin1Out = true;
				}
				if (coin2nx < 0 || coin2nx >= N || coin2ny < 0 || coin2ny >= M) {
					coin2Out = true;
				}

				if (coin1Out ^ coin2Out) { // 동전 1개만 범위밖일 때 정답
					result = depth + 1;
					return;
				}

				if (coin1Out & coin2Out) { // 동전 2개 모두 범위밖일 때 패스
					continue;
				}

				if (!isVisited[coin1nx][coin1ny][coin2nx][coin2ny]) {
					boolean coin1Move = true; // coin1이 움직였는지
					boolean coin2Move = true; // coin2이 움직였는지
					if (map[coin1nx][coin1ny] == '#') { // coin1이 벽을 만났을 때
						coin1Move = false;
						coin1nx = coin1[0];
						coin1ny = coin1[1];
					}
					if (map[coin2nx][coin2ny] == '#') { // coin2이 벽을 만났을 때
						coin2Move = false;
						coin2nx = coin2[0];
						coin2ny = coin2[1];
					}

					if (!(coin1Move | coin2Move)) { // 2개의 동전 모두 움직이지 않을 때 패스
						continue;
					}

					// 동전2개가 모두 움직일 때 큐에 삽입
					queue.offer(new Node(new int[] { coin1nx, coin1ny }, new int[] { coin2nx, coin2ny }, depth + 1));
					isVisited[coin1nx][coin1ny][coin2nx][coin2ny] = true;
				}
			}
		}

	}
}
