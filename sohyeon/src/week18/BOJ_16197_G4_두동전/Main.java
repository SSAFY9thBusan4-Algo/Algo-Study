package week18.BOJ_16197_G4_두동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static char[][] map;
	static int result = -1;
	
	static class Coin {
		int r;
		int c;
		int cnt;
		public Coin(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Coin [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
		}	
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 동전 위치 찾기
		int cnt = 0;
		Coin[] coins = new Coin[2];
		for1: for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 'o') {
					coins[cnt++] = new Coin(i,j,0);
					if (cnt == 2) {
						break for1;
					}
				}
			}
		}
		
		bfs(coins);
		
		System.out.println(result);
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static void bfs(Coin[] coins) {
		
		boolean[][][][] visited = new boolean[N][M][N][M];
		Queue<Coin[]> queue = new ArrayDeque<Main.Coin[]>();
		queue.offer(coins);
		visited[coins[0].r][coins[0].c][coins[1].r][coins[1].c] = true;			
		visited[coins[1].r][coins[1].c][coins[0].r][coins[0].c] = true;			
		
		Coin coin1, coin2;
		Boolean isin1, isin2;
		while(!queue.isEmpty()) {
			coins = queue.poll();
			coin1 = coins[0];
			coin2 = coins[1];
			
			if (coin1.cnt >= 10) {
				result = -1;
				return;
			}
			
			for (int d=0; d<4; d++) {
				int nr1 = coin1.r+dr[d];
				int nc1 = coin1.c+dc[d];
				int nr2 = coin2.r+dr[d];
				int nc2 = coin2.c+dc[d];									
				
				isin1 = isin(nr1, nc1);
				isin2 = isin(nr2, nc2);
				
				
				// 벽인지 체크(벽이면 원위치로)
				int check = 0;
				if (isin1 && map[nr1][nc1] == '#') {
					nr1 = coin1.r;
					nc1 = coin1.c;
					check++;
				}
				if (isin2 && map[nr2][nc2] == '#') {
					nr2 = coin2.r;
					nc2 = coin2.c;
					check++;
				}
				if (check ==  2) continue;

				// 둘 다 떨어졌다면
				if (!isin1 && !isin2) {
					continue;
				}
				
				// 한 동전만 떨어진다면
				if ((isin1 && !isin2) || (!isin1 && isin2)) {
					result = coin1.cnt+1;					
					return;
				}
				// 두 동전 다 떨어지지 않았다면
				// 버튼 누른 횟수 체크
				else if (isin1 && isin2 
						&& (!visited[nr1][nc1][nr2][nc2] && !visited[nr2][nc2][nr1][nc1])){
					visited[nr1][nc1][nr2][nc2] = true;
					visited[nr2][nc2][nr1][nc1] = true;
					queue.offer(new Coin[] {new Coin(nr1,nc1,coin1.cnt+1), 
							new Coin(nr2, nc2,coin2.cnt+1)});	
					
				}
			}
			
		}
		
	}
	private static Boolean isin(int r, int c) {
		if (0<=r && r<N & 0<=c && c<M) return true;
		else return false;
	}
	
}
