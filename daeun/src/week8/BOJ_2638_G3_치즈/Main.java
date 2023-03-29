package week8.BOJ_2638_G3_치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, map[][];
	static boolean[][] visit, out;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Point> queue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		map = new int[N][M];
		out = new boolean[N][M];
		int total=0;
		for (int i = 0; i < N; i++) {
			split = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if(map[i][j] == 1) { //치즈 수 세기
					total++;
				}
			}
		}

		queue = new ArrayDeque<>();

		int count = 0;
		while(total != 0) { //치즈 다 녹으면 끝
			visit = new boolean[N][M];
			find(0, 0);
			melt();
			count++;
			int size = queue.size();
			total -= size;
			while(size>0) {
				Point p = queue.poll();
				map[p.x][p.y] = 0;
				size--;
			}	
		}
		System.out.println(count);
	}

	private static void find(int i, int j) { //dfs로 외부를 out에 넣기
		visit[i][j] = true;
		out[i][j] = true;
		for(int d=0;d<4;d++) {
			int x = i+dx[d];
			int y = j+dy[d];
			if(x<0 || x>=N || y<0 || y>=M || visit[x][y] || map[x][y]==1) {
				continue;
			}
			find(x, y);
		}
	}

	private static void melt() { //out 비교해서 녹는 치즈 찾기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					continue;
				}
				int temp = 0;
				for (int d = 0; d < 4; d++) {
					int X = i + dx[d];
					int Y = j + dy[d];
					if (0 <= X && X < N && 0 <= Y && Y < M && out[X][Y]) {
						temp++;
					}
				}
				if (temp >= 2) { //2보다 크면 녹는다
					queue.offer(new Point(i, j));					
				}
			}
		}
	}
}