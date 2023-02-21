package week3.BOJ_9084_G5_동전;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 결과를 한번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		/*
		 * 1. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		/*
		 * 2. 입력파일 객체화
		 */
		int T = Integer.parseInt(st.nextToken());

		/*
		 * 3. 알고리즘 풀기
		 */
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken()); // 동전의 가지수

			int[] coin = new int[N]; // 동전의 종류 저장
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(in.readLine());
			int M = Integer.parseInt(st.nextToken());// 만들어야 할 금액

			/*
			 * 점화식
			 * - dp[j] = dp[j]+dp[j-coin[i]]
			 * - dp[0] = 1
			 * i : 동전의 종류를 확인할 index
			 * j : 만들어야 할 금액
			 * coin[i] : 동전의 종류
			 */
			int[] dp = new int[M + 1];
			dp[0] = 1;
			for (int i = 0; i < N; i++) {
				for (int j = coin[i]; j <= M; j++) {
					dp[j] = dp[j] + dp[j - coin[i]];
				}
			}
			sb.append(dp[M]).append("\n");
		}

		/*
		 * 4. 정답 출력
		 */
		System.out.println(sb);
	}
}
