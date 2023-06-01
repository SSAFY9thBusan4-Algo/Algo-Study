package week16.BOJ_14620_S2_꽃길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		int result = Integer.MAX_VALUE;
		for (int i=0; i<N*N; i++) {
			for (int j=0; j<N*N; j++) {
				for (int k=0; k<N*N; k++) {
					result = Math.min(result, seedflower(new int[] {i,j,k}));
				}
			}
		}
		
		System.out.println(result);
		
	}

	static int[] dr = {0,1,0,-1,0};
	static int[] dc = {0,0,1,0,-1};
	private static int seedflower(int[] list) {
		
		boolean[][] visited = new boolean[N][N];
		int cost =0;
		for (int i : list) {
			int r = i/N;
			int c = i%N;
			
			if (r==0 || r==N-1 || c==0 || c==N-1) return 3000;
						
			for (int j=0; j<5; j++) {
				if (!visited[r+dr[j]][c+dc[j]]) {
					visited[r+dr[j]][c+dc[j]] = true;
					cost += map[r+dr[j]][c+dc[j]];
				}
				else return 3000;
			}
		}		
		return cost;
	}
	
}
