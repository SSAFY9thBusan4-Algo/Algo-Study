package week7.BOJ_1743_S1_음식물피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
	private static int[][] map;
	private static int N,M,result;
	private static boolean[][] visited;
	
    public static void main(String[] args) throws IOException {
	
	   BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	   
	   StringTokenizer st = new StringTokenizer(in.readLine());
	   N = Integer.parseInt(st.nextToken());
	   M = Integer.parseInt(st.nextToken());
	   int K = Integer.parseInt(st.nextToken());
	   
	   map = new int[N][M];
	   
	   for (int i=0; i<K; i++) {
		   int rc[] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		   map[rc[0]-1][rc[1]-1] = 1;
	   }
	   
	   visited = new boolean[N][M];
	   for (int r=0; r<N; r++) {
		   for (int c=0; c<M; c++) {
			   if (map[r][c]==1 && !visited[r][c]) {
				   bfs(r,c);
			   }			   
		   }
	   }
	   
	   System.out.println(result);
	   
   }

    private static int[] dr = {-1,0,1,0};
    private static int[] dc = {0,1,0,-1};
	private static void bfs(int r, int c) {
		
		int cnt = 1;
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			
			int[] rc = queue.poll();
			int row = rc[0];
			int col = rc[1];
			
			for (int i=0; i<4; i++) {
				int nr = row+dr[i];
				int nc = col+dc[i];
				if (0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]==1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr,nc});
					cnt++;
				}
			}
		}		
		result = Math.max(result, cnt);
		
	}

}
