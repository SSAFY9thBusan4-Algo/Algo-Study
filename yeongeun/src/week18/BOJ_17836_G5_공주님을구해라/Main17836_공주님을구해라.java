package src.week18.BOJ_17836_G5_공주님을구해라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17836_공주님을구해라 {
	
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);
		int T = Integer.parseInt(in[2]);
		
		int[][] map = new int[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = solve(N, M, map);
		
		if(result <= T) System.out.println(result);
		else System.out.println("Fail");
		
	}

	private static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};
	private static int solve(int n, int m, int[][] map) {

		int result = 100_005;
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0));
		
		int[][] visited = new int[n][m];
		visited[0][0] = 1;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int t = visited[cur.x][cur.y];
			
			if(map[cur.x][cur.y] == 2) {
				int distance = Math.abs(cur.x - (n-1)) + Math.abs(cur.y - (m-1));
				int time = t + distance;
				result = time < result ? time : result;
			}
			else if(cur.x == n-1 && cur.y == m-1) {
				result = t < result ? t : result;
				break;
			}
			
			for(int[] d : delta) {
				int dx = cur.x + d[0];
				int dy = cur.y + d[1];
				
				if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
				if(map[dx][dy] == 1) continue;

				if(visited[dx][dy] == 0 || visited[dx][dy] > t+1) {
					visited[dx][dy] = t+1;
					queue.offer(new Point(dx,dy));
				}
				
			}
		}
		
		
		return result-1;
	}
}
