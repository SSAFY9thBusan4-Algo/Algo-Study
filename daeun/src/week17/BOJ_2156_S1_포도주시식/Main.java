import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int num[], dp[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		num = new int[N+1];
		dp = new int[N+1];
		for(int i=1;i<N+1;i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 0;
		dp[1] = num[1];
		if(N>1) { //2번째 잔은 무조건 2개 합
			dp[2] = num[1] + num[2];
		}
		
		for(int i=3;i<N+1;i++) {
			//지금 잔 선택 X vs 하나 앞 잔 선택 X vs 두개 앞 잔 선택 X
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2]+num[i], dp[i-3]+num[i-1]+num[i]));
		}
		
		System.out.println(dp[N]);
	}
}
