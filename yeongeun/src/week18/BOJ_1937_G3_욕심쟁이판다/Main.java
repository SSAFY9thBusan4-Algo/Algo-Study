package src.week18.BOJ_1937_G3_욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[][] map;
	private static int[][] depth;
	private static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		depth = new int[N][N];
		
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// solve
		int result = 1;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N; j++) {
				if(depth[i][j] == 0) dfs(i, j);
				if(depth[i][j] > result) result = depth[i][j];
			}
		}
		
		System.out.println(result);
	}

	private static void dfs(int i, int j) {
		
		int max = 0;
		for(int d = 0 ; d < 4 ; d++) {
			int dx = i + delta[d][0];
			int dy = j + delta[d][1];
			
			if(dx < 0 || dx >= N || dy < 0 || dy >= N) continue;
			if(map[dx][dy] <= map[i][j]) continue;
			
			if(depth[dx][dy] == 0) dfs(dx,dy);
			if(depth[dx][dy] > max) max = depth[dx][dy];
		}
		
		depth[i][j] = max+1;
		
	}
}
