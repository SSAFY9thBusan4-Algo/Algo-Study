package week10.BOJ_2573_G4_빙산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.io.IOException;

public class Main {
	static int N, M, map[][];
	static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			split = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		} //입력 끝
		
		int result = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		while(true) {
			int area = findArea();//덩어리 개수 구하기
			if(area == 0) { //빙산이 다 녹았다.
				result = 0;
				break; 
			}
			if(area >=2) {
				break;
			}
			
			int sea=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] != 0) {
						sea = 0;
						for(int d=0;d<4;d++) {
							int X = i+dx[d];
							int Y = j+dy[d];
							
							if(!check(X, Y) || map[X][Y]!=0) {
								continue;
							}
							sea++;
						}
						queue.offer(new int[] {i, j, map[i][j]-sea}); //녹인 빙산 큐에 넣기 (지장 안 가게)
					}
				}
			}
			
			while(!queue.isEmpty()) { //녹은 빙산 적용하기
				int[] now = queue.poll();
				map[now[0]][now[1]] = now[2]>0?now[2]:0; //맵에 넣기
			}
			
			result++;
			
		}
		
		System.out.println(result);
		
	}
	
	private static boolean check(int X, int Y) {
		if(X<0|| X>=N ||Y<0 || Y>=M) {
			return false;
		}
		return true;
	}
	
	private static int findArea() { 
		int cnt=0;
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0 && !visit[i][j]) {
					queue.offer(new int[] {i,j});
					visit[i][j] = true;
					
					while(!queue.isEmpty()) {
						int[] now = queue.poll();
						
						for(int d=0;d<4;d++) {
							int X = now[0]+dx[d];
							int Y = now[1]+dy[d];
							if(check(X, Y) && map[X][Y]!=0 && !visit[X][Y]) {
								queue.offer(new int[] {X,Y});
								visit[X][Y] = true;
							}
						}
					}
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}











