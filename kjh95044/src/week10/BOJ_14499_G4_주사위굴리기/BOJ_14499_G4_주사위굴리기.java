import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M, map[][];
	static int[] dice = new int[7];
	static int[][] delta = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static Point cur;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());

		cur = new Point(startX, startY);
		int K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int cmd = Integer.parseInt(st.nextToken());

			int nx = cur.x + delta[cmd - 1][0];
			int ny = cur.y + delta[cmd - 1][1];

			if (!(nx >= 0 && ny >= 0 && nx < N && ny < M))
				continue;
			
			// 주사위 가로 방향 : 위 1, 오른 3, 아래 6, 왼 4
			// 주사위 세로 방향 : 위 1, 저쪽 2, 아래 6, 이쪽 5
			int[] horizen = {1,3,6,4};
			int[] vertical = {1,2,6,5};
			
			switch (cmd) {
			
			case 1: // 동쪽 : 가로 방향 한칸씩 돌리기
				int temp = dice[horizen[3]];
				for(int h=3; h>0; h--) {
					dice[horizen[h]] = dice[horizen[h-1]];
				}
				dice[horizen[0]] = temp;
				break;

			case 2: // 서쪽 : 가로 방향 반대로 한칸씩 돌리기
				temp = dice[horizen[0]];
				for(int h=0; h<3; h++) {
					dice[horizen[h]] = dice[horizen[h+1]];
				}
				dice[horizen[3]] = temp;
				
				break;
			case 3: // 북쪽 : 세로 방향 반대로 한칸씩 돌리기
				temp = dice[vertical[0]];
				for(int h=0; h<3; h++) {
					dice[vertical[h]] = dice[vertical[h+1]];
				}
				dice[vertical[3]] = temp;
				break;
			case 4: // 남쪽 : 세로 방향 한칸씩 돌리기
				temp = dice[vertical[3]];
				for(int h=3; h>0; h--) {
					dice[vertical[h]] = dice[vertical[h-1]];
				}
				dice[vertical[0]] = temp;
				break;

			}
			
			if(map[nx][ny] !=0) {
				dice[6] = map[nx][ny];
				map[nx][ny] = 0;
			}else {
				map[nx][ny] = dice[6];
			}
			
			cur = new Point(nx,ny);
			
//			for (int j = 1; j < 7; j++) {
//				sb.append(j + ": " + dice[j] + " ");
//			}
			sb.append(dice[1]).append("\n"); // 주사위 위에꺼 출력
		}

		System.out.println(sb);
	}
}
