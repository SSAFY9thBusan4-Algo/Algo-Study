package src.week4.BOJ_11052_S1_카드구매하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		
		int[] price = new int[N+1];	// 입력받은 가격
		for(int i = 0 ; i < N ; i++) {
			price[i+1] = Integer.parseInt(input[i]);	// 1장 팩 가격 -> 1번 인덱스
		}
		
		// solve
		
		int[] dp = new int[N+1];	// 각 장을 가장 비싸게 샀을때 사는 가격
		dp[1] = price[1];
		
		for(int i = 2; i <= N; i++) {
			int max = price[i];		// 팩으로 바로 사거나
			
			for(int j = 1 ; j <= i/2 ; j++) {
				int count = dp[j] + dp[i-j];	//j개 사는 가격 + (i-j)개를 사는 가격
				if(max < count) max = count;
			}
			
			dp[i] = max;	// 가장 비싼 가격을 저장.
		}
		
		System.out.println(dp[N]);
	}
}
