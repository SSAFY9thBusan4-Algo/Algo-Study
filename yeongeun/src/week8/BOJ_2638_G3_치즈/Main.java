package src.week8.BOJ_2638_G3_치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			return x == other.x && y == other.y;
		}
		
	}
	
	private static int N, M;
	final static int[][] delta = {{-1,0},{1,0},{0,1},{0,-1}};

	private static char[][] map;
	private static boolean[][] aircheck;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		
		map = new char[N][];
		String input;
		for(int i = 0 ; i < N ; i++) {
			input = br.readLine().replace(" ", "");
			map[i] = input.toCharArray();
		}
		
		// solve
		int time = 0;
		while(true) {
			// 1. dfs로 도달가능한 곳 체크
			aircheck = new boolean[N][M];
			air(0,0);
			
			// 2. 2변 이상 공기 접촉 되는 치즈 구하기
			Set<Point> cheeses = new HashSet<Point>();
			for(int i = 1 ; i < N-1 ; i++) {
				for(int j = 1 ; j < M-1 ; j++) {
					int cnt = 0;
					if(aircheck[i][j-1] != aircheck[i][j]) cnt++;
					if(aircheck[i][j+1] != aircheck[i][j]) cnt++;
					if(aircheck[i-1][j] != aircheck[i][j]) cnt++;
					if(aircheck[i+1][j] != aircheck[i][j]) cnt++;
					
					if(cnt >= 2) {
						cheeses.add(new Point(i,j));
					}
				}
			}
			
			if(cheeses.isEmpty()) break;
			else time++;
			
			// 3. 1시간 지나면 사라진다.
			for(Point ch : cheeses) {
				map[ch.x][ch.y] = '0';
			}
			
		}
		
		System.out.println(time);
		
	}
	private static void air(int i, int j) {
		aircheck[i][j] = true;
		
		for(int d = 0 ; d < 4; d++) {
			int dx = i + delta[d][0];
			int dy = j + delta[d][1];
			
			if(dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] == '0' && !aircheck[dx][dy]) {
				air(dx, dy);
			}
		}
	}
}
