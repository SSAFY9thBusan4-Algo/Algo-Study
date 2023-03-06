package week5.SWEA_모의_1953_탈주범검거;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_모의_1953_탈주범검거 {
	
	private static StringBuilder sb = new StringBuilder();
	static int n, m, l, cnt;
	static int[][] arr;
	static int[][] typeTunnel = {{-1,0}, {1,0}, {0,1}, {0,-1}};
	static boolean[][] visited;
	static Queue<int[]> que;
			
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
					
			arr = new int[n][m];
			for(int i=0; i<n; i++ ) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			cnt = 0;
			bfs(r, c);
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int r, int c) {
		visited = new boolean[n][m];
		que = new ArrayDeque<>();
		
		visited[r][c] = true;
		que.offer(new int[]{r, c});
		int nx = 0, ny = 0;
		while(!que.isEmpty()) {
			if(l == 0) return;
			
			int size = que.size();
			l--;
			for(int depth=0; depth<size; depth++) {
				int[] temp = que.poll();
				cnt++;
				int x = temp[0];
				int y = temp[1];
				int type = arr[x][y];
				
				switch (type) {
				case 1:
					for(int i=0; i<4; i++) {
						nx = x + typeTunnel[i][0];
						ny = y + typeTunnel[i][1];
						
						isPossible(nx, ny, i);
					}
					break;
				case 2:
					for(int i=0; i<2; i++) {
						nx = x + typeTunnel[i][0];
						ny = y + typeTunnel[i][1];
						
						isPossible(nx, ny, i);
					}
					break;
				case 3:
					for(int i=2; i<4; i++) {
						nx = x + typeTunnel[i][0];
						ny = y + typeTunnel[i][1];
						
						isPossible(nx, ny, i);
					}
					break;
				case 4:
					for(int i=0; i<4; i+=2) {
						nx = x + typeTunnel[i][0];
						ny = y + typeTunnel[i][1];
						
						isPossible(nx, ny, i);
					}
					break;
				case 5:
					for(int i=1; i<3; i++) {
						nx = x + typeTunnel[i][0];
						ny = y + typeTunnel[i][1];
						
						isPossible(nx, ny, i);
					}
					break;
				case 6:
					for(int i=1; i<4; i+=2) {
						nx = x + typeTunnel[i][0];
						ny = y + typeTunnel[i][1];
						
						isPossible(nx, ny, i);
					}
					break;
				case 7:
					for(int i=0; i<4; i+=3) {
						nx = x + typeTunnel[i][0];
						ny = y + typeTunnel[i][1];
						
						isPossible(nx, ny, i);
					}
					break;
				}
			}
		}
	}
	
	static void isPossible(int nx, int ny, int i) {
		if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && arr[nx][ny] != 0) {
			
			int nextType = arr[nx][ny];	
			switch (i) {
			case 0:
				if(nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
					que.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
				break;
			case 1:
				if(nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
					que.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
				break;
			case 2:
				if(nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
					que.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
				break;
			case 3:
				if(nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
					que.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
				break;
			}
		}
	}
}