package week9.BOJ_11559_G4_PuyoPuyo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.io.IOException;

public class Main {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
	static char[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static ArrayList<Point> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new char[12][6];
		for(int i=0;i<12;i++) {
			String str = br.readLine();
			for(int j=0;j<6;j++) {
				map[i][j] = str.charAt(j);
			}
		} //입력 끝
		
		int result = 0;
		while(true) {
			boolean flag = false;
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					
					if(map[i][j]!='.') { //뿌요 나오면
						list = new ArrayList<>(); //연쇄로 없앨 목록
						bfs(i, j, map[i][j]);
						
						if(list.size()>3) { //터질 수 있는 경우라면
							flag = true; //연쇄
							for(int k=0;k<list.size();k++) {
								Point p = list.get(k);
								map[p.x][p.y] = '.'; //일단 터뜨렸고
							}
						}
					}
				}
			}
			move(); //움직이기
			if(!flag) break;
			result++;
		}
		System.out.println(result);
	}
	private static void bfs(int i, int j, char c) {
		boolean[][] visit = new boolean[12][6];
		Queue<Point> queue = new ArrayDeque<>();
		Point p = new Point(i, j);
		queue.offer(p);
		list.add(p);
		visit[i][j] = true;
		while(!queue.isEmpty()) {
			p = queue.poll();
			for(int d=0;d<4;d++) {
				int X = p.x+dx[d];
				int Y = p.y+dy[d];
				
				if(X<0 || X>11 || Y<0 || Y>5 || visit[X][Y] ||map[X][Y] != c)
					continue;
				p = new Point(X, Y);
				queue.offer(p);
				list.add(p);
				visit[X][Y] = true;
			}
			
		}
		
	}
	private static void move() {
		for(int j=0;j<6;j++) {
			for(int i=11;i>=0;i--) { //아래에서부터
				if (map[i][j] != '.') {
                    int next = i;
                    while (true) { //이 부분에서 계속 틀렸다
                    	next++;
                        if (next<12 && map[next][j] == '.') {
                            map[next][j] = map[next-1][j];
                            map[next-1][j] = '.';
                        } else {
                            break;
                        }
                    }
                }
			}
		}
	}	
}