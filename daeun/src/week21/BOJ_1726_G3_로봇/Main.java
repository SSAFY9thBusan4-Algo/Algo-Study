import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Point {
		int x;
		int y;
		int d;
		int cnt;

		public Point(int x, int y, int d, int cnt) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 동 서 남 북 => 이거 입력 순서 주의
		int[] dx = { 0, 0, 1, -1 };
		int[] dy = { 1, -1, 0, 0 };

		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		boolean[][] map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			split = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				if(split[j].equals("0")) {
					map[i][j] = true;
				}
				else {
					map[i][j] = false;
				}
			}
		}

		split = br.readLine().split(" ");
		int x = Integer.parseInt(split[0])-1;
		int y = Integer.parseInt(split[1])-1;
		int d = Integer.parseInt(split[2])-1;
		Point start = new Point(x, y, d, 0);

		split = br.readLine().split(" ");
		x = Integer.parseInt(split[0])-1;
		y = Integer.parseInt(split[1])-1;
		d = Integer.parseInt(split[2])-1;
		Point end = new Point(x, y, d, 0);

		Queue<Point> q = new ArrayDeque<>();
		boolean[][][] visit = new boolean[N][M][4];
		q.offer(start);
		visit[start.x][start.y][start.d] = true;

		int cnt = 0;
		out: while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point now = q.poll();
				x = now.x;
				y = now.y;
				d = now.d;
				cnt = now.cnt;
				if (x == end.x && y == end.y && d == end.d) {
					break out;
				}

				// 명령 1번
				for (int dist = 1; dist < 4; dist++) {
					int X = dx[d] * dist + x;
					int Y = dy[d] * dist + y;
					if (X < 0 || X >= N || Y < 0 || Y >= M || !map[X][Y]) {
						break;
					}
					if(!visit[X][Y][d]) { //방문은 따로 해줘야 함
						q.offer(new Point(X, Y, d, cnt + 1));
						visit[X][Y][d] = true;
					}
				}

				// 명령 2번
				int one = 0;
				int two = 0;
				if (d == 0 || d == 1) { // 동서
					one = 2;
					two = 3;
				}
				else { // 남북
					one = 0;
					two = 1;
				}

				if (!visit[x][y][one]) {
					q.offer(new Point(x, y, one, cnt + 1));
					visit[x][y][one] = true;
				}
				if (!visit[x][y][two]) {
					q.offer(new Point(x, y, two, cnt + 1));
					visit[x][y][two] = true;
				}
			}
		}

		System.out.println(cnt);
	}
}
