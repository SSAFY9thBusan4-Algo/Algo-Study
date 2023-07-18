package week18.BOJ_1937_G3_욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int n;
	static int[][] map;
	static int[][] dp;
	static int result;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(in.readLine());
		map = new int[n][n];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		dp = new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				result = Math.max(result, dfs(i,j));
			}
		}
			
		System.out.println(result);
	}

	private static int dfs(int r, int c) {
		
		if (dp[r][c] != 0) return dp[r][c];
		
		dp[r][c] = 1;
		for (int d=0; d<4; d++) {
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if (isin(nr,nc) && map[r][c] < map[nr][nc]) {
				dp[r][c] = Math.max(dp[r][c], dfs(nr,nc)+1);
			}			
		}
		
		return dp[r][c];
	}

	private static boolean isin(int r, int c) {
		if (0<=r && r<n && 0<=c && c<n) return true;
		else return false;
	}
	
}
