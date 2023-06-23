import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String a = br.readLine();
		String b = br.readLine();
		int n = a.length();
		int m = b.length();
		
		// LCS(Longest Common Subsequence, 최장 공통 부분 수열)
		int[][] d = new int[n+1][m+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) { 
					d[i][j] = d[i-1][j-1] + 1; // 비교하기 이전수열의 최대값
				}
				else {
					d[i][j] = Math.max(d[i-1][j], d[i][j-1]); // 
				}
			}
		}
		
		// 역추적
		int cnt = d[n][m];
		int x = n;
		int y = m;
		while(cnt > 0) {
			if(d[x-1][y] == cnt) x--;
			else if(d[x][y-1] == cnt) y--;
			else {
				sb.append(a.charAt(x-1));
				x--;
				y--;
				cnt--;
			}
		}
		
		System.out.println(sb.reverse());
	}
}
