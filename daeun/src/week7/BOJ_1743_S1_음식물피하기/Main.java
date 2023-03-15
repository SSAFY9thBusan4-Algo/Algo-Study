package week7.BOJ_1743_S1_음식물피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, M, K, map[][], max;
	static boolean[][] visit;
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String[] split = br.readLine().split(" ");
       N = Integer.parseInt(split[0]);
       M = Integer.parseInt(split[1]);
       K = Integer.parseInt(split[2]);
       map = new int[N][M];
       visit = new boolean[N][M];
       
       int r, c;
       for(int i=0;i<K;i++) {
    	   split = br.readLine().split(" ");
    	   r = Integer.parseInt(split[0])-1;
           c = Integer.parseInt(split[1])-1;
           map[r][c] = 1;
       }
      
       for(int i=0;i<N;i++) {
    	   for(int j=0;j<M;j++) {
    		   if(map[i][j]==1 && !visit[i][j]) {
    			   bfs(i, j);
    		   }
    	   }
       }
       System.out.println(max);
	}
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y});
		visit[x][y] = true;
		int count = 0; //합쳐진 음식물 크기
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			count++;
			for(int d=0;d<4;d++) {
				int X = temp[0]+dx[d];
				int Y = temp[1]+dy[d];
				if(X<0 || X>=N || Y<0 || Y>=M || map[X][Y] != 1 || visit[X][Y]) {
					continue;
				}
				queue.offer(new int[] {X, Y});
				visit[X][Y] = true;
			}
		}
		max = Math.max(max, count);
	}
}