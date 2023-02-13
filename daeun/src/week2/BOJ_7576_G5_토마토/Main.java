package week2.BOJ_7576_G5_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		M = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);
		int[][] day = new int[N][M];
		
		map= new int[N][M];
		for(int i=0;i<N;i++) {
			String[] split2 = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(split2[j]);
			}
			
		}
		
		Queue<int[]> tomato = new ArrayDeque<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 1) { //익은 토마토를 큐에 넣음
					day[i][j] = 1; //날짜에 1 넣기
					tomato.add(new int[] {i,j});
				}
			}
		}
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		while(!tomato.isEmpty()) { //큐가 빌 때까지
			int now[] = tomato.poll();
			int x = now[0];
			int y = now[1];
			
			for(int i=0;i<4;i++) { //사방탐색
				int X = x + dx[i];
				int Y = y + dy[i];
				
				if(X<0 || Y<0 || X>N-1 || Y>M-1) { //범위 밖이면
					continue;
				}
				if(map[X][Y]!=0) { //안 익은 토마토가 아니면 다음으로
					continue;
				}
				
				map[X][Y] = map[X][Y] + 1; //안익은 토마토는 +1해서 익게 하고
				day[X][Y] = day[x][y] + 1;
				tomato.add(new int[] {X, Y}); //큐에 넣기
			}
		}
		
		boolean flag = true;
		int max = 0;
		out:
		for(int i=0; i<N;i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) { //안 익은 토마토가 있다면 
					flag = false; 
					break out;
				}
				max = Math.max(max, day[i][j]);
			}
		}
		if(flag) {
			System.out.println(max - 1); 
		}
		else {
			System.out.println(-1); 
		}
		
	}
}
