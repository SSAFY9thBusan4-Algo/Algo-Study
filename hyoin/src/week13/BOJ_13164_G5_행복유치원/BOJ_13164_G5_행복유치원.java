package week13.BOJ_13164_G5_행복유치원;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13164_G5_행복유치원 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine());
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		// 인접한 학생들의 키 차이를 내림차순으로 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N - 1; i++) {
			pq.offer(input[i + 1] - input[i]);
		}
		
		// 예제 : 1 3 | 5 6 | 10
		// 3개의 그룹으로 나눌려면 가장 큰 3-1개의 키차이를 제외하면 됨
		int result=0;
		for(int i=0; i<K-1; i++) {
			pq.poll();
		}
		
		while(!pq.isEmpty()) {
			result+=pq.poll();
		}
		
		System.out.println(result);
	}
}
