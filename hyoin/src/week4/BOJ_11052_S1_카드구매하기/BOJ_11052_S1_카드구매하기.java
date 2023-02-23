package week4.BOJ_11052_S1_카드구매하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11052_S1_카드구매하기 {

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
		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
		}

		/*
		 * 3. 알고리즘 풀기
		 */
		// dp[i]는 dp[i], dp[i-1]+dp[0], dp[i-2]+dp[1], ... 중에 가장 큰 값
		// 4개의 카드의 최대값을 구하기 위해서는 카드 4개를 살 수 있는 경우의 수를 모두 구해야함 -> 1개짜리 4묶음, 2개짜리 2묶음, 3개짜리 1묶음&1개짜리 1묶음, 4개짜리 1묶음
		for(int i=1; i<N; i++) {
			int half = (i-1)/2;
			for(int j=0; j<=half; j++) {
				int end = i-1-j;
				dp[i] = Math.max(dp[i], dp[j]+dp[end]);
			}
		}

		/*
		 * 4. 정답 출력
		 */
		System.out.println(dp[N-1]);
	}

}
