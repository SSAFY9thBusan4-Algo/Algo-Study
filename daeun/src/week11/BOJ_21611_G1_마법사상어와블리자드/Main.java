import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class Main {
	static int N, M, idx, d[], s[], result;
	static int[][] map, number;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int[] next = { 2, 3, 1, 0 }; // 이거는 움직일때니까 또 따로 해줘야 되네...

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			split = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		d = new int[M];
		s = new int[M];
		for (int i = 0; i < M; i++) {
			split = br.readLine().split(" ");
			d[i] = Integer.parseInt(split[0]) - 1;
			s[i] = Integer.parseInt(split[1]);
		}
		// 상어는 map[N/2][N/2]에 있다.
		number = new int[N * N][2];
		int x = N / 2, y = N / 2;
		int X = 0, Y = 0;
		int dir = 2; // 시작 방향
		int num = 1; // 시작 숫자
		int move = 1; // 얼마나 이동해야 되는지 (달팽이 크기)

		out: 
		while (true) {
			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < move; i++) { // 움직이고
					if (x == 0 && y == 0) {
						break out;
					}
					X = x + dx[dir];
					Y = y + dy[dir];
					number[num][0] = X;
					number[num][1] = Y;
					num++;

					x = X;
					y = Y;
				}
				dir = next[dir]; // 다음 회전으로
			}
			move++;
		}

		for (int i = 0; i < M; i++) {
			magic(d[i], s[i]);
			move();
			while (true) {
				if (bomb()) {
					move();
				} else {
					break;
				}
			}
			group();
		}
		
		System.out.println(result);
	}

		//칸 번호대로 가면서 연결된 구슬 숫자를 세고
		//그걸 map에 구슬 개수 / 구슬 번호 2개로 넣기		
	private static void group() {
		int[][] gmap = new int[N][N];
		int cnt = 1; //하나도 그룹                      
		int num = 1; //1번부터 시작
		int x=0, y=0, X=0, Y=0;
		for(int i=1;i<N*N-1;i++) { //칸 번호
			x = number[i][0];
			y = number[i][1];
			
			if(map[x][y]==0) { //더이상 구슬이 없음
				break;
			}
			
			X = number[i+1][0];
			Y = number[i+1][1];
			if(map[x][y] == map[X][Y]) {
				cnt++;
			}
			else {
				if(num >= N*N) { //더이상 구슬 넣을 칸 없음
					break;
				}
				int inX = number[num][0];
				int inY = number[num][1];
				num++;
				if(num == N*N) { //새로운 자리가 칸 밖
					break;
				}
				int inX2 = number[num][0];
				int inY2 = number[num][1];
				num++;
				gmap[inX][inY] = cnt;
				gmap[inX2][inY2] = map[x][y]; //마지막으로 확인한 구슬 번호
				cnt = 1;
				
			}
		}		
        
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = gmap[i][j];
			}
		}
	}

	private static boolean bomb() {
		int x = N / 2, y = N / 2;
		int X = 0, Y = 0;
		int dir = 2;
		int move = 1;
		int cnt = 0; // 연속
		boolean flag = false;
		ArrayList<int[]> list = new ArrayList<>();
		
		int temp = map[x][y];
		out: 
		while (true) {
			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < move; i++) { // 움직이고
					if (x == 0 && y == 0) {
						break out;
					}
					
					X = x + dx[dir];
					Y = y + dy[dir];
					if(map[X][Y] == temp) {
						cnt++;
					}
					else {
						if(cnt > 3) {
							result += map[x][y] * cnt;
							flag = true;
							for(int[] now: list) {
								map[now[0]][now[1]] = 0;
							}
						}
						cnt = 1;
						list.clear();
						temp = map[X][Y];
					}
					
					list.add(new int[] {X, Y});

					x = X;
					y = Y;
				}
				dir = next[dir]; // 다음 회전으로
			}
			move++;
		}

		if (flag)
			return true;
		else
			return false;
	}

	private static void move() {
		for (int i = 1; i < N * N; i++) {
			int X = number[i][0];
			int Y = number[i][1];
			if (map[X][Y] == 0) {
				int[] now = find(i);
				map[X][Y] = map[now[0]][now[1]];
				map[now[0]][now[1]] = 0;
			}
		}
	}

	private static int[] find(int num) {
		int[] arr = new int[2];
		for (int i = num + 1; i < N * N; i++) {
			int X = number[i][0];
			int Y = number[i][1];
			if (map[X][Y] != 0) {
				arr[0] = X;
				arr[1] = Y;
				break;
			}

		}
		return arr;
	}

	private static void magic(int dir, int st) { // 반복문으로
		int startX = N / 2;
		int startY = N / 2;
		for (int i = 0; i < st; i++) {
			startX += dx[dir];
			startY += dy[dir];
			if (startX < 0 || startX >= N || startY < 0 || startY >= N) {
				break;
			}
			map[startX][startY] = 0;
		}
	}

}
