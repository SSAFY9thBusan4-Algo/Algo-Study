package week3.BOJ_1992_S1_쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int[][] map;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {				
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		makeQuad(new int[] {0,0}, N);		
		
		System.out.println(sb);
	}

	private static void makeQuad(int[] start, int n) {
		
		int check = map[start[0]][start[1]];
		boolean flag = true;
		for1:
		for (int i = start[0]; i < (start[0]+n); i++) {
			for (int j = start[1]; j < (start[1]+n); j++) {
				if (map[i][j] != check) {
					int nn = (int)(n/2);
					sb.append("(");
					makeQuad(start, nn);
					
					makeQuad(new int[] {start[0], start[1]+nn}, nn);
					
					makeQuad(new int[] {start[0]+nn, start[1]}, nn);
					
					makeQuad(new int[] {start[0]+nn, start[1]+nn}, nn);
					sb.append(")");
					flag = false;
					break for1;
				}
			}
		}
		if (flag) sb.append(check);
		
	}
	
}
