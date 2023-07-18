package week17.BOJ_18430_G4_무기공학;

import java.util.Scanner;

public class Main {
	
	static int N,M, result;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		visited = new boolean[N][M];
		dfs(0,0);
		
		System.out.println(result);
	}

	private static void dfs(int idx, int cost) {
		
		if (idx == N*M) {
			result = Math.max(result, cost);
			return;
		}
		
		// 그냥 이동
		dfs(idx+1, cost);

		int r = idx/M;
		int c = idx%M;		
		
		// 현재 위치에서 부메랑 모양 4개 각각 가능한지 확인
		if (isin(r,c) && isin(r,c-1) && isin(r+1,c)
				&& !visited[r][c] && !visited[r][c-1] && !visited[r+1][c]) {
			visited[r][c] = true;
			visited[r][c-1] = true;
			visited[r+1][c] = true;
			dfs(idx+1, cost+map[r][c]*2+map[r][c-1]+map[r+1][c]);
			visited[r][c] = false;
			visited[r][c-1] = false;
			visited[r+1][c] = false;
		}
		if (isin(r,c) && isin(r-1,c) && isin(r,c-1)
				&& !visited[r][c] && !visited[r-1][c] && !visited[r][c-1]) {
			visited[r][c] = true;
			visited[r][c-1] = true;
			visited[r-1][c] = true;
			dfs(idx+1, cost+map[r][c]*2+map[r][c-1]+map[r-1][c]);
			visited[r][c] = false;
			visited[r][c-1] = false;
			visited[r-1][c] = false;
		}
		if (isin(r,c) && isin(r-1,c) && isin(r,c+1)
				&& !visited[r][c] && !visited[r-1][c] && !visited[r][c+1]) {
			visited[r][c] = true;
			visited[r][c+1] = true;
			visited[r-1][c] = true;
			dfs(idx+1, cost+map[r][c]*2+map[r][c+1]+map[r-1][c]);
			visited[r][c] = false;
			visited[r][c+1] = false;
			visited[r-1][c] = false;
		}
		if (isin(r,c) && isin(r,c+1) && isin(r+1,c)
				&& !visited[r][c] && !visited[r][c+1] && !visited[r+1][c]) {
			visited[r][c] = true;
			visited[r][c+1] = true;
			visited[r+1][c] = true;
			dfs(idx+1, cost+map[r][c]*2+map[r][c+1]+map[r+1][c]);
			visited[r][c] = false;
			visited[r][c+1] = false;
			visited[r+1][c] = false;
		}
	}

	private static boolean isin(int r, int c) {
		if (0<=r && r<N && 0<=c && c<M) return true;
		else return false;
	}
	
}
