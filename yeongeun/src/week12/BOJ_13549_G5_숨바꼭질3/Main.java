package src.week12.BOJ_13549_G5_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int K = Integer.parseInt(in[1]);
		
		//solve
		int M = 100_001;
		int[] visited = new int[M];
		Arrays.fill(visited, Integer.MAX_VALUE);
		visited[N] = 0;
		
		Queue<int[]> queue = new PriorityQueue<int[]>((o1, o2) -> o1[1]-o2[1]);
		queue.offer(new int[] {N, 0});
		
		int result = -1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int pos = cur[0];
			int time = cur[1];
			
			if(pos == K) {
				result = time;
				break;
			}
			
			if(visited[pos] < time) continue;	//이미 방문 한 경우
			
			// 1. 순간이동
			int next = pos * 2;
			if(next < M && visited[next] > time) {
				visited[next] = time;
				queue.offer(new int[] {next, time});
			}
			
			// 2. 걷기
			next = pos + 1;
			if(next < M && visited[next] > time + 1) {
				visited[next] = time + 1;
				queue.offer(new int[] {next, time+1});
			}
			next = pos - 1;
			if(next >= 0 && visited[next] > time + 1) {
				visited[next] = time + 1;
				queue.offer(new int[] {next, time+1});
			}
		}
		System.out.println(result);
	}
}
