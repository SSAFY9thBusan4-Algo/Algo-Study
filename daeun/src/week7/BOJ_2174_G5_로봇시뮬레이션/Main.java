package week7_BOJ_2174_G5_로봇시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class Robot {
		int x;
		int y;
		int dir;

		public Robot(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	static Robot[] robots;
	static int dy[] = { 1, 0, -1, 0 };
	static int dx[] = { 0, 1, 0, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int A = Integer.parseInt(split[0]);
		int B = Integer.parseInt(split[1]);
		int[][] map = new int[B][A];
		split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		robots = new Robot[N + 1]; // N개의 로봇
		int x, y, dir = 0;
		for (int i = 1; i < N + 1; i++) { // 로봇 위치 x, y, 방향
			split = br.readLine().split(" ");
			x = Integer.parseInt(split[0]) - 1;
			y = Integer.parseInt(split[1]) - 1;
			if (split[2].equals("N")) {
				dir = 0;
			} else if (split[2].equals("E")) {
				dir = 1;
			} else if (split[2].equals("S")) {
				dir = 2;
			} else if (split[2].equals("W")) {
				dir = 3;
			}
			robots[i] = new Robot(x, y, dir); // 로봇 배열에 넣고
			map[y][x] = i; // 몇번 로봇인지 넣기 => 0이면 없을 때와 헷갈리니까 반복문 1부터 시작
		}
		String nfr;
		int num, robot, X = 0, Y = 0;
		boolean flag = true;
		out: 
		for (int i = 0; i < M; i++) {
			split = br.readLine().split(" ");
			robot = Integer.parseInt(split[0]); // 어떤 로봇
			nfr = split[1]; // 명령어 왼쪽, 오른쪽, 앞으로
			num = Integer.parseInt(split[2]);
			for (int j = 0; j < num; j++) {
				Robot now = robots[robot];
				dir = now.dir; //이거 안 해서 한참 찾았다...
				if (nfr.equals("L")) {
					dir--;
					if (dir == -1) {
						dir = 3;
					}
					robots[robot] = new Robot(now.x, now.y, dir);
				} else if (nfr.equals("R")) {
					dir++;
					if (dir == 4) {
						dir = 0;
					}
					robots[robot] = new Robot(now.x, now.y, dir);
				} else if (nfr.equals("F")) {
					X = now.x + dx[now.dir];
					Y = now.y + dy[now.dir];
					if (X < 0 || X >= A || Y < 0 || Y >= B) { // 벽에 충돌
						System.out.print("Robot " + (robot) + " crashes into the wall");
						flag = false;
						break out;
					} else if (map[Y][X] != 0) { // 다른 로봇이 있다면
						System.out.print("Robot " + (robot) + " crashes into robot " + (map[Y][X]));
						flag = false;
						break out;
					}
					map[now.y][now.x] = 0;
					map[Y][X] = robot;
					robots[robot] = new Robot(X, Y, now.dir);
				}

			}
		}

		if (flag) {
			System.out.print("OK");
		}
	}
}
