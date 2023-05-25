package week15.BOJ_1461_G5_도서관;

import java.util.*;
import java.io.*;

public class BOJ_1461_G5_도서관 {

	public static int N;
	public static int M;
	public static int[] input;
	public static int negativeCo;
	public static int result;
	public static int last; // 마지막에 더해진 값

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N];
		st = new StringTokenizer(in.readLine());

		negativeCo = 0; // 음수 개수
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			if (input[i] < 0) {
				negativeCo++;
			}
		}

		Arrays.sort(input);

		if (input[0] * input[N - 1] > 0) { // 음수, 양수 중 한 종류만 존재할 때
			if (input[0] > 0) { // 양수만 존재할 때
				visitPositive();
			} else {
				visitNegative();

			}
			result -= last;

		} else { // 음수, 양수 혼합되어 있을 때
			if (Math.abs(input[0]) > Math.abs(input[N - 1])) { // 음수쪽이 더 멀때
				// 양수 먼저 방문
				visitPositive();
				// 음수 방문
				visitNegative();
				result -= last;
			} else {
				// 음수 먼저 방문
				visitNegative();
				// 양수 방문
				visitPositive();
				result -= last;
			}
		}

		System.out.println(result);
	}

	// 음수 방문
	private static void visitNegative() {
		int start = 0;

		if (negativeCo < M) { // 음수 개수가 M개보다 작을 때 제일 마지막 위치만 방문
			start = 0;
		} else {  
			if (negativeCo % M != 0) { // 음수 개수가 M으로 나누어 떨어지지 않을 때 0에서 해당 나머지만큼 먼저 방문
				start = negativeCo - (negativeCo % M); // 나머지만큼 먼저 방문
				result += Math.abs(input[start]) * 2;
				start -= M;
			} else {
				start = negativeCo - M;
			}
		}

		// M개씩 쥐고 방문
		for (int i = start; i >= 0; i -= M) {
			result += Math.abs(input[i]) * 2;
			last = Math.abs(input[i]);
		}
	}

	// 양수 방문
	private static void visitPositive() {
		int positiveCo = N - negativeCo;
		int start = 0;

		if (positiveCo < M) { // 양수 개수가 M개보다 작을 때 제일 마지막 위치만 방문
			start = N - 1;
		} else {
			if (positiveCo % M != 0) {
				start = negativeCo + (positiveCo % M) - 1;
				result += input[start] * 2;
				start += M;
			} else {
				start = negativeCo + M - 1;
			}
		}
		for (int i = start; i < N; i += M) {
			result += input[i] * 2;
			last = input[i];
		}
	}
}
