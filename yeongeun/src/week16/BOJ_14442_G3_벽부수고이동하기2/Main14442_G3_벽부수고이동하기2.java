package src.week16.BOJ_14442_G3_벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main14442_G3_벽부수고이동하기2 {

	private static class Point {
		int x, y;
		int broken;
		public Point(int x, int y, int broken) {
			super();
			this.x = x;
			this.y = y;
			this.broken = broken;
		}
	}
	
	private static int N, M, k;
	private static char[][] map;
	private static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		k = Integer.parseInt(input[2]);
		
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
		queue.offer(new Point(0, 0, 0));
		boolean[][][] visited = new boolean[N][M][k+1];	// 해당 위치에 k번의 벽을 부수고 간 적이 있는가.
		visited[0][0][0] = true;
		
		int timer = 1;
		// bfs
		while(!queue.isEmpty()) {
			// 같은 시간에 도달한 위치
			int size = queue.size();
			for(int s = 0 ; s < size; s++) {
				Point cur = queue.poll();
				int curBroke = cur.broken;
				
				// 도착했으면
				if(cur.x == N-1 && cur.y == M-1) return timer;
				
				// move
				for(int d = 0 ; d < delta.length; d++) {
					int dx = cur.x + delta[d][0];
					int dy = cur.y + delta[d][1];
					
					if(dx >= 0 && dx < N && dy >= 0 && dy < M) {
						// 해당 위치가 벽인가.
						// 아니다. -> 방문한 적 없으면 간다.
						if(map[dx][dy] == '0' && !visited[dx][dy][curBroke]) {
							visited[dx][dy][curBroke] = true;
							queue.offer(new Point(dx,dy,curBroke));
						}
						// 벽이다. -> 뚫을 수 있는가? -> 방문한 적 있는가?
						else if(map[dx][dy] == '1' && curBroke+1 <= k && !visited[dx][dy][curBroke+1]){
							visited[dx][dy][curBroke+1] = true;
							queue.offer(new Point(dx,dy,curBroke+1));
						}
					}
				}
			}
			timer++;
		}
		
		return -1;
	}
}
