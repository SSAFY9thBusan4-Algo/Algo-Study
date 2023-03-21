package week7.BOJ_1743_S1_음식물피하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743_S1_음식물피하기 {

	public static int N;
	public static int M;
	public static int K;
	public static int[][] map;
	public static boolean[][] isVisited;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int count;
	public static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}

		result=0;
        isVisited = new boolean[N + 1][M + 1];
        // 음식물을 시작으로 상하좌우 탐색해나가면서 음식물의 크기 측정(모든 음식물 탐색)
        // 탐색하면서 방문한 음식물은 다시 크기 측정할 필요가 없으니 제외
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j]==1&&!isVisited[i][j]) { // 음식물이고 방문하지 않았을 때
					count = 1; // 현재 음식물 위치에서의 크기
					bfs(i, j);
					result = Math.max(result, count); // 최대값 업데이트
				}
			}
		}
		
		System.out.println(result);

	}

	private static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		isVisited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] curXY = queue.poll();
			int curX = curXY[0];
			int curY = curXY[1];

			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];

				if (nx >= 1 && nx <= N && ny >= 1 && ny <= M && !isVisited[nx][ny]) {
					if (map[nx][ny] == 1) {// 인접좌표가 음식물일 때 음식물의 크기+1
						count++;
						isVisited[nx][ny]=true;
						queue.offer(new int[] { nx, ny });
					}
				}
			}
		}
	}
}

