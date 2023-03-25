package week7.BOJ_2665_G4_미로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	private static int n;
	private static int[][] map;
	private static int[][] check;
	private static int[] dr = {-1,0,1,0};
	private static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(in.readLine());
		map = new int[n][n];
		check = new int[n][n];		
		for (int i=0; i<n; i++) {
			Arrays.fill(check[i], Integer.MAX_VALUE);
		}
		
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(in.readLine().split("")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		// 시작방부터 bfs
		bfs(0,0);
		
		System.out.println(check[n-1][n-1]);
		
	}

	// 다음 칸이 흰 방이면 그냥 감, 검은 방이면 이전에 방에 방문한 다른 경로보다 검은 방은 적게 방문한 상태이면 가고 아니면 pass
	private static void bfs(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {row, col});
		check[row][col] = 0;
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			int r = poll[0];
			int c = poll[1];
			
			if (r==n-1 && c==n-1) {
				continue;
			}
			
			// 이 방을 지난간 경로의 검은 방을 지나온 수가 현재 경로가 검은 방을 지나온 수보다 크면 check배열 갱신하고 지나감
			for (int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];				
				if (0<=nr && nr<n && 0<=nc && nc<n) {
					if (map[nr][nc] == 0 && (check[nr][nc]>check[r][c]+1)) { // 검은방인 경우
						check[nr][nc] = check[r][c]+1;
						queue.offer(new int[] {nr, nc});
					}
					else if (map[nr][nc] == 1 && (check[nr][nc]>check[r][c])) {  //흰방인 경우
						check[nr][nc] = check[r][c];
						queue.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}
	
}
