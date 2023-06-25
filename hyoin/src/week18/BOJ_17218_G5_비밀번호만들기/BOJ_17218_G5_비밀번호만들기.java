package week18.BOJ_17218_G5_비밀번호만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17218_G5_비밀번호만들기 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		char[] input1 = in.readLine().toCharArray();
		char[] input2 = in.readLine().toCharArray();

		// dp[i][j] : input1의 0~i번째와 input2의 0~j번째까지 중 가장 긴 부분 공통 문자
		String[][] dp = new String[input1.length][input2.length];

		// input1을 순회하면서 초기값 저장(input2의 0번째와 input1의 0~i번째까지의 부분공통문자)
		boolean isSame = false; // 이전에 input2[0]과 같은 문자가 존재했는지
		for (int i = 0; i < input1.length; i++) {
			if (input1[i] == input2[0] || isSame) {
				dp[i][0] = Character.toString(input2[0]);
				isSame = true;
			} else {
				dp[i][0] = "";
			}
		}

		// input2의 초기값
		isSame = false;
		for (int i = 0; i < input2.length; i++) {
			if (input2[i] == input1[0]||isSame) {
				dp[0][i] = Character.toString(input1[0]);
				isSame = true;
			} else {
				dp[0][i] = "";
			}
		}

		// 부분 최장 문자열 저장
		for (int i = 1; i < input1.length; i++) {
			for (int j = 1; j < input2.length; j++) {
				if (input1[i] == input2[j]) { // input1과 input2의 문자가 같으면 이전 최장문자열에 현재 문자 추가
					dp[i][j] = dp[i - 1][j - 1] + input1[i];
				} else { // 현재 input1과 input2의 문자가 다르면 이전 최장문자열 중 더 길이가 긴 문자 저장
					if (dp[i - 1][j].length() < dp[i][j - 1].length()) {
						dp[i][j] = dp[i][j - 1];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
		}

		System.out.println(dp[input1.length - 1][input2.length - 1]);
	}
}
