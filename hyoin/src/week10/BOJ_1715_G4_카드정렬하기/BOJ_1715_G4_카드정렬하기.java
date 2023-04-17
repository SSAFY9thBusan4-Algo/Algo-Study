package week10.BOJ_1715_G4_카드정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715_G4_카드정렬하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(in.readLine()));
		}

		int A = 0; // 카드묶음 A
		int B = 0; // 카드묶음 B
		int sum = 0; // 현재 비교한 횟수
		int result = 0; // 최종결과

		// 가장 작은 카드묶음끼리 서로 먼저 비교해야 최소비교횟수가 나옴
		while (pq.size() > 1) { // 카드 묶음이 1개 남으면 종료
			A = pq.poll();
			B = pq.poll();
			sum = A + B;
			result += sum;
			pq.offer(sum);
		}

		System.out.println(result);
	}
}
