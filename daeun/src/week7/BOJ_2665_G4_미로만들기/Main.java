package week7.BOJ_2665_G4_미로만들기; //처음에는 끝에 도착하면 끝나게 해서..

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, result, visit[][];
	static boolean[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N + 1][N + 1];
		visit = new int[N + 1][N + 1]; // 뚫은 수를 세야해서 int로 바꿈
		
		for (int i = 1; i < N + 1; i++) {
			String str = br.readLine();
			for (int j = 1; j < N + 1; j++) {
				if (str.charAt(j - 1) == '1') {
					map[i][j] = true;
				} else {
					map[i][j] = false;
				}
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		
		result = Integer.MAX_VALUE;
		dfs(1, 1);
		System.out.println(result);
	}

	private static void dfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y, 0 }); // x좌표, y좌표, 뚫은 벽
		visit[x][y] = 0;

		int[] temp;
		while (!queue.isEmpty()) {
			temp = queue.poll();
			for (int d = 0; d < 4; d++) {
				int X = temp[0] + dx[d];
				int Y = temp[1] + dy[d];

				if (X > 0 && X <= N && Y > 0 && Y <= N && visit[X][Y] > temp[2]) { // 일단 범위 안에서 더 작은게 나오면
					if (map[X][Y]) { // 벽이 아니면
						queue.offer(new int[] { X, Y, temp[2]});
						visit[X][Y] = temp[2];
					} else { // 벽이면
						queue.offer(new int[] { X, Y, temp[2] + 1});
						visit[X][Y] = temp[2] + 1;

					}
				}
			}
		}
		
		result = visit[N][N];
	}
}