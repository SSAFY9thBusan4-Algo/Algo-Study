package src.week25.BOJ_17086_S2_아기상어2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main17086 {
	
	private static int N, M;
	private static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		
		map = new char[N][M];
		for(int i = 0 ; i < N ; i++) {
			in = br.readLine().split(" ");
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = in[j].charAt(0);
			}
		}
		
		
		int max = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == '0') {
					int n = safeLength(i,j);
					if(n > max) max = n;
				}
			}
		}
		System.out.println(max);
	}

	private static final int[][] dir = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	private static int safeLength(int i, int j) {

		int cnt = 1;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i,j});
		boolean[][] visited = new boolean[N][M];
		visited[i][j] = true;
		
		loop : while(!queue.isEmpty()) {
			int size = queue.size();
			while(--size >= 0) {
				int[] cur = queue.poll();
				
				for(int[] d : dir) {
					int dx = cur[0] + d[0];
					int dy = cur[1] + d[1];
					
					if(dx >= 0 && dx < N && dy >= 0 && dy < M && !visited[dx][dy]) {
						if(map[dx][dy] == '1') break loop;
						visited[dx][dy] = true;
						queue.offer(new int[] {dx,dy});
					} 
				}
			}
			cnt++;
		}
		
		return cnt;
	}
}
