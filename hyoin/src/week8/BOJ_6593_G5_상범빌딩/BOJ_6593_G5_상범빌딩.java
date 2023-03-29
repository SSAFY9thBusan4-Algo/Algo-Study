package week8.BOJ_6593_G5_상범빌딩;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593_G5_상범빌딩 {
	public static int L;
	public static int R;
	public static int C;
	public static char[][][] map;
	public static boolean[][][] isVisited;
	public static int[] dx = { -1, 1, 0, 0, 0, 0 }; // x축
	public static int[] dy = { 0, 0, -1, 1, 0, 0 }; // y축
	public static int[] dz = { 0, 0, 0, 0, 1, -1 }; // z축
	public static int[] startZXY; // 시작 z,y,x 좌표
	public static boolean isEscape; // 탈출 가능 여부
	public static int result;

	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(in.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L==0&&R==0&&C==0) {
				break;
			}

			// map의 0번째 인덱스가 z축, 1번째 인덱스가 x축,3번째 인덱스가 y축
			map = new char[L][R][C];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					map[i][j] = in.readLine().toCharArray();
					for (int k = 0; k < C; k++) {
						if (map[i][j][k] == 'S') {
							startZXY = new int[] { i, j, k };
						}
					}
				}
				in.readLine();
			}

			result=0;
			isVisited = new boolean[L][R][C];
			isEscape=false;
			bfs();
			if (isEscape) {
				sb.append("Escaped in ").append(result).append(" minute(s).").append("\n");
			} else {
				sb.append("Trapped!").append("\n");
			}
		}
		System.out.println(sb);
	}

	// z축이 추가된 것 말고는 일반 bfs 로직과 동일
	private static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(startZXY);
		isVisited[startZXY[0]][startZXY[1]][startZXY[2]] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int k = 0; k < size; k++) {
				int[] curZXY = queue.poll();

				for (int i = 0; i < 6; i++) { // 방향이 6개(동,서,남,북,상,하)
					int nz = curZXY[0] + dz[i];
					int nx = curZXY[1] + dx[i];
					int ny = curZXY[2] + dy[i];

					if (nz >= 0 && nz < L && nx >= 0 && nx < R && ny >= 0 && ny < C && !isVisited[nz][nx][ny]) {
						if (map[nz][nx][ny] == '.') {
							isVisited[nz][nx][ny]=true;
							queue.offer(new int[] { nz, nx, ny });
						} else if (map[nz][nx][ny] == 'E') {
							isEscape = true;
							result++;
							return;
						}
					}
				}
			}
			result++;
		}

	}
}

