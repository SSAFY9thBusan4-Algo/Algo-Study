import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static char map[][];
	static boolean[][] visited;
	static int N = 12;
	static int M = 6;
	static boolean isBoom = true;
	static int result = 0;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		// 하나도 안 터지면 종료
		while (isBoom) {
			
			isBoom = false;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						bfs(i, j);
					}

				}
			}

			if (isBoom) {
				result ++;
				
				// 뿌요들 바닥으로 내리기
				ArrayDeque<Character> queue = new ArrayDeque<>();
				for (int j = 0; j < M; j++) {

					for (int i = 0; i < N; i++) {
						if (map[i][j] != '.') {
							queue.offer(map[i][j]);
							map[i][j] = '.';
						}
					}
					for (int i = N - 1; i >= 0; i--) {

						if (queue.isEmpty())
							break;
						map[i][j] = queue.pollLast();
					}
				}
			} 

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
		}

		System.out.println(result);
	}

	static int[][] delta = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	private static void bfs(int i, int j) {

		char puyo = map[i][j]; // bfs를 통해 처음 들어온 알파벳
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(i, j));
		visited[i][j] = true;
		List<Point> list = new ArrayList<>();
		list.add(new Point(i, j)); // puyo와 같은 알파벳 리스트

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = now.x + delta[d][0];
				int ny = now.y + delta[d][1];

				if (!(nx >= 0 && ny >= 0 && nx < N && ny < M))
					continue;

				if (visited[nx][ny] || map[nx][ny] == '.')
					continue;

				if (map[nx][ny] == puyo) {
					// 같은 알파벳이면 저장 해놓고, 그 수가 4이상이면 터트린다. (.으로 바꾼다)
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					list.add(new Point(nx, ny));
				}
			}
		}

		if (list.size() >= 4) {
			for (Point point : list) {
				map[point.x][point.y] = '.';
			}
			isBoom = true;
		}
	}
}
