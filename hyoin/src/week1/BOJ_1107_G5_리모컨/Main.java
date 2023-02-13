package week1.BOJ_1107_G5_리모컨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		boolean broken_button[] = new boolean[10]; // 0~9까지의 버튼 변수(고장난 버튼은 true)
		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				broken_button[Integer.parseInt(st.nextToken())] = true;
			}
		}

		int result = Math.abs(100 - N); // 100부터 +나 -로만 움직일 때

		for (int i = 0; i < 1000000; i++) {
			int chanel = i;
			int count = 0; // 이동 가능한 숫자의 자릿수
			boolean isPossible = true; // 숫자 버튼으로만 이동 가능한지
			do {
				if (broken_button[chanel % 10]) {// 이동할 번호가 고장난 숫자 버튼일 때
					isPossible = false;
					break;
				} else {// 숫자 버튼으로 이동 가능할 때
					chanel = chanel / 10;
					count++;
				}
			} while (chanel > 0);

			// 현재 채널이 숫자 버튼으로 이동 가능할 때
			if (isPossible) {
				count = count + Math.abs(N - i); // 현재채널자릿수 + 이동하려는 채널만큼 +나 -버튼으로 움직일 개수
				result = Math.min(result, count);
			}
		}

		System.out.println(result);
	}

}
