package week2.BOJ_10972_S3_다음순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();

		N = Integer.parseInt(st.nextToken());
		int[] permutation = new int[N]; // 입력된 순열을 1개씩 저장하는 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			permutation[i] = Integer.parseInt(st.nextToken());
		}

		// 문제 풀이
		for (int i = N - 1; i >= 0; i--) {// 역순으로 탐색
			if (i - 1 < 0) {// 입력이 내림차순으로 정렬되어있을 때 -> 마지막 순열
				sb.append("-1\n");
				break;
			}
			// 1. 현재 숫자가 다음숫자(왼쪽숫자)보다 클 때
			if (permutation[i] > permutation[i - 1]) {
				int maxIndex = 0;

				// 2. 영역 나누기
				int[] right = Arrays.copyOfRange(permutation, i, N);
				int[] left = Arrays.copyOfRange(permutation, 0, i);

				// 3-1. 오른쪽 구역에서 기준값((현재-1번째)숫자)보다 큰 값 찾기
				for (int j = right.length - 1; j > 0; j--) {
					if (right[j] > left[left.length - 1]) {
						maxIndex = j;
						break;
					}
				}

				// 3-2. 위치 교환
				int temp = right[maxIndex];
				right[maxIndex] = left[left.length - 1];
				left[left.length - 1] = temp;

				// 4. 오른쪽 구역을 오름차순으로 정렬
				Arrays.sort(right);

				sb.append(Arrays.toString(left).replace(",", "").replace("[", "").replace("]", ""));
				sb.append(" ");
				sb.append(Arrays.toString(right).replace(",", "").replace("[", "").replace("]", ""));

				break;

			}
		}

		System.out.println(sb);
	}

}
