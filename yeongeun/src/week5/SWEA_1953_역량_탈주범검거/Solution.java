package src.week5.SWEA_1953_역량_탈주범검거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// input
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			int R = Integer.parseInt(input[2]);
			int C = Integer.parseInt(input[3]);
			int L = Integer.parseInt(input[4]);

			char[][] map = new char[N][M];
			for(int i = 0 ; i < N ; i++) {
				map[i] = br.readLine().replace(" ", "").toCharArray();
			}

			// ==========================================
			
			final int[][] direc = {{0,1,2,3},{0,2},{1,3},{3,2},{0,3},{1,0},{1,2}}; // 현재 index일때 갈수있는 방향
			final int[][] delta = {{1,0},{0,-1},{-1,0},{0,1}}; // 하,좌,상,우
			
			Queue<Point> queue = new ArrayDeque<>();
			queue.offer(new Point(R,C));

			boolean[][] visited = new boolean[N][M];
			visited[R][C] = true;

			int count = 1;

			// 1. L 초만큼 진행
			for(int sec = 1 ; sec < L ; sec++) {
				
				if(queue.isEmpty()) break;

				// 2. 1초동안 더 갈 수 있는 길 찾기
				int size = queue.size();
				for(int s = 0 ; s < size; s++) {
					Point cur = queue.poll();
					
					int[] dir = direc[map[cur.x][cur.y] - '1'];

					// 3. 모든 방향으로 움직이기
					for(int d : dir) {
						int dx = cur.x + delta[d][0];
						int dy = cur.y + delta[d][1];
						// 갈 수 있는 길은 큐에 넣기.
						if(dx >= 0 && dx < N && dy >= 0 && dy < M 
								&& map[dx][dy] != '0' && !visited[dx][dy]) {
							
							// 4. 나오는 방향으로 왔을때 길이 있는가
							if(canArrive(map[dx][dy], d)) {
								queue.offer(new Point(dx,dy));
								visited[dx][dy] = true;
								count++;
							}
						}
					}
				}


			}

			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}

	private static boolean canArrive(char next, int dir) {
		if(next == '1') return true;

		if(next == '2' && dir%2 == 0) return true;
		if(next == '3' && dir%2 == 1) return true;
		//4567
		/* 4: 01 일때 true
		 * 5: 12
		 * 6: 23
		 * 7: 30
		 * 	  ^
		 *    d
		 */
		if(next == '7' && dir == 0) return true;
		if(next > '3') {
			int d = next - '4';
			if(dir-d < 0) return false;

			dir = (dir+4-d)%4;
			if(dir == 0 || dir == 1) return true;
		}
		return false;
	}
}
