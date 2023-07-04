package src.week19.BOJ_2615_S1_오목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static char[][] map;
	private static int[][] delta = {{0,1},{1,0},{1,1},{-1,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		map = new char[19][19];
		for(int i = 0 ; i < 19 ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < 19 ; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		//solve
		boolean flag = true;
		loop : for(int i = 0 ; i < 19 ; i++) {
			for(int j = 0 ; j < 19 ; j++) {
				
				if(map[i][j] != '0') {
					if(check(i,j)) {
						flag = false;
						System.out.println(map[i][j]);
						System.out.println((i+1)+" "+(j+1));
						break loop;
					}
				}
			}
		}
		if(flag) System.out.println(0);
	}

	private static boolean check(int i, int j) {
		char cur = map[i][j];
		
		for(int[] d : delta) { //방향
			int x = i, y = j, cnt = 0;
			
			// 해당 방향으로 5개가 되는지.
			while(map[x][y] == cur) {
				cnt++;
				x += d[0];
				y += d[1];
				if(x < 0 || x >= 19 || y < 0 || y >= 19) break;
			}
			
			// 반대방향으로 더 있는지.
			if(cnt == 5) {
				x = i - d[0];
				y = j - d[1];
				if(x < 0 || x >= 19 || y < 0 || y >= 19) {}
				else {
					if(map[x][y] == cur) cnt++;
				}
			}
			
			if(cnt == 5) return true;
		}
		
		return false;
	}
}
