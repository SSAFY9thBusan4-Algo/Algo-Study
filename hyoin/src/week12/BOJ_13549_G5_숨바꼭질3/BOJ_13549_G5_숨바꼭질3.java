package week12.BOJ_13549_G5_숨바꼭질3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549_G5_숨바꼭질3 {

	public static int N;
	public static int K;
	public static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[100002];
		queue.offer(new int[] { N, 0 }); // 현재 위치, 소요시간
		isVisited[N] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int locatioin = cur[0];
			int time = cur[1];

			if (locatioin == K) { // 종료조건
				result = time;
				return;
			}
			
			// 0초 후에 2*X의 위치로 이동
			int nextLocation = locatioin * 2;
			if (nextLocation >= 0 && nextLocation <= 100000 && !isVisited[nextLocation]) { // 범위를 벗어나지 않고 방문한 적이 없을 때
				queue.offer(new int[] { nextLocation, time });
				isVisited[nextLocation] = true;
			}

			// 1초 후에 X-1로 이동
			nextLocation = locatioin - 1;
			if (nextLocation >= 0 && nextLocation <= 100000 && !isVisited[nextLocation]) {
				queue.offer(new int[] { nextLocation, time + 1 });
				isVisited[nextLocation] = true;
			}

			// 1초 후에 X+1로 이동
			nextLocation = locatioin + 1;
			if (nextLocation >= 0 && nextLocation <= 100000 && !isVisited[nextLocation]) {
				queue.offer(new int[] { nextLocation, time + 1 });
				isVisited[nextLocation] = true;
			}

		}

	}
}
