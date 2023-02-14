package week2.BOJ_14501_S3_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N; // 퇴사일
	private static int dp[]; // 해당 index에서의 최대 수익을 저장하는 배열
	private static int[] period; // 상담 기간
	private static int[] money; // 상담 수익

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		period = new int[N + 1];
		money = new int[N + 1];
		dp = new int[N + 2]; // 퇴사 당일 계산 시 N+1번째 index필요

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			period[i] = Integer.parseInt(st.nextToken());
			money[i] = Integer.parseInt(st.nextToken());
		}
		
		/*
		 * 풀이 시작
		 * N일부터 역순으로 최대 상담 수익을 dp배열에 저장
		 * 1. 현재 날짜에서 상담 진행이 불가능하면 다음날의 최대 수익(dp[i+1])을 dp에 저장함
		 * 2. 현재 날짜에서 상담이 가능하다면 최대 수익을 갱신해야함
		 * 	2-1. 다음날의 최대 수익(dp[i + 1])과 상담을 진행함으로써 생기는 이익(money[i] + dp[next])을 비교
		 */
		for(int i = N; i > 0; i--) {
			int next = i + period[i]; // 상담 이후 날짜
			
			if(next > N + 1) { // 퇴사일을 넘어갈 때
				// 1. 상담을 진행 못하므로 다음날의 dp값을 가져옴
				dp[i] = dp[i+1];
			} else { // 2. 상담 가능할 때
				// 2-1. 현재 날짜의 최대수익 갱신
				dp[i] = Math.max(dp[i + 1], money[i] + dp[next]);
			}
		}

		System.out.println(Arrays.stream(dp).max().getAsInt());
	}

}
