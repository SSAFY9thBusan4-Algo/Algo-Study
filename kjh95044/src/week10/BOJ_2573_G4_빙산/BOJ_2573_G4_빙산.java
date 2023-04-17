package com.jihong.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산 {

	static StringBuilder sb = new StringBuilder();
	static int N, M, map[][], iceCnt, result;
	static boolean[][] visited;
	
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
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					iceCnt++;
				}
			}
		}

		while (iceCnt > 0) {
			visited = new boolean[N][M];
			result++;
			
			// 빙산 다 녹을때 까지 녹인다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) {
						meltIce(new Point(i, j));
					}
				}
			}

			// 테스트용 출력
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					sb.append(map[i][j]);
//				}
//				sb.append("\n");
//			}
//			sb.append("\n");

			// 두조각 이상으로 쪼개지면 끝낸다.
			if(isMoreThenTwo()) {
				break;
			}
		}
		
		// 빙산이 다 녹아서 끝나 버린 경우는 0을 출력해야한다.
		if(iceCnt==0) {
			result = 0;
		}
		sb.append(result);
		System.out.println(sb);
	}

	private static boolean isMoreThenTwo() {
		int cnt = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 방문 하지 않았고, 0이 아닌걸 새로 발견하면 cnt 증가
				// 새로운걸 발견하면 bsf를 통해 인접한 모든 곳 방문처리
				
				if(!visited[i][j] && map[i][j] != 0) {
					bfs(new Point(i,j));
					cnt++;
				}
				
				
				if(cnt>=2) { // 2조각 이상이면 true 리턴
					return true;
				}
			}
		}
		
		return false;
	}
	
	

	private static void bfs(Point p) {
		Queue<Point> q = new ArrayDeque<>();
		visited[p.x][p.y] = true;
		q.offer(p);
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + delta[d][0];
				int ny = cur.y + delta[d][1];

				if(!(nx>=0 && ny>=0 && nx<N && ny<M)) continue;
				
				if(!visited[nx][ny] && map[nx][ny]!=0) {
					visited[nx][ny] = true;
					q.offer(new Point(nx,ny));
				}
			}
		}
		
	}



	static int[][] delta = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private static void meltIce(Point p) {
		// 주변에 바다가 있는 만큼 빙산 높이 제거
		
		visited[p.x][p.y] = true; // 아직 1년이 안 지났는데 빙산이 다 녹아 바다가 되었을때  방문하는 것을 방지
		int waterCnt=0;
		
		for (int d = 0; d < 4; d++) {
			int nx = p.x + delta[d][0];
			int ny = p.y + delta[d][1];

			if(!(nx>=0 && ny>=0 && nx<N && ny<M)) continue;
			
			if(!visited[nx][ny] && map[nx][ny]==0) {
				waterCnt++;
			}
		}
		map[p.x][p.y] -= waterCnt;
		if(map[p.x][p.y] <0) { // 음수가 되었을때 0으로 바꿔줌
			map[p.x][p.y] = 0;
		}
		
		if(map[p.x][p.y] == 0) { // 빙산 갯수 갱신
			iceCnt -=1;
		}
	}
}
