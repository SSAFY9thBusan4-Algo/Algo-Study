package src.week18.BOJ_16197_G4_두동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main16197_두동전 {
	
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Coins {
		Point[] pl;
		int t;
		public Coins(Point[] pl, int t) {
			this.pl = pl;
			this.t = t;
		}
	}
	
	private static int N, M, result;
	private final static char COIN = 'o', BLANK = '.', WALL='#';
	private final static int[][] delta = {{0,-1},{0,1},{-1,0},{1,0}};
	private static char[][] map;
	private static boolean[][][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		
		map = new char[N][M];
		for(int i = 0 ; i < N ; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 1. 초기위치 구하기
		Point p1 = null, p2 = null;
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == COIN) {
					map[i][j] = BLANK;
					if(p1 == null) p1 = new Point(i,j);
					else {
						p2 = new Point(i,j);
						break;
					}
				}
			}
		}
		
		// 2. 이동 
		visited = new boolean[N][M][N][M];
		result = bfs(p1, p2);
		
		System.out.println(result);
	}
	
	private static int bfs(Point p1, Point p2) {
		
		Queue<Coins> queue = new ArrayDeque<>();
		queue.offer(new Coins(new Point[] {p1,p2}, 0));
		visited[p1.x][p1.y][p2.x][p2.y] = true;
		
		while(!queue.isEmpty()) {
			Coins cur = queue.poll();
			int t = cur.t;
			if(t >= 10) break;
			
			for(int[] d : delta ) {
				Point m1 = move(cur.pl[0], d);
				Point m2 = move(cur.pl[1], d);
				
				// 하나만 떨어지면
				boolean o1 = m1 == null, o2 = m2 == null;
				if(o1 ^ o2) return t+1;
				else if(o1 && o2) continue;
				
				// 2개 모두 살아있으면 다시 굴린다. 
				if(!visited[m1.x][m1.y][m2.x][m2.y]) {
					visited[m1.x][m1.y][m2.x][m2.y] = true;
					queue.offer(new Coins(new Point[] {m1,m2}, t+1));
				}
			}
			
		}
		
		return -1;
	}
	
	private static Point move(Point p, int[] d) {
		int mx = p.x + d[0];
		int my = p.y + d[1];
		
		if(mx < 0 || mx >= N || my < 0 || my >= M) return null;
		if(map[mx][my] == WALL) return p;
		return new Point(mx,my);
	}
	
}
