package week12.BOJ_3020_G5_개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3020_G5_개똥벌레 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int top[] = new int[H + 1]; // 종유석
		int bottom[] = new int[H + 1]; // 석순
		
		// 해당 높이에 해당하는 종유석 or 석순이 몇개인지 입력 받음
		for (int i = 0; i < N / 2; i++) {
			top[Integer.parseInt(in.readLine())]++;
			bottom[Integer.parseInt(in.readLine())]++;
		}

		// 누적합(구역별 장애물 개수)
		// 구역이 H인 석순은 bootom[H]개, 구역이 H-1인 석순은 bootom[H-1]+bootom[H]개
		// 종유석도 마찬가지
		for (int i = H - 1; i >= 1; i--) {
			bottom[i] += bottom[i + 1];
			top[i] += top[i + 1];
		}

		int min = Integer.MAX_VALUE;
		int minCount = 0;
		// 구역별 장애물 개수 총 합
		for (int i = 1; i <= H; i++) {
			int tacle = bottom[i] + top[H - i + 1]; // 종유석은 위에서부터 자라나니까 범위가 H-i+1

			if (min > tacle) {
				min = tacle;
				minCount = 1;
			} else if (min == tacle) {
				minCount++;
			}
		}
		
		System.out.println(min+" "+minCount);
	}
}