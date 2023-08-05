import java.io.*;
import java.util.*;

public class BOJ_17086_S3_아기상어2 {
	
	static int N, M;
	static boolean isVisited[][];
	static int map[][];
	
	// 인접방향으로 이동하기 위한 배열
	static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static class Pos {
		int r;
		int c;
		int dis;
		
		public Pos(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}
	}
	
	static int ans = Integer.MIN_VALUE; // 안전 거리의 최댓값 
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					isVisited = new boolean[N][M];
					BFS(i,j);
				}
			}
		}
		
		System.out.print(ans);
		
	}
	
	public static void BFS(int i, int j) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(i, j, 0));
		isVisited[i][j] = true;
		boolean flag = false;
		
		while(!queue.isEmpty()) {
			Pos curPos = queue.poll();
			
			for(int k = 0; k<8; k++) {
				int nr = curPos.r + dr[k];
				int nc = curPos.c + dc[k];
				
				if(0 <= nr && nr < N && 0 <= nc && nc < M) {
					if(!isVisited[nr][nc]) {
						// 다음 이동 칸에 아기상어가 없다면
						if(map[nr][nc] == 0) {
							queue.offer(new Pos(nr, nc, curPos.dis + 1));
							isVisited[nr][nc] = true;
						}
						// 다음 이동 칸에 아기상어가 있다면 
						else if(map[nr][nc] == 1) {
							ans = Math.max(ans, curPos.dis + 1);
							flag = true;
							break;
						}
					}
				}
			}
			
			if(flag) break;
		}
	}

}
