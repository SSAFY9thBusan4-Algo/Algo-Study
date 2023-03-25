package src.week7.BOJ_2665_S4_미로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	private static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][];
		for(int i = 0 ; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// solve
		int result = go();
		
		System.out.println(result);
	}
	
	private static int go() {

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,0,0});
		
		int[][] visited = new int[map.length][map.length];
		for(int i = 0 ; i < map.length; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		final int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
		
		// 결과값. 끝방까지가는 최소 전환 횟수
		int result = Integer.MAX_VALUE;
		
		// 출발
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(cur[2] > result) continue;	// 가지치기. 
			
			// 이동
			for(int d = 0 ; d < 4; d++) {
				int dx = cur[0] + delta[d][0];
				int dy = cur[1] + delta[d][1];
				
				if(dx < 0 || dx >= map.length || dy < 0 || dy >= map.length)
					continue;
				
				// 만약 도착했으면
				if(dx == map.length-1 && dy == map.length-1) {
					result = cur[2];
				}
				
				// 이전에 간 것보다 더 작으면 간다.
				if(map[dx][dy] == '1' && visited[dx][dy] > cur[2]) {
					visited[dx][dy] = cur[2];
					queue.offer(new int[] {dx,dy,cur[2]});
				}
				else if(map[dx][dy] == '0' && visited[dx][dy] > cur[2]+1){
					visited[dx][dy] = cur[2]+1;
					queue.offer(new int[] {dx,dy,cur[2]+1});
				}
			}
		}
		return result;
	}
}
