package week7.BOJ_2174_G5_로봇시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2174_G5_로봇시뮬레이션 {

	public static class Robot {
		int x;
		int y;
		int directioin;

		public Robot(int x, int y, int directioin) {
			super();
			this.x = x;
			this.y = y;
			this.directioin = directioin;
		}

	}

	public static class Command {
		int no; // 명령어 실행할 로봇 번호
		String command; // 명령어
		int count; // 명령어 반복 횟수

		public Command(int no, String command, int count) {
			super();
			this.no = no;
			this.command = command;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();

		int A = Integer.parseInt(st.nextToken()); // 가로(열)
		int B = Integer.parseInt(st.nextToken()); // 세로(행)

		st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int dx[] = { -1, 0, 1, 0 }; // 순서대로 NESW방향
		int dy[] = { 0, 1, 0, -1 };

		// 로봇 정보 담기
		Robot[] robots = new Robot[N + 1];
		int map[][] = new int[B + 1][A + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			String d = st.nextToken();

			// 문제에서는 아래에서 위로 map이 구성되어있지만 이를 위에서 아래로 변경
			// 좌우 방향은 똑같지만 상하방향은 바뀜
			// 명령어 L과 R또한 서로 바뀜
			int direction = 0;
			if (d.equals("N")) {
				direction = 2;
			} else if (d.equals("E")) {
				direction = 1;
			} else if (d.equals("S")) {
				direction = 0;
			} else if (d.equals("W")) {
				direction = 3;
			}
			robots[i] = new Robot(x, y, direction);
			map[x][y] = i;
		}

		// 명령어 정보 담기
		Command[] commands = new Command[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int no = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			int count = Integer.parseInt(st.nextToken());
			commands[i] = new Command(no, command, count);
		}

		boolean status = true; // 잘못된 명령인지 여부
		out: for (int i = 0; i < M; i++) {
			Command command = commands[i];
			Robot robot = robots[command.no]; // 명령어를 실행할 로봇
			int robotX = robot.x;
			int robotY = robot.y;
			int robotD = robot.directioin;

			int nx = robotX;
			int ny = robotY;
			for (int j = 0; j < command.count; j++) { // 명령어의 반복횟수만큼 반복
				if (command.command.equals("L")) { // 명령어 L일 때(입력이 상하 반전이니 회전도 반대)
					robotD += 1;
					if (robotD > 3) {
						robotD = 0;
					}
				} else if (command.command.equals("R")) {// 명령어 R일 때(입력이 상하 반전이니 회전도 반대)
					robotD -= 1;
					if (robotD < 0) {
						robotD = 3;
					}
				} else if (command.command.equals("F")) {// 명령어 F일 때
					nx += dx[robotD];
					ny += dy[robotD];

					if (nx <= 0 || nx > B || ny <= 0 || ny > A) { // 로봇이 벽에 충돌하는 경우
						status = false;
						sb.append("Robot ").append(command.no).append(" crashes into the wall");
						break out;
					} else {
						if (map[nx][ny] > 0) {// 로봇과 로봇이 충돌하는 경우
							status = false;
							sb.append("Robot ").append(command.no).append(" crashes into robot ").append(map[nx][ny]);
							break out;
						}
					}
				}
			}
			robots[command.no] = new Robot(nx, ny, robotD); // 명령어 실행 후 로봇의 좌표, 방향 업데이트
			map[robotX][robotY] = 0; // map에 표시된 로봇의 정보 수정
			map[nx][ny] = command.no;
		}

		if (status) {
			sb.append("OK");
		}

		System.out.println(sb.toString());
	}
}
