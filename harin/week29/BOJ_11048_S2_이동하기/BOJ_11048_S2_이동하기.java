import java.io.*;
import java.util.*;

public class BOJ_11048_S2_이동하기 {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int dp[][] = new int[N+1][M+1];
		
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(in.readLine());
			for(int c=1; c<=M; c++) {
				dp[r][c] = Integer.parseInt(st.nextToken());
				
				int max = Math.max(dp[r-1][c-1], Math.max(dp[r-1][c], dp[r][c-1]));
				dp[r][c] += max;
			}
		}
		
		System.out.println(dp[N][M]);	
	}	
}
