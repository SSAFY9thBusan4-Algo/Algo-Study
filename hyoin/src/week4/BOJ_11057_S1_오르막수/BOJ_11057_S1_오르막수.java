package week4.BOJ_11057_S1_오르막수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11057_S1_오르막수 {

	// 결과를 한번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		/*
		 * 1. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		/*
		 * 2. 입력파일 객체화
		 */
		int N = Integer.parseInt(in.readLine());

		/*
		 * 3. 알고리즘 풀기
		 */
/*		 dp의 각 index의 값 : N+1자리수의 맨 앞자리가 index일 때의 경우의 수
		 N이 1일 때 : 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 
			-> 글자수가 2일 때(N==2) 맨 앞자리가 0일 때의 경우의 수는 10, 맨 앞자리가 1일 때의 경우의 수는 9 ...
			=> 해당 경우의 수를 모두 더하면 수의 길이가 2일 때의 총 경우의 수
		 		맨 앞자리가 0일 때는 수의 길이가 1일 때(N=1)의 경우의 수 -> 01,02,03...,09 로 표현가능한데 0은 생략 가능하므로
		 N이 2일 때 : 55, 45, 36, 28, 21, 15, 10, 6, 3, 1
		  -> 맨 앞자리가 0일 때는 이전 dp의 0번째~9번째의 합(10+9+...+1), 1일 때는 1번째~9번째의 합 ...
		 N이 3일 때 : 220, 165, 120, 84, 56, 35, 20, 10, 4, 1
		  -> 맨 앞자리가 0일 때는 이전 dp의 0번째~9번째의 합(55+45+..+1), 1일 때는 1번째~9번째의 합 ...
*/
		int[] dp = new int[10];
		// 1 2 3 4 5 6 7 8 9 10 저장(N이 1일 때)
		for (int i = 0; i < 10; i++) {
			dp[i] = 10-i;
		}

		N = N - 1; // N이 1일 때는 위에서 초기화 해줬으므로 -1
		while (N != 0) {
			for (int i = 0; i <= 9; i++) {
				int sum = 0;
				for (int j = i; j <= 9; j++) {
					sum += dp[j];
				}
				dp[i] = sum%10007;
			}
			N--;
		}

		/*
		 * 4. 정답 출력
		 */
		System.out.println(dp[0]);
	}
}
