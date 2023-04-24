package week10.BOJ_2573_G4_빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {

	private static int N, M;
	private static int[][] map;
	private static boolean flag;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = in.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		// 1년씩 빙산 줄이기
		int y = 0;
		while(true) {
			
			// 빙산 줄일 수 있는지 확인하고 줄일 수 있으면 줄이기
			if (check()) {
				y++;
				
//				for (int i=0; i<N; i++) {
//					System.out.println(Arrays.toString(map[i]));
//				}
//				System.out.println();
			
				// 빙산 덩이 세기
				if (count() > 1) {
					break;
				}			
			}	
			else {
				y = 0;
				break;
			}
		}
		
		System.out.println(y);
	}

	private static int[] dr = {-1,0,1,0};
	private static int[] dc = {0,1,0,-1};
	private static boolean check() {
		// 녹을 수 있는 빙산 리스트에
		List<int[]> list = new ArrayList<int[]>();
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (map[r][c] > 0) {  // 빙산이면 4방향 확인
					int cnt = 0;
					for (int d=0; d<4; d++) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						if (0<=nr && nr<N && 0<=nc && nc<M &&
								map[nr][nc] == 0) cnt++;
					}
					if (cnt > 0) list.add(new int[] {r,c,cnt});
				}
			}
		}
		
		// 빙산 녹이기
		for (int[] bing : list) {
			map[bing[0]][bing[1]] -= bing[2];
			if (map[bing[0]][bing[1]]<0) {
				map[bing[0]][bing[1]] = 0;
			}
		}
		
		return list.size()>0 ? true : false;
	}
	
	private static int count() {
		int cnt = 0;
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (!visited[r][c] && map[r][c]!=0) {
					cnt++;
					visited[r][c] = true;
					queue.offer(new int[] {r,c});
					while (!queue.isEmpty()) {
						
						int[] poll = queue.poll();
						int cr = poll[0];
						int cc = poll[1];
						
						for (int d=0; d<4; d++) {
							int nr = cr + dr[d];
							int nc = cc + dc[d];
							if (0<=nr && nr<N && 0<=nc && nc<M
									&& !visited[nr][nc] && map[nr][nc]!=0) {
								visited[nr][nc] = true;
								queue.offer(new int[] {nr,nc});
							}
						}
						
					}
				}
			}
		}
		
		return cnt;
	}
	
}
