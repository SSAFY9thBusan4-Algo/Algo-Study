import java.io.*;
import java.util.*;

public class BOJ_1749_G4_점수따먹기 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//2차원 구간합 배열
		int d[][] = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				d[i][j] = d[i][j-1] + d[i-1][j] - d[i-1][j-1] + map[i][j];
			}
		}
		
		// 부분 행렬에서 최대값 구하기
		int ans = Integer.MIN_VALUE;
		
		for(int r1 = 1; r1 <= N; r1++) {
			for(int c1 = 1; c1 <= N; c1++) {
				for(int r2 = r1; r2 <= N; r2++) {
					for(int c2 = c1; c2 <= M; c2++) {
						int result = d[r2][c2] - d[r1-1][c2] - d[r2][c1-1] + d[r1-1][c1-1];
						ans = Math.max(ans, result);
					}
				}
			}
		}
		
		System.out.println(ans);
	}

}
