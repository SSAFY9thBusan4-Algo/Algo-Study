package week9.BOJ_2632_G2_피자판매;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BOJ_2632_G2_피자판매 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int pizzaSize = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		// ------------- 피자A 경우의 수 계산 ---------------
		int[] A = new int[m]; // 피자A의 크기
		int[] Asum = new int[m]; // 해당 index까지의 피자크기 누적합

		// 피자 A로 만들 수 있는 피자조각 크기와 개수 저장
		Map<Integer, Integer> Asize = new HashMap<>(); // key : 피자조각 크기, value : 해당 크기의 피자조각 개수

		// 피자A입력받기 및 누적합 계산 및 처음으로 얻을 수 있는 경우의 수 계산
		Asum[0] = A[0];
		Asize.put(Asum[0], 1);
		for (int i = 1; i < m; i++) {
			A[i] = Integer.parseInt(in.readLine());
			Asum[i] = Asum[i - 1] + A[i];
			
			if (Asize.containsKey(Asum[i])) { // 이미 해당 피자조각크기가 존재할 때 기존의 개수+1
				int count = Asize.get(Asum[i]);
				Asize.put(Asum[i], count + 1);
			} else {
				Asize.put(Asum[i], 1);
			}
		}

		// 다음 칸부터 경우의 수 계산 
		// ex) 위에서는 0번째 인덱스부터 연속된 경우의 수를 계산
		// 아래에서는 1번째 인덱스부터 연속된 경우의 수 계산(모두 연속된 부분은 위에서 계산했기 때문에 패스)
		for (int i = 1; i < m; i++) { // 1~m-1번째 인덱스의 연속된 경우의 수 계산
			for (int j = i; j < i + m - 1; j++) { // m-1번 반복(경우의 수가 m-1)
				int sum = 0;
				if (j >= m) {
					sum = Asum[m - 1] - Asum[i - 1] + Asum[j - m];
				} else {
					sum = Asum[j] - Asum[i - 1];
				}

				if (Asize.containsKey(sum)) { 
					int count = Asize.get(sum);
					Asize.put(sum, count + 1);
				} else {
					Asize.put(sum, 1);
				}
			}
		}

		// ------------- 피자B 경우의 수 계산 ---------------
		
		int[] B = new int[n];
		int[] Bsum = new int[n];
		
		// 피자 B로 만들 수 있는 피자조각 크기와 개수 저장
		Map<Integer, Integer> Bsize = new HashMap<>();// key : 피자조각 크기, value : 해당 크기의 피자조각 개수

		// 아래 코드는 피자A의 경우의 수를 구할때와 동일
		Bsum[0] = B[0];
		Bsize.put(Bsum[0], 1);
		for (int i = 0; i < n; i++) {
			B[i] = Integer.parseInt(in.readLine());
			Bsum[i] = Bsum[i - 1] + B[i];
			
			if (Bsize.containsKey(Bsum[i])) { // 이미 존재할 때
				int count = Bsize.get(Bsum[i]);
				Bsize.put(Bsum[i], count + 1);
			} else {
				Bsize.put(Bsum[i], 1);
			}
		}

		for (int i = 1; i < n; i++) {
			for (int j = i; j < i + n - 1; j++) {
				int sum = 0;
				if (j >= n) {
					sum = Bsum[n - 1] - Bsum[i - 1] + Bsum[j - n];
				} else {
					sum = Bsum[j] - Bsum[i - 1];
				}

				if (Bsize.containsKey(sum)) { // 이미 존재할 때
					int count = Bsize.get(sum);
					Bsize.put(sum, count + 1);
				} else {
					Bsize.put(sum, 1);
				}
			}
		}

		// ------------- 손님이 원하는 크기의 피자를 판매하는 모든 방법의 가지 수 계산 -----------------
		int result = 0;
		for (Entry<Integer, Integer> entrySet : Asize.entrySet()) {
			int size = entrySet.getKey(); // 피자A의 조각 크기
			int count = entrySet.getValue(); // 해당 size의 크기를 만들 수 있는 경우의 수

			if (size > pizzaSize) { // 손님이 원하는 크기보다 크면 패스
				continue;
			}
			if (size == pizzaSize) { // 손님이 원하는 크기와 같을 때 피자B를 볼 필요없이 바로 result에 추가
				result += count;
			} else {
				if (Bsize.containsKey(pizzaSize - size)) { // 남은 크기를 피자B에서 채울 수 있을 때
					int Bcount = Bsize.get(pizzaSize - size);
					result += count * Bcount; 
				}
			}
		}

		if (Bsize.containsKey(pizzaSize)) { // 피자B 단독으로 손님이 원하는 크기를 만들 수 있을 때
			int Bcount = Bsize.get(pizzaSize);
			result += Bcount;
		}

		System.out.println(result);

	}
}
