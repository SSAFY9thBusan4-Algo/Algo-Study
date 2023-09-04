import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][N+1];
		
		//기본
		for(int i=1;i<N+1;i++) {
			//i개 중 1개를 선택하는 방법
			dp[i][1] = i;
			dp[i][0] = 1;
		}
		
		for(int i=3;i<N+1;i++) {
			//반만 고르고 반은 그대로 하면 되니까
			for(int j=2;j<=(i+1)/2;j++) {
				dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % 1000000003;
			}
		}
		
		//원형이니까 N-2에서 첫번째 경우도 빼야 해서 N-3
		System.out.println((dp[N-1][K] + dp[N-3][K-1]) % 1000000003);
	}
}
