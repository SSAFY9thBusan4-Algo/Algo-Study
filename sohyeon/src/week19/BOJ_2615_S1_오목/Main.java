package week19.BOJ_2615_S1_오목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int result = 0;
	static int[][] map = new int[19][19];
	
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i=0; i<19; i++) {
			map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		
		for (int i=0; i<19; i++) {
			for (int j=0; j<19; j++) {
				if (map[i][j]!=0) {
					for (int d=0; d<8; d++) {
						int rd = d-4>=0 ? d-4 : 8+(d-4);
						if (!isin(i+dr[rd],j+dc[rd]) || 
								(map[i+dr[rd]][j+dc[rd]]) != map[i][j]) {							
							dfs(i,j,1,i,j,d);							
						}
					}					
				}
			}
		}
		
		if (result == 0) System.out.println(0);
	}

	private static void dfs(int r, int c, int cnt, int sr, int sc, int d) {

		if (result != 0) return;
		
		int nr = r+dr[d];
		int nc = c+dc[d];
		
		if ((isin(nr,nc) && map[nr][nc]!=map[sr][sc]) || !isin(nr,nc)) {			
			if (cnt == 5) {
				result = map[sr][sc];
				System.out.println(result);
				if (sc<c || (sc==c && sr<r)) {
					System.out.println((sr+1)+ " " + (sc+1));						
				}else if (sc>c || (sc==c && sr>r)) {
					System.out.println((r+1)+ " " + (c+1));						
				}
				return;
			}
		}
		else if (isin(nr,nc) && map[nr][nc]==map[sr][sc] && cnt < 5) {
			dfs(nr,nc,cnt+1, sr,sc,d);
		}
		
	}

	private static boolean isin(int r, int c) {
		if (0<=r && r<19 && 0<=c && c<19) return true;
		else return false;
	}
	
}
