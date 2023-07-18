import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N; // 세로선
	public static int M; // 가로선
	public static int H; // 놓을 수 있는 가로선
	public static int[][] map;
	public static boolean status;
	public static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H + 1][N + 1]; // (가로선, 세로선) = 1이면 오른쪽 사다리, 2면 왼쪽 사다리

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[a][b + 1] = 2;
		}

		for (int i = 0; i <= 3; i++) {
			result=i;
			dfs(1, 0);
			if(status) break;
		}
		if (status) {
			System.out.println(result);
		} else {
			System.out.println(-1);
		}
	}

	private static void dfs(int width, int count) {

		if (status) {
			return;
		}

		if (count == result) {
			boolean isOk = true;
			for (int i = 1; i <= N; i++) {
				int curW = 1;
				int curH = i;
				for (int j = 1; j < H + 1; j++) {
					if (map[curW][curH] == 1) {
						curH = curH + 1;
					} else if (map[curW][curH] == 2) {
						curH = curH - 1;
					}
					curW++;
				}

				if (curH != i) {
					isOk = false;
					break;
				}
			}

			if (isOk) {
				status = true;
				result = count;
			}
			return;
		}

		for (int i = width; i < H + 1; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					map[i][j] = 1;
					map[i][j + 1] = 2;

					dfs(i, count + 1);

					map[i][j] = 0;
					map[i][j + 1] = 0;
				}
			}
		}
	}
}
