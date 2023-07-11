package week21.BOJ_1726_G3_로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Robot {
		int r,c,d,count;

		public Robot(int r, int c, int d, int count) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.count = count;
		}
	}
	
	static int M,N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[M][N];
		for (int i=0; i<M;i++) {
			map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		st = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		if (d==2) d=3;
		else if (d==3) d=2;		
		Robot start = new Robot(r-1,c-1,d,0);
		st = new StringTokenizer(in.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		if (d==2) d=3;
		else if (d==3) d=2;
		Robot end = new Robot(r-1,c-1,d,0);
		
		boolean[][][] visited = new boolean[M][N][4];
		visited[start.r][start.c][start.d-1] = true;
		Queue<Robot> queue = new ArrayDeque<Main.Robot>();
		queue.offer(start);
		 
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		
		Robot cur;
		int nr,nc,nd;		
		while(!queue.isEmpty()) {
			 cur = queue.poll();			 			
			 
			 if (cur.r == end.r && cur.c == end.c &&
					 cur.d == end.d) {
				 System.out.println(cur.count);
				 break;
			 }
			 
			 // go (1,2,3)
			 for (int i=1; i<=3; i++) {
				 nr = cur.r+i*dr[cur.d-1];
				 nc = cur.c+i*dc[cur.d-1];
				 if (isin(nr,nc) && !visited[nr][nc][cur.d-1] && map[nr][nc]==0) {
					 visited[nr][nc][cur.d-1] = true;					 
					 queue.offer(new Robot(nr,nc,cur.d,cur.count+1));
				 }else if (isin(nr,nc) && map[nr][nc]==1) break;
			 }
			 
			 // turn (left,right)
			 nd = cur.d-1<1 ? 4+(cur.d-1) : cur.d-1;
			 if (!visited[cur.r][cur.c][nd-1]) {				 
				 visited[cur.r][cur.c][nd-1] = true;
				 queue.offer(new Robot(cur.r,cur.c,nd,cur.count+1));				 
			 }
			 nd = cur.d+1>4 ? (cur.d+1)-4 : cur.d+1;
			 if (!visited[cur.r][cur.c][nd-1]) {				 
				 visited[cur.r][cur.c][nd-1] = true;
				 queue.offer(new Robot(cur.r,cur.c,nd,cur.count+1));				 
			 }			 
		}
		
	}

	private static boolean isin(int r, int c) {
		if (0<=r && r<M && 0<=c && c<N) return true;
		else return false;
	}
	
}
