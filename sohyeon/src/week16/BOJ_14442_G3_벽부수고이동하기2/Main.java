package week16.BOJ_14442_G3_벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int result = Integer.MAX_VALUE;
	static int N,M,K;
	static char[][] map;	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();			
		}

		bfs();
		
		if (result==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static void bfs() {
		boolean[][][] visited = new boolean[N][M][K+1];
		visited[0][0][K] = true;
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {0,0,K,1});
		
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			int cost = cur[3];
			
			if (r==N-1 && c==M-1) {			
				result = Math.min(result, cost);
				return;
			}
			
			for (int d=0; d<4; d++) {
				
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (isin(nr,nc)) {
					
					if (map[nr][nc] == '0' && !visited[nr][nc][cnt]) {
						visited[nr][nc][cnt] = true;						
						queue.offer(new int[] {nr,nc,cnt, cost+1});				
					}
					else if (map[nr][nc] == '1') {
						if(cnt>0 && !visited[nr][nc][cnt-1]) {						
							visited[nr][nc][cnt-1] = true;							
							queue.offer(new int[] {nr,nc,cnt-1, cost+1});
						}
					}
					
				}
			}			
		}
			
	}
	private static boolean isin(int r, int c) {
		if (0<=r && r<N && 0<=c && c<M) return true;
		else return false;
	}
	
}
