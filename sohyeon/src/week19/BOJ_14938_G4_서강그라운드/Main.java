package week19.BOJ_14938_G4_서강그라운드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] t = new int[n];
		t = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		
		int[][] dp = new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (i==j) dp[i][j] = 0;
				else dp[i][j] = 987654321;
			}
		}
		int a,b,c;
		for (int i=0; i<r; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken());
			dp[a][b] = c;
			dp[b][a] = c;
		}
		
		for (int k=0; k<n; k++) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k][j]);
				}
			}
		}
		
		int result = 0;
		for (int i=0; i<n; i++) {
			int sum = 0;  //i에서 시작했을때 얻을 수 있는 아이템 개수
			for (int j=0; j<n; j++) {
				if (dp[i][j]<=m) sum+=t[j];
			}
			result = Math.max(result, sum);
		}
		
		System.out.println(result);
	}
	
}
