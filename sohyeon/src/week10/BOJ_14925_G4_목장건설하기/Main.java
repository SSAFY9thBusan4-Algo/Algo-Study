package week10.BOJ_14925_G4_목장건설하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static int M,N;
	private static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		int result = 0;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = in.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		
		map = new int[M][N];
		
		for (int i=0; i<M; i++) {
			map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		if (map[0][0] == 0) result = 1;
		
		// 목장을 지을 수 있는땅 표시
		for (int r=0; r<M; r++) {
			for (int c=0; c<N; c++) {
				map[r][c] = map[r][c]==0 ? 1 : 0;
			}
		}
		
		for (int i=2; i<=Math.min(M, N); i++) {
			boolean flag = true; 
			for (int r=i-1; r<M; r++) {
				for (int c=i-1; c<N; c++) {
					if (map[r-1][c-1]>=i-1 && map[r][c-1]>=i-1 && map[r-1][c]>=i-1
							&& map[r][c]==i-1) {
						map[r][c] = i;
						result = Math.max(result, i);
						flag = false;
					}
				}
			}
//			for (int j=0; j<M; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
//			System.out.println();
			
			if (flag) break;
			
		}
		
		System.out.println(result);
		
	}
	
}
