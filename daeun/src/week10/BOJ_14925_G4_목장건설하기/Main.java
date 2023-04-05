package week10.BOJ_14925_G4_목장건설하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = br.readLine().split(" ");
		int M = Integer.parseInt(split[0]);
		int N = Integer.parseInt(split[1]);
		boolean[][] map = new boolean[M][N];
		int[][] dp = new int[M+1][N+1];
		for(int i=0;i<M;i++) { //boolean 배열에 저장
			split = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				if(split[j].equals("0")) {
					map[i][j] = true; 
				}
				else {
					map[i][j] = false;
				}
			}
		}
		int max = 0;
		for(int i=1;i<M+1;i++) {
			for(int j=1;j<N+1;j++) {
				if(map[i-1][j-1]) {
					//사각형의 네 모서리가 되는 부분들 중 최소 dp가 목장을 건설할 수 있는 값임
					dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1])+1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		System.out.println(max);
	}
}