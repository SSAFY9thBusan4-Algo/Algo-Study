package week12.BOJ_13549_G5_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	private static int K;
	private static boolean[] visited = new boolean[100001];
	private static int[] visited2 = new int[100001];
	private static int result = 1000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
				
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = in.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		
		visited[N] = true;
		bfs(N, 0);
		
		System.out.println(result);
	}

	private static void bfs(int now, int cnt) {	
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {now, cnt});
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int n = cur[0];
			int c = cur[1];
			
			if (n == K) {
				result = Math.min(result, c);
				continue;
			}
			
			if (n-1 >= 0 && (visited[n-1] == false || (visited[n-1] == true && visited2[n-1] > c+1))) {
				visited[n-1] = true;
				visited2[n-1] = c+1;
				queue.offer(new int[] {n-1, c+1});
			}
			if (n+1 <= 100000 && (visited[n+1] == false || (visited[n+1] == true && visited2[n+1] > c+1))) {
				visited[n+1] = true;
				visited2[n+1] = c+1;
				queue.offer(new int[] {n+1, c+1});		
			}
			if (n*2 <= 100000 && (visited[n*2] == false || (visited[n*2] == true && visited2[n*2] > c))) {
				visited[n*2] = true;
				visited2[n*2] = c;
				queue.offer(new int[] {n*2, c});
			}
		}
				
	}

}
