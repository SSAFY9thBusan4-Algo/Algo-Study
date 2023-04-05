package week8.BOJ_6593_G5_상범빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	private static int L, R, C;
	private static char[][][] map;
	private static boolean[][][] visited;
	private static int[] dl = {0,0,0,0,-1,1};
	private static int[] dr = {0,0,1,-1,0,0};
	private static int[] dc = {1,-1,0,0,0,0};

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			
			String[] input = in.readLine().split(" ");
			L = Integer.parseInt(input[0]);
			R = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
			
			map = new char[L][R][C];
			visited = new boolean[L][R][C];
			
			if(L==0 && R==0 && C==0) {
                break;
            }
			
			for (int l=0; l<L; l++) {
				for (int r=0; r<R; r++) {
					map[l][r] = in.readLine().toCharArray();
				}
				in.readLine();
			}
			
			int sl = 0;
			int sr = 0;
			int sc = 0;
			for (int l=0; l<L; l++) {
				for (int r=0; r<R; r++) {
					for (int c=0; c<C; c++) {
						if (map[l][r][c] == 'S') {
							sl = l;
							sr = r;
							sc = c;
						}
					}
				}
			}
			
			visited[sl][sr][sc] = true;			
			bfs(sl, sr, sc);	
			
		}
		
	}

	private static void bfs(int sl, int sr, int sc) {			
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {sl, sr, sc, 0});
		
		boolean flag = false;
		while (!queue.isEmpty() && !flag) {
			int[] poll = queue.poll();
			
			int l = poll[0];
			int r = poll[1];
			int c = poll[2];
			int cnt = poll[3];					
			
			for (int d=0; d<6; d++) {
				int nl = l + dl[d];
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (0<=nl && nl<L && 0<=nr && nr<R && 0<=nc && nc<C
						&& !visited[nl][nr][nc]) {
					visited[nl][nr][nc] = true;
					if (map[nl][nr][nc] == '.') {						
						queue.offer(new int[] {nl, nr, nc, cnt+1});							
					}
					else if (map[nl][nr][nc] == 'E') {
						System.out.println("Escaped in "+(cnt+1)+" minute(s).");
						flag = true;
						break;
					}					
				}
			}			
		}
		if (!flag) System.out.println("Trapped!");
		
	}

	
}
