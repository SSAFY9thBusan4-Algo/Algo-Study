package week8.BOJ_2638_G3_치즈;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_G3_치즈 {

	public static int N;
	public static int M;
	public static int[][] map;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static List<int[]> contactCheck; // 외부 공기와 접촉하는지 확인 할 좌표를 넣을 list
	public static int time;

	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 외부 공기와 접촉하는 위치는 -1로 변경
		// 1. 처음에는 (0,0)부터 상하좌우 인접한 좌표들을 탐색하면서 공기 중 외부 공기와 접촉하는 값을 -1로 변경
		// 2. 2변 이상이 외부공기(-1)와 접촉하는 치즈를 찾아서 공기(0)으로 변경
		// 3. 2번에서 찾은 치즈가 공기(0)으로 변했으니 해당 치즈 좌표들부터 다시 1~2번 진행
		// 4. 더이상 2번에서 찾은 치즈가 없으면 break
		contactCheck = new ArrayList<>();
		contactCheck.add(new int[] {0,0});
		while (true) {
			// 공기 중 외부 공기와 접촉하는 부분 계산
			for(int i=0; i<contactCheck.size(); i++) {
				int x = contactCheck.get(i)[0];
				int y = contactCheck.get(i)[1];

				if(map[x][y]==0) { // 조건 처리해주니 공간/시간이 훨씬 많이 줄었다
					bfs(x,y);
				}
			}
			
			// 2변 이상이 외부공기와 접촉한 치즈 찾기
			contactCheck = new ArrayList<>();
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if(map[i][j]==1) { // 치즈중
						int count=0;
						for (int k = 0; k < 4; k++) {
							int nx = i+dx[k];
							int ny = j+dy[k];

							if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == -1) {// 외부공기와 접촉하지 않을 때
								count++;
							}
						}
						if(count>=2) {
							contactCheck.add(new int[] {i,j});
						}
					}
				}
			}
			
			if(contactCheck.size()==0) { // 치즈가 모두 녹았을 때
				break;
			}
			
			// 치즈 녹이기
			removeCheese();
		}

		sb.append(time);
		System.out.println(sb);
	}

	// 공기 중 외부 공기와 접촉하는 부분의 값 -1로 변경
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		map[x][y]=-1;

		while (!queue.isEmpty()) {
			int[] curXY = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = curXY[0] + dx[i];
				int ny = curXY[1] + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
					map[nx][ny]=-1;
					queue.offer(new int[] { nx, ny });
				}
			}
		}

	}
	
	// 치즈 녹이기
	private static void removeCheese() {
		time++;
		for(int i=0; i<contactCheck.size(); i++) {
			int[] cheeseXY = contactCheck.get(i);
			map[cheeseXY[0]][cheeseXY[1]]=0;
		}
	}
}
