import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15565_S1_귀여운라이언 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine());
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int count = 0; // 현재 라이언의 개수
		int min = Integer.MAX_VALUE;
		boolean isFirst = false; // 처음으로 K개의 라이언을 가졌을 때
		Queue<Integer> queue = new ArrayDeque<>(); // 라이언의 index들을 넣음
		for (int i = 0; i < N; i++) {
			if (input[i] == 1) { // 라이언일 때
				queue.offer(i);
				count++;
			}
			if (count == K) { // 처음으로 K개의 라이언을 가졌을 때
				isFirst = true;
			}
			if (isFirst && input[i] == 1) { // 현재 라이언이고 K개의 라이언을 가지고 있을 때
				int index = queue.poll(); // K개를 가지는 제일 가까운 라이언의 index
				if (i - index + 1 < min) { // 최솟값 갱신
					min = i - index + 1;
				}
			}
		}

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}
}
