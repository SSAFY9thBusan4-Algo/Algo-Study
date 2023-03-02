package week4.BOJ_11057_S1_오르막수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11057_S1_오르막수 {
	
	StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		// 0	1 	2	3	4	5	6	7	8	9
		
		// 1 	1	1	1	1	1	1	1	1	1	: 10
		// 1 	2	3	4	5	6	7	8	9	10	: 55
		// 1	3	6	10	15	21	28	36	45	55	: 220
		// 1	4	10	20	35	56	84	120	165	220	: 714
		
		int[] dp = new int[11];
		for(int i=1; i<=10; i++) {
			dp[i] = 1;
		}
		
		
		for(int i=1; i<T; i++) {
			for(int j=1; j<=10; j++) {
				dp[j] += dp[j-1] % 10007;
			}
		}
		
		int sum = 0;
		for(int i = 0; i<dp.length; i++) {
			sum += dp[i];
		}
		
		System.out.println(sum % 10007);
		
	}
}
