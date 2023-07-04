package week19.BOJ_2615_S1_오목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_S1_오목 {

	public static int[] dx = { 1, 0, 1, -1 }; // 하, 우, 오른쪽아래, 왼쪽아래
	public static int[] dy = { 0, 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[19][19];
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int match = 0; // 누가 이겼는지
		int resultX = 0;
		int resultY = 0;

		out: 
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (map[i][j] != 0) { // 색있는 돌일 때
					for (int k = 0; k < 4; k++) { // 하->상, 우->좌, 오른쪽아래->오른쪽위, 왼쪽아래->왼쪽위 순으로 탐색
						int cnt = 1; // 연속된 돌의 개수
						int nx = i;
						int ny = j;
						
						// 하, 우, 오른쪽아래, 왼쪽아래 중 한 방향으로 계속 탐색
						while (true) {
							nx += dx[k];
							ny += dy[k];
							if (nx >= 0 && nx < 19 && ny >= 0 && ny < 19) {
								if (map[nx][ny] == map[i][j]) { // 다음 돌이 같은 색일 때
									cnt++;
								} else { // 다른색이면 종료
									break;
								}
							} else { // 범위를 벗어나면 종료
								break;
							}

						}

						nx = i;
						ny = j;
						// 위와 반대로 상, 좌, 오른쪽위, 왼쪽위 중 한 방향으로 계속 탐색
						while (true) {
							nx += -dx[k]; // 현재 방향과 반대방향으로 탐색
							ny += -dy[k];
							if (nx >= 0 && nx < 19 && ny >= 0 && ny < 19) {
								if (map[nx][ny] == map[i][j]) {
									cnt++;
								} else {
									break;
								}
							} else {
								break;
							}
						}

						if (cnt == 5) { // 돌이 5개 연속일 때
							match = map[i][j];
							resultX = nx + dx[k]+1; // 현재 nx가 가장왼쪽 or 가장위쪽에 있는 돌
							resultY = ny + dy[k]+1;
							break out;
						}
					}
				}
			}
		}

		System.out.println(match);

		if (match != 0) {
			System.out.println(resultX + " " + resultY);
		}

	}
}
