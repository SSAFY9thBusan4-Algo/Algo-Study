package week5.SWEA_1953_탈주범검거;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	private static StringBuilder sb = new StringBuilder();
	private static int N, M, L, result;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = {-1,0,1,0};
	private static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//System.setIn(new FileInputStream(new File("res/input.txt")));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#"+tc+" ");
	
			result = 1;
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
			}
			
			visited[R][C] = true;
			bfs(R,C,1);
			
			sb.append(result+"\n");
			
		}
		
		System.out.println(sb);
	}

	private static void bfs(int row, int col, int count) {
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {row, col, count});
		
		while (!queue.isEmpty()) {
			int[] rcc = queue.poll();
			int r = rcc[0];
			int c = rcc[1];
			int cnt = rcc[2];
			int t = map[r][c];  // 현재 터널 종류
			
			if (cnt == L) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (0<=nr && nr<N && 0<=nc && nc<M && visited[nr][nc] == false) {
					
					if ((t == 1 || t == 2 || t == 4 || t == 7) && i == 0) {  // 위로갈때
						if (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6) {
							visited[nr][nc] = true;
							result += 1;
							queue.offer(new int[] {nr, nc, cnt+1});
						}
					}
					else if ((t == 1 || t == 3 || t == 4 || t == 5) && i == 1) {  // 오른쪽으로 갈때
						if (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7) {
							visited[nr][nc] = true;
							result += 1;
							queue.offer(new int[] {nr, nc, cnt+1});
						}
					}
					else if ((t == 1 || t == 2 || t == 5 || t == 6) && i == 2) {  // 아래쪽으로 갈때
						if (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7) {
							visited[nr][nc] = true;
							result += 1;
							queue.offer(new int[] {nr, nc, cnt+1});
						}
					}
					else if ((t == 1 || t == 3 || t == 6 || t == 7) && i == 3) {  // 왼쪽으로 갈때
						if (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5) {
							visited[nr][nc] = true;
							result += 1;
							queue.offer(new int[] {nr, nc, cnt+1});
						}
					}
					
				}
				
			}
			
		}		 	
		
	}

}
