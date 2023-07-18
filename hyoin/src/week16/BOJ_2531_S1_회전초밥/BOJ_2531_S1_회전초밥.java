package week16.BOJ_2531_S1_회전초밥;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531_S1_회전초밥 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken()); // 접시수
		int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰번호

		int[] info = new int[N];
		int[] kind = new int[d + 1]; // 초밥 종류에 따른 개수
		for (int i = 0; i < N; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}

		int result = 0;
		int size = 0;
		// 처음 연속하는 k개만큼 계산
		for (int i = 0; i < k; i++) {
			if (kind[info[i]] == 0) { // 같은 종류의 초밥이 아닐 때
				size++;
			}
			kind[info[i]] += 1; // 초밥종류 체크
		}

		if (kind[c] == 0) { // 쿠폰 사용이 가능할 때
			size += 1;
		}

		if (result < size) { // 최대값 갱신
			result = size;
		}

		if (kind[c] == 0) { // 쿠폰 체크 해제
			size -= 1;
		}

		int start = 0; // 처음 들어온 초밥(곧 제외할 예정)
		int end = k; // 곧 들어올 초밥

		for (int i = 1; i < N; i++) {
			kind[info[start]] -= 1; // 처음 들어온 초밥 제외
			if (kind[info[start]] == 0) { // 해당 초밥이 더이상 없을 때
				size--;
			}

			if (kind[info[end]] == 0) { // 들어올 초밥의 종류가 처음일 때
				size++;
			}
			kind[info[end]] += 1; // 초밥 종류 +1

			if (kind[c] == 0) { // 쿠폰사용이 가능할 때
				size += 1;
			}

			// 최대값 갱신
			if (result < size) {
				result = size;
			}
			if (kind[c] == 0) { // 쿠폰사용 개수 제외하기
				size -= 1;
			}

			start = i;
			end = (i + k) % N;

		}
		System.out.println(result);
	}
}
