package week19.BOJ_8983_G4_사냥꾼;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983_G4_사냥꾼 {

	public static int M;
	public static int N;
	public static int L;
	public static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		input = new int[M];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < M; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input);

		int result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int position = binSearch(x, y); // 현재 동물과 가장 가까이에 있는 사대의 위치
			int dist = Math.abs(input[position] - x) + y;

			if (dist <= L) {
				result++;
			}
		}

		System.out.println(result);

	}

	public static int binSearch(int x, int y) {
		int start = 0;
		int end = M - 1;

		int left = start;
		int right = end;

		while (left <= right) {
			int mid = (left + right) / 2;
			int dist = Math.abs(input[mid] - x) + y;

			if (dist <= L || input[mid] == x) { // 범위내에 포함되거나 x좌표가 같으면 해당 위치 return
				return mid;
			}

			if (input[mid] < x) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		// left가 범위를 벗어났으므로 가장가까운 위치는 right
		if (left > end) {
			return right;
		}

		if (right < start)
			return left;

		// left와 right 중 거리가 더 짧은 곳 반환
		if (Math.abs(input[left] - x) < Math.abs(input[right] - x)) {
			return left;
		} else {
			return right;
		}
	}
}
