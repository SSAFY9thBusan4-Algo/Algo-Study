import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][m];
		int[][] d = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] == 0) d[i][j] = 1;
			}
		}
		
		for(int i=1; i<n; i++) {
			for(int j=1; j<m; j++) {
				if(arr[i][j] != 0) continue;
				if(arr[i-1][j-1]!=0 || arr[i-1][j]!=0 || arr[i][j-1]!=0) continue;
				
				d[i][j] = Math.min(Math.min(d[i-1][j-1], d[i-1][j]), d[i][j-1]) + 1;
			}
		}
		
		int ans = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				ans = Math.max(ans, d[i][j]);
			}
		}
		
		System.out.println(ans);
	}
}
