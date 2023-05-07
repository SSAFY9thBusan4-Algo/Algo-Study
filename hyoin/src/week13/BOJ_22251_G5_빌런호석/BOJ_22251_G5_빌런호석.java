package week13.BOJ_22251_G5_빌런호석;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_22251_G5_빌런호석 {

	public static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken()); // 만들 수 있는 최대 숫자
		K = Integer.parseInt(st.nextToken()); // 자리수
		int P = Integer.parseInt(st.nextToken()); // 최대 반전가능 개수
		int X = Integer.parseInt(st.nextToken()); // 현재 층 수

		// 맨 위부터 시계방향으로 위치 표시
		// 각 숫자가 디스플레이에 표시되는 현황(1이면 켜저있고 0이면 꺼져있음)
		Map<Integer, int[]> map = new HashMap<>();
		map.put(0, new int[] { 1, 1, 1, 1, 1, 1, 0 });
		map.put(1, new int[] { 0, 1, 1, 0, 0, 0, 0 });
		map.put(2, new int[] { 1, 1, 0, 1, 1, 0, 1 }); // 0, 1, 6, 4, 3
		map.put(3, new int[] { 1, 1, 1, 1, 0, 0, 1 }); // 0, 1, 6, 2, 3
		map.put(4, new int[] { 0, 1, 1, 0, 0, 1, 1 }); // 5, 6, 1, 2
		map.put(5, new int[] { 1, 0, 1, 1, 0, 1, 1 }); // 0, 5, 6, 2, 3
		map.put(6, new int[] { 1, 0, 1, 1, 1, 1, 1 }); // 0, 5, 4, 3, 2, 6
		map.put(7, new int[] { 1, 1, 1, 0, 0, 0, 0 }); // 0, 1, 2
		map.put(8, new int[] { 1, 1, 1, 1, 1, 1, 1 }); // 0, 1, 2, 3, 4, 5, 6
		map.put(9, new int[] { 1, 1, 1, 1, 0, 1, 1 }); // 0, 5, 6, 1, 2, 3

		// 현재 숫자 자리수 구하기
		int[] curNum = makeDigit(X);
		
		// 현재 숫자 X의 각 자리수에서 0~9로 바꿀 때 필요한 반전 개수(reverseCo를 저장하니 시간 단축됨)
		// 현재 숫자가 35라면
		// reverseCo[0][2] : 현재 숫자 첫번째 자리인 3을 2로 바꿀 때 필요한 반전 개수(2개)
		// reverseCo[1][9] : 현재 숫자 두번째 자리인 5를 9로 바꿀 때 필요한 반전 개수(1개)
		int[][] reverseCo = new int[K][10];
		for (int i = 0; i < curNum.length; i++) {
			for (int j = 0; j < 10; j++) {
				int[] curArr = map.get(curNum[i]);
				int[] tempArr = map.get(j); // 목표 숫자

				int count=0;
				for(int k=0; k<7; k++) {
					if(curArr[k]!=tempArr[k]) {
						count++;
					}
				}
				reverseCo[i][j] = count;
			}
		}
		

		int result = 0;
		// 1 ~ N 중에 P개 이내의 표시등을 반전시켜 해당 수를 만들 수 있는지 확인
		for (int i = 1; i <= N; i++) {
			int[] curDigit = makeDigit(i); // 현재 목표 숫자

			boolean isPossible = true; // P개 이내로 목표 숫자를 만들 수 있는지
			int count = 0; // 반전개수
			for (int j = 0; j < K; j++) { // 각 자리수마다 반전개수 계산
				count += reverseCo[j][curDigit[j]];
				if(count>P) {
					isPossible=false;
					break;
				}
			}

			if (isPossible && count != 0) {
				result++;
			}
		}

		System.out.println(result);

	}

	// 현재 숫자를 각 자리수마다 배열로 저장
	// 맨 앞자리부터 0번 인덱스에 저장
	private static int[] makeDigit(int num) {
		int[] curDigit = new int[K];
		for (int i = K - 1; i >= 0; i--) {
			curDigit[i] = num % 10;
			num /= 10;
		}
		return curDigit;
	}

}
