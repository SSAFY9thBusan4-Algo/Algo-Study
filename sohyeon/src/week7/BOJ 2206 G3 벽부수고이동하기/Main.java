package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	private static int N,M;
	private static int[][] map;
	private static int[][][] visited;
	private static int[] dr = {-1,0,1,0};
	private static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = in.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new int[N][M];
		visited = new int[N][M][2];
		
		for (int i=0; i<N; i++) {
			map[i] = Arrays.stream(in.readLine().split("")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		bfs(0,0); 
		
	}

	private static void bfs(int row, int col) {
		
		boolean flag = true;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {row, col, 1});
		visited[row][col][1] = 1;
		
		while (!queue.isEmpty()) {
			int[] poll = queue.poll();
			int r = poll[0];
			int c = poll[1];
			int check = poll[2];
			
			if (r == N-1 && c == M-1) {
				flag = false;
				System.out.println(visited[r][c][check]);
				break;
			}
			
			for (int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if (0<=nr && nr<N && 0<=nc && nc<M) {
					if (map[nr][nc] == 0 && visited[nr][nc][check] == 0) {
						queue.offer(new int[] {nr, nc, check});
						visited[nr][nc][check] = visited[r][c][check] + 1;
					}
					else if (map[nr][nc] == 1 && check == 1) {
						queue.offer(new int[] {nr, nc, 0});
						visited[nr][nc][0] = visited[r][c][1] + 1;
					}
				}
			}
		}
		
		if (flag) System.out.println(-1);
		
		
	}
	
}
