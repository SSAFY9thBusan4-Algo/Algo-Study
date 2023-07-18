import java.io.*;
import java.util.*;

public class BOJ_17836_G5_공주님을구해라 {
	
	/*
	 * 메모리 14828KB 시간 140ms
	 */

	static class Pos { // 위치 클래스 선언
		int r; //행
		int c; //열
		int time; //시간
		boolean isGram; //그람 여부

		public Pos(int r, int c, int time, boolean isGram) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
			this.isGram = isGram;
		}
	}

	static int N; // 세로 길이
	static int M; // 가로 길이
	static int limitTime; // 제한 시간

	static int[][] map;

	static int[] dr = {-1, 1, 0, 0}; //상하좌우
	static int[] dc = {0, 0, -1, 1};
	static boolean[][][] isVisited; //방문 배열, 그람을 가졌을 때와 안 가졌을 때 방문 배열을 따로 처리한다. 

	static int ans = Integer.MAX_VALUE; //최단 시간(답)

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken()); //세로 길이
		M = Integer.parseInt(st.nextToken()); //가로 길이
		limitTime = Integer.parseInt(st.nextToken()); //제한 시간

		map = new int[N][M];
		isVisited = new boolean[N][M][2];

		for(int i=0; i<N; i++) { //map 배열 입력 받기
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs(); 
		
		if(ans == Integer.MAX_VALUE)
			System.out.println("Fail");
		else 
			System.out.println(ans);
	}

	private static void bfs() {
		Queue<Pos> queue = new ArrayDeque<Pos>();
		queue.offer(new Pos(0, 0, 0, false));
		isVisited[0][0][0] = true;
			
		while(!queue.isEmpty()) {

			Pos curPos = queue.poll();
			if(curPos.time > limitTime) continue; //제한 시간을 초과할 경우 continue
			if(curPos.r == N-1 && curPos.c == M-1) { //공주님에게 도착했을 경우 최단 시간 계산
				ans = ans < curPos.time ? ans : curPos.time;
			}

			for(int i=0; i<4; i++) { //인접 배열로 이동한다. 
				int nr = curPos.r + dr[i];
				int nc = curPos.c + dc[i];

				if(0<=nr && nr < N && 0<=nc && nc < M) { // map 배열 범위 안에 있을 때
					if(!isVisited[nr][nc][0] && !curPos.isGram) { //그람을 가지고 있지 않을 때
						if(map[nr][nc] != 1) { // 벽 말고는 갈 수 있다. 
							isVisited[nr][nc][0] = true;
							if(map[nr][nc] == 0)
								queue.offer(new Pos(nr, nc, curPos.time + 1, false));
							else // 그람을 주웠을 때
								queue.offer(new Pos(nr, nc, curPos.time + 1, true));
						}
					}
					else if(!isVisited[nr][nc][1] && curPos.isGram) { //그람을 가지고 있을 때
						isVisited[nr][nc][1] = true;
						queue.offer(new Pos(nr, nc, curPos.time + 1, true));
					}
				}
			}
		}
	}
}


