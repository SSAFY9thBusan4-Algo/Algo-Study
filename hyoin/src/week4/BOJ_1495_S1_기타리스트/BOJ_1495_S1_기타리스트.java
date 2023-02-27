package week4.BOJ_1495_S1_기타리스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1495_S1_기타리스트 {

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
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] dp = new int[M + 1]; // dp[i] = j -> j번째 연주에서 볼륨 i로 연주

		/*
		 * 3. 알고리즘 풀기
		 */

		Arrays.fill(dp, -1);
		dp[S] = 0; // 시작 볼륨 삽입

		st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= N; i++) {
			int v = Integer.parseInt(st.nextToken());

			List<Integer> vlist = new ArrayList<Integer>(); // 만들 수 있는 볼륨들 담는 list
			for (int j = 0; j < M + 1; j++) {
				if (dp[j] == i - 1) { // 이전 연주에서 나온 볼륨들 찾기
					int plusV = j + v;
					int minusV = j - v;

					// +볼륨과 -볼륨이 가능할 때 list에 추가
					if (plusV >= 0 && plusV <= M) {
						vlist.add(plusV);
					}
					if (minusV >= 0 && minusV <= M) {
						vlist.add(minusV);
					}
				}
			}

			// 볼륨 index 업데이트
			for (int vo : vlist) {
				dp[vo] = i;
			}
		}

		// 마지막 곡을 연주했을 때 최대볼륨 찾기
		int result = -1;
		for (int i = M; i >= 0; i--) {
			if (dp[i] == N) {
				result = i;
				break;
			}
		}

		/*
		 * 4. 정답 출력
		 */
		System.out.println(result);
	}
}
