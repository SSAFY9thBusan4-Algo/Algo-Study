import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Robot {
		int no, x, y;
		int dir;

		public Robot(int no, int x, int y) {
			super();
			this.no = no;
			this.x = x;
			this.y = y;;
		}
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()); // 가로
		int b = Integer.parseInt(st.nextToken()); // 세로
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Robot[][] arr = new Robot[b+1][a+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // C
			int y = Integer.parseInt(st.nextToken()); // R
			
			arr[y][x] = new Robot(i, y, x); // 맵상에서 로봇 위치 저장
			
			switch (st.nextToken()) { // 방향
			case "N":
				arr[y][x].dir = 0; // 북
				break;
			case "E":
				arr[y][x].dir = 1; // 동
				break;
			case "S":
				arr[y][x].dir = 2; // 남
				break;
			case "W":
				arr[y][x].dir = 3; // 서
				break;
			}
		}
		
		for(int cmd=0; cmd<m; cmd++) { // 명령
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			String turn = st.nextToken(); // 명령의 종류
			int cnt = Integer.parseInt(st.nextToken()); // 명령 횟수
			
			for(int i=b; i>=1; i--) {
				for(int j=1; j<=a; j++) {
					if(arr[i][j] == null) continue;
					
					if(arr[i][j].no == idx) { // 해당 번호의 로봇 명령 실행
						Robot robot = arr[i][j];
						
						int nx, ny;
						while(cnt-- > 0) {
							switch (turn) {
							case "L":
								if(robot.dir == 0) { // 왼쪽 회전
									robot.dir = 3;
								} else {
									robot.dir--;
								};
								break;
							case "R":
								if(robot.dir == 3) { // 오른쪽 회전
									robot.dir = 0;
								} else {
									robot.dir++;
								};
								break;
							case "F":
								switch(robot.dir) {
								case 0: // 북
									nx = robot.x + 1;
									if(nx > b) { // 벽에 충돌
										System.out.println("Robot " + robot.no + " crashes into the wall");
										return;
									}
									if(arr[nx][j] != null) { // Y번 로봇에 충돌
										System.out.println("Robot " + robot.no + " crashes into robot " + arr[nx][j].no);
										return;
									}
									
									robot.x++;
									break;
								case 1: // 동
									ny = robot.y + 1;
									if(ny > a) { // 벽에 충돌
										System.out.println("Robot " + robot.no + " crashes into the wall");
										return;
									}
									if(arr[i][ny] != null) { // Y번 로봇에 충돌
										System.out.println("Robot " + robot.no + " crashes into robot " + arr[i][ny].no);
										return;
									}
									
									robot.y++;
									break;
								case 2: // 남
									nx = robot.x - 1;
									if(nx < 1) { // 벽에 충돌
										System.out.println("Robot " + robot.no + " crashes into the wall");
										return;
									}
									if(arr[nx][j] != null) { // Y번 로봇에 충돌
										System.out.println("Robot " + robot.no + " crashes into robot " + arr[nx][j].no);
										return;
									}
									
									robot.x--;
									break;
								case 3: // 서
									ny = robot.y - 1;
									if(ny < 1) { // 벽에 충돌
										System.out.println("Robot " + robot.no + " crashes into the wall");
										return;
									}
									if(arr[i][ny] != null) { // Y번 로봇에 충돌
										System.out.println("Robot " + robot.no + " crashes into robot " + arr[i][ny].no);
										return;
									}
									
									robot.y--;
									break;
								}
								break;
							}
						}
						
						// 명령 실행 후 맵상에서 위치 변경 해주기
						arr[i][j] = null;
						arr[robot.x][robot.y] = robot;
					}
				}
			}
		}
		
		System.out.println("OK");
	}
}
