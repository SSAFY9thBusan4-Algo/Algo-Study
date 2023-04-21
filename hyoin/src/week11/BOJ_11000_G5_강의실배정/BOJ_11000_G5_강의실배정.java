package week11.BOJ_11000_G5_강의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000_G5_강의실배정 {

	public static class Study implements Comparable<Study> {
		int start;
		int end;

		public Study(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Study o) {
			if (this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}

		@Override
		public String toString() {
			return "Study [start=" + start + ", end=" + end + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());

		PriorityQueue<Study> pq = new PriorityQueue<>(); // 시작시간 오름차순 & 종료시간 오름차순

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			pq.offer(new Study(start, end));
		}

		PriorityQueue<Integer> pq2 = new PriorityQueue<>(); // 종료시간이 오름차순으로 들어감
		Study cur = pq.poll();
		pq2.offer(cur.end);

		while (!pq.isEmpty()) {
			cur = pq.poll();

			if (pq2.peek() <= cur.start) { // 현재 시작시간이 종료시간의 최소값보다 크거나 같으면 같은 강의실 사용 가능
				pq2.poll(); // 종료시간이 바꼈으므로 poll
			}
			pq2.offer(cur.end); // 바뀐 종료시간 큐에 삽입
		}

		System.out.println(pq2.size()); // 우선순위큐에 남은 개수가 강의실의 개수
	}
}
