package src.week7.BOJ_1743_S1_음식물피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	private static boolean[][] map;
	private static int maxSize, count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		map = new boolean[N+2][M+2];
		int x, y;
		for(int i = 0 ; i < K; i++) {
			input = br.readLine().split(" ");
			x = Integer.parseInt(input[0]);
			y = Integer.parseInt(input[1]);
			map[x][y] = true;
		}
		
		for(int i = 0 ; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
		// solve
		for(int i = 1 ; i <= N; i++) {
			for(int j = 1 ; j <= M; j++) {
				if(map[i][j]) {
					count = 0;
					visite(i,j);
					if(count > maxSize) maxSize = count;
				}
			}
		}
		
		System.out.println(maxSize);
	}

	private static final int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	private static void visite(int i, int j) {
		// 1. visite
		map[i][j] = false;
		count++;
		
		// 2. move
		for(int d = 0 ; d < 4; d++) {
			if(map[i+delta[d][0]][j+delta[d][1]]) {
				visite(i+delta[d][0], j+delta[d][1]);
			}
		}
		
	}
}
