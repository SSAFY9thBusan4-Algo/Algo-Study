import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int[][] map = new int[N+1][M+1];
		int[][] dp = new int[N+1][M+1];
		for(int i=0;i<N;i++) {
			split = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i+1][j+1] = Integer.parseInt(split[j]);
			}
		}
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<M+1;j++) {
				//대각선보다 하나 거쳐서 가는게 더 많은 사탕을 먹을 수 있음
				dp[i][j] = Math.max(map[i][j]+dp[i-1][j], map[i][j]+dp[i][j-1]);
			}
		}
		
		System.out.println(dp[N][M]);
	}
}
