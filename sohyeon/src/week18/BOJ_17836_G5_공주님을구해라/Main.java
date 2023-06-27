package week18.BOJ_17836_G5_공주님을구해라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	static int N,M,T, result;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[] nmt = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		N = nmt[0];
		M = nmt[1];
		T = nmt[2];
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		result = Integer.MAX_VALUE;
		bfs();
		
		System.out.println(result==Integer.MAX_VALUE ? "Fail" : result);
		
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static void bfs() {
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {0,0,0});
		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;
		
		int r,c,nr,nc,cost;
		while (!queue.isEmpty()) {		
			
			int[] poll = queue.poll();
			r = poll[0];
			c = poll[1];
			cost = poll[2];
			
			if (r==N-1 && c==M-1) {				
				result = Math.min(result, cost);
				break;
			}			
			if (cost == T) break;
			
			for (int d=0; d<4; d++) {
				nr = r+dr[d];
				nc = c+dc[d];
				
				if (isin(nr,nc) && !visited[nr][nc] && map[nr][nc]!=1) {
					visited[nr][nc] = true;
					
					// 그람이 있는 위치라면
					if (map[nr][nc] == 2) {
						int dist = cost+1 + (N-1-nr) + (M-1-nc);
						if (dist <= T) {
							result = Math.min(result, dist);							
						}
						break;
					}
					// 아니면
					else {
						queue.offer(new int[] {nr,nc, cost+1});					
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
