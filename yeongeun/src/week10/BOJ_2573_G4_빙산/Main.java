package src.week10.BOJ_11559_G4_BOJ_2573_G4_빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);
		
		int[][] map = new int[N][];
		for(int i = 0 ; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		// solve
		int[][] temp;
		boolean[][] checked;
		int time = 0;
		boolean flag = false;
		while(true) {
			// 1. 빙산 녹이기
			// 1-1. 확인
			temp = new int[N][M];
			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < M; j++) {
					
					if(j != 0) {
						if(map[i][j-1] == 0 && map[i][j] > 0) {
							temp[i][j]++;
						}
						else if(map[i][j-1] > 0 && map[i][j] == 0) {
							temp[i][j-1]++;
						}
					}
					
					if(i != 0) {
						if(map[i-1][j] == 0 && map[i][j] > 0) {
							temp[i][j]++;
						}
						else if(map[i-1][j] > 0 && map[i][j] == 0) {
							temp[i-1][j]++;
						}
					}
				}
			}
			
			// 1-2. 녹이기
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < M; j++) {
					if(temp[i][j] > 0) {
						map[i][j] = map[i][j] - temp[i][j] < 0 ? 0 : map[i][j] - temp[i][j];
					}
				}
			}
			
			
			// 2. 몇 덩이인지 세기
			checked = new boolean[N][M];
			int count = 0;
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < M ; j++) {
					if(!checked[i][j] && map[i][j] != 0 ) {
						bfs(i,j, map, checked);
						count++;
					}
				}
			}
			
			// 3. 확인
			time++;
			if(count >= 2) {
				flag = true;
				break;
			}
			else if(count == 0) {
				break;
			}
			
		}
		

		// output
		if(flag) System.out.println(time);
		else System.out.println(0);
	}

	private static final int[][] delta = {{-1,0},{1,0},{0,1},{0,-1}};
	private static void bfs(int i, int j, int[][] map, boolean[][] checked) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j});
		checked[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();

			for(int d = 0 ; d < 4 ;d++) {
				int dx = cur[0] + delta[d][0];
				int dy = cur[1] + delta[d][1];
				
				if(dx >= 0 && dx < map.length && dy >= 0 && dy < map[0].length 
						&& !checked[dx][dy] && map[dx][dy] != 0) {
					queue.offer(new int[] {dx,dy});
					checked[dx][dy] = true;
				}
			}
		}
		
	}
}
