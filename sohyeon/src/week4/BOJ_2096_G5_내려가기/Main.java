package week4.BOJ_2096_G5_내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	private static int[][] delta = {{-1,-1},{-1,0},{-1,1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
				
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(in.readLine());
		
		int[][] map = new int[N][3];
		for (int i = 0; i < N; i++ ) {
			map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		int[][] mindp = new int[N][3];
		int[][] maxdp = new int[N][3];
		for (int i = 0; i < N; i++) {
			mindp[i] = Arrays.copyOf(map[i],3);
			maxdp[i] = Arrays.copyOf(map[i],3);
		}		
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				int minv = 9*N, maxv = 0;
				for (int k = 0; k < 3; k++) {
					int nr = i+delta[k][0];
					int nc = j+delta[k][1];					
					if (0<=nr && nr<N-1 && 0<=nc && nc<3) {
						minv = Math.min(minv, mindp[nr][nc]);
						maxv = Math.max(maxv, maxdp[nr][nc]);
					}					
				}
				mindp[i][j] += minv;
				maxdp[i][j] += maxv;
			}
		}
		
		int minsum = 9*N, maxsum = 0;
		for (int i = 0; i < 3; i++) {
			minsum = Math.min(minsum, mindp[N-1][i]);
			maxsum = Math.max(maxsum, maxdp[N-1][i]);
		}
		
		System.out.println(maxsum+" "+minsum);
		
	}
	
}
