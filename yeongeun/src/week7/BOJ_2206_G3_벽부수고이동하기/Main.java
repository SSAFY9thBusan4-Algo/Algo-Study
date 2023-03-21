package src.week7.BOJ_2206_G3_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	private static class Point {
		int x, y;
		boolean isBroke;
		public Point(int x, int y, boolean isBroke) {
			super();
			this.x = x;
			this.y = y;
			this.isBroke = isBroke;
		}
	}
	
	private static int N, M;
	private static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new char[N][M];
		for(int i = 0 ; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// solve
		int result = move();
		
		System.out.println(result);
	}

	private static int move() {

		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, false));
		int[][] visited = new int[N][M];
		for(int i = 0 ; i < N; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		visited[0][0] = 0;
		
		final int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
		int timer = 1;
		// bfs
		while(!queue.isEmpty()) {
			// depth (timer)
			int size = queue.size();
			for(int s = 0 ; s < size; s++) {
				Point cur = queue.poll();
				if(cur.x == N-1 && cur.y == M-1) return timer;
				int curBroke = cur.isBroke? 1 : 0;
				
				
				//move
				for(int d = 0 ; d < delta.length; d++) {
					int dx = cur.x + delta[d][0];
					int dy = cur.y + delta[d][1];
					
					if(dx >= 0 && dx < N && dy >= 0 && dy < M) {
						if(map[dx][dy] == '0' && visited[dx][dy] > curBroke) {
							visited[dx][dy] = curBroke;
							queue.offer(new Point(dx,dy,cur.isBroke));
						}
						else if(map[dx][dy] == '1' && !cur.isBroke && visited[dx][dy] > curBroke+1){
							visited[dx][dy] = curBroke+1;
							queue.offer(new Point(dx,dy,true));
						}
					}
				}
			}
			timer++;
		}
		
		
		return -1;
	}
	
	
}
