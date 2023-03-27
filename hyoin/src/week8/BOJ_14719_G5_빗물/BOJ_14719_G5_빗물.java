package week8.BOJ_14719_G5_빗물;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_G5_빗물 {
	// 현재 위치를 기준으로 왼쪽에서 제일 큰 값과 오른쪽에서 제일 큰 값 중 작은값만큼 물이 고일 수 있음
	// 현재 블럭의 높이가 위에서 구한 값보다 작을 때만 물이 고일 수 있음
	// 0번째와 W-1번째는 빗물이 고일 수 없으므로 배열 범위에서 제외 가능
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] map = new int[W]; // 블럭 높이
		int[] leftMax = new int[W]; // 현재 index를 기준으로 왼쪽에서 제일 높은 값을 저장하는 배열
		int[] rightMax = new int[W]; // 현재 index를 기준으로 오른쪽에서 제일 높은 값을 저장하는 배열

		st = new StringTokenizer(in.readLine());
		int result = 0;

		// 현재 index를 기준으로 왼쪽에서 제일 높은 값 구하기
		map[0] = Integer.parseInt(st.nextToken());
		int lMax = map[0];
		for (int i = 1; i < W; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			leftMax[i] = lMax;
			if (map[i] > lMax) {
				lMax = map[i];
			}
		}

		// 현재 index를 기준으로 오른쪽에서 제일 높은 값 구하기
		int RMax = map[W - 1];
		for (int i = W - 2; i > 0; i--) {
			rightMax[i] = RMax;
			if (map[i] > RMax) {
				RMax = map[i];
			}
		}

		// 고인 빗물의 양 측정
		for (int i = 1; i < W - 1; i++) {
			int rain = (leftMax[i] < rightMax[i]) ? leftMax[i] : rightMax[i]; // 왼쪽최대값과 오른쪽최대값 중 작은 값이 최대 고일수 있는 빗물의 양
			if (map[i] < rain) { // 현재 빌딩의 높이가 고일수 있는 빗물의 양보다 작아야 빗물이 고일 수 있음
				result += (rain - map[i]);
			}
		}
		System.out.println(result);

	}
}
