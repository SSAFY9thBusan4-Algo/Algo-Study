package src.week8.BOJ_6593_G5_상범빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	private static char[][][] map;
	private static int L, R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String[] input = br.readLine().split(" ");
			L = Integer.parseInt(input[0]);
			R = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
			
			if(L == 0 && R == 0 && C == 0) break;
			
			int sz = 0, sx = 0, sy = -1;
			String in;
			map = new char[L][R][C];
			for(int i = 0  ; i < L; i++) {
				for(int j = 0 ; j < R; j++) {
					in = br.readLine();
					map[i][j] = in.toCharArray();
					if(sy == -1 && (sy=in.indexOf('S')) != -1) {	//find Start
						sz = i;
						sx = j;						
					}
				}
				br.readLine();
			}
			
			// solve
			int time = getEscapeTime(sz, sx, sy);
			
			if(time == -1) {
				sb.append("Trapped!\n");
			}
			else {
				sb.append("Escaped in ").append(time).append(" minute(s).\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static int[][] delta = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
	private static int getEscapeTime(int sz, int sx, int sy) {
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {sz, sx, sy});
		
		int time = 0;
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			while(size-- > 0) {
				int[] cur = queue.poll();
				
				for(int d = 0 ; d < delta.length ; d++) {
					int dz = cur[0] + delta[d][0];
					int dx = cur[1] + delta[d][1];
					int dy = cur[2] + delta[d][2];
					
					if(dz < 0 || dz >= L || dx < 0 || dx >= R || dy < 0 || dy >= C) {
						continue;
					}
					if(map[dz][dx][dy] == 'E') return time+1;
					else if(map[dz][dx][dy] == '.') {
						//visited
						map[dz][dx][dy] = '-';
						queue.offer(new int[] {dz, dx, dy});
					}
					
				}
				
			}
			time++;
		}
		
		return -1;
	}
}