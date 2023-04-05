package week10.BOJ_14499_G4_주사위굴리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14499_G4_주사위굴리기 {
	private static StringBuilder sb = new StringBuilder();

	// 주사위의 한면을 알면  반대쪽 면은 7-현재면번호로 구할 수 있어서 현재 바닥면, 오른쪽면, 뒷면만 저장
	public static class Dice {
		int x; // x좌표
		int y; // y좌표
		int cur; // 현재 바닥면
		int right; // 오른쪽면
		int back; // 뒷면

		public Dice(int x, int y, int cur, int right, int back) {
			super();
			this.x = x;
			this.y = y;
			this.cur = cur;
			this.right = right;
			this.back = back;
		}

		@Override
		public String toString() {
			return "Dice [x=" + x + ", y=" + y + ", cur=" + cur + ", right=" + right + ", back=" + back + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int diceX = Integer.parseInt(st.nextToken());
		int diceY = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Dice dice = new Dice(diceX, diceY, 6, 3, 2); // 현재 주사위 정보(xy좌표, 현재 바닥면(6), 오른쪽면(3), 뒷면(2)
		int[] diceNum = new int[7]; // index 주사위면의 수
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < K; i++) {
			int direction = Integer.parseInt(st.nextToken());
			
			int nx = dice.x;
			int ny = dice.y;
			int cur = dice.cur;
			int right = dice.right;
			int back = dice.back;
			
			// 주사위 굴리기
			switch (direction) {
			case 1: // 동(cur, left, right만 변함)
				ny += 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					dice.y=ny;
					dice.cur=right;
					dice.right=7-cur;
				}
				else {
					continue;
				}
				break;
			case 2: // 서(cur, left, right만 변함)
				ny -= 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					dice.y=ny;
					dice.cur=7-right;
					dice.right=cur;
				}
				else {
					continue;
				}
				break;
			case 3: // 북(left, right는 변하지 않음)
				nx -= 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					dice.x=nx;
					dice.cur=back;
					dice.back=7-cur;
				}
				else {
					continue;
				}
				break;
			case 4: // 남(left, right는 변하지 않음)
				nx += 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					dice.x=nx;
					dice.cur=7-back;
					dice.back=cur;
				}
				else {
					continue;
				}
				break;
			}
			
			// 주사위 & 지도 수 업데이트
			if(map[nx][ny]==0) { // 이동한 칸에 쓰여있는 수가 0일 때
				map[nx][ny]=diceNum[dice.cur]; // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
			}
			else {
				diceNum[dice.cur]=map[nx][ny]; // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
				map[nx][ny]=0; // 칸에 쓰여 있는 수 0으로 변경
			}
			
			sb.append(diceNum[7-dice.cur]).append("\n"); // 이동한 주사위의 상단면의 값 출력

		}
		System.out.println(sb);
	}
}
