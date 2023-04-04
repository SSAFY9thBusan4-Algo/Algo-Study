package src.week9.BOJ_11559_G4_PuyoPuyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static char[][] map;
	private static boolean[][] visited;
	private final static int[][] delta = {{-1,0}, {1,0}, {0,1}, {0,-1}};
	private final static char EMPTY = '.';

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[12][];
		for(int i = 0 ; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int result = 0;
		int r;
		while(true) {
			r = run();
			if(r == 0) break;
			else result ++;		//여러 그룹이 터지더라도 한번의 연쇄가 추가된다.

			gravity();
		}
		
		System.out.println(result);
	}

	private static void gravity() {
		
		for(int j = 0 ; j < 6 ; j++) {
			int empty = 0;
			for(int i = 11 ; i >= 0 ; i--) {
				if(map[i][j] == EMPTY) {
					empty++;
				}
				else if(empty != 0){
					map[i+empty][j] = map[i][j];
					map[i][j] = EMPTY;
				}
			}
		}
	}

	private static int run() {
		
		visited = new boolean[12][6];

		int count = 0;
		for(int i = 0 ; i < 12; i++) {
			for(int j = 0 ; j < 6; j++) {
				if(map[i][j] != EMPTY && !visited[i][j]) {
					if(isCombo(i,j)) count++;	// bfs해서 4개 터지는지.
				}
			}
		}
		
		return count;
	}

	
	private static boolean isCombo (int i, int j) {

		int count = 1;
		int idx = 0;
		
		List<int[]> list = new ArrayList<>();	// 큐 대신 리스트. 4개 되면 지울라고.
		list.add(new int[] {i,j});
		visited[i][j] = true;
		
		while(idx < count) {
			int[] cur = list.get(idx++);
			
			// 4방탐색
			for(int d = 0 ; d < 4; d++) {
				int dx = cur[0] + delta[d][0];
				int dy = cur[1] + delta[d][1];
				
				if(dx >= 0 && dx < 12 && dy >= 0 && dy < 6) {
					
					if(map[dx][dy] == map[i][j] && !visited[dx][dy]) {
						visited[dx][dy] = true;
						list.add(new int[] {dx,dy});
						count++;
					}
				}
			}
		}
		
		// 콤보면 map에서 없애기.
		if(idx >= 4) {
			remove(list);
			return true;
		}
		
		return false;
	}
	
	private static void remove(List<int[]> list) {
		for(int[] p : list) {
			map[p[0]][p[1]] = EMPTY;
		}
	}
	
	
}
