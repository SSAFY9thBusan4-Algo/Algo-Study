package week8.BOJ_9328_G1_열쇠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static class Key{
		int x;
		int y;
		char alpha;
		public Key(int x, int y, char alpha) {
			super();
			this.x = x;
			this.y = y;
			this.alpha = alpha;
		}
	}
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {1, -1, 0, 0};
	static int h, w, count;
	static char[][] map;
	static ArrayList<Character> keyList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		String[] split;
		String str;
		for(int t=0;t<T;t++) {
			split = br.readLine().split(" ");
			h = Integer.parseInt(split[0]);
			w = Integer.parseInt(split[1]);
			map = new char[h+2][w+2];
			for(int i=0;i<h+2;i++) {
				for(int j=0;j<w+2;j++) {
					map[i][j] = '.';
				}
			}
			for(int i=0;i<h;i++) {
				str = br.readLine();
				for(int j=0;j<w;j++) {
					map[i+1][j+1] = str.charAt(j);
				}
			}
			keyList = new ArrayList<>();
			str = br.readLine();
			if(!str.equals("0")) {
				for(int i=0;i<str.length();i++) {
					keyList.add(Character.toUpperCase(str.charAt(i)));
				}
			}
			count = 0;
			bfs();
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}
	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visit = new boolean[h+2][w+2];
		ArrayList<Key> door = new ArrayList<>();
		queue.offer(new Point(0, 0));
		visit[0][0] = true;
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			for(int d=0;d<4;d++) {
				int X = now.x+dx[d];
				int Y = now.y+dy[d];
				if(X<0 || X>=h+2 || Y<0 || Y>=w+2 || map[X][Y]=='*' || visit[X][Y]) {
					continue;
				}
				if(map[X][Y] == '.') {
					queue.offer(new Point(X, Y));
					visit[X][Y] = true;
				}
				else if(map[X][Y] == '$') {
					count++;
					map[X][Y] = '.';
					queue.offer(new Point(X, Y));
					visit[X][Y] = true;
				}
				else if(Character.isUpperCase(map[X][Y])){
					if(keyList.contains(map[X][Y])) {
						map[X][Y] = '.';
						queue.offer(new Point(X, Y));
						visit[X][Y] = true;
					}
					else {
						door.add(new Key(X, Y, map[X][Y]));
					}
				}
				else if(Character.isLowerCase(map[X][Y])){
					char temp = Character.toUpperCase(map[X][Y]);
					keyList.add(temp);
					map[X][Y] = '.';
					queue.offer(new Point(X, Y));
					visit[X][Y] = true;
					for(int idx = door.size()-1;idx>=0;idx--) {
						Key key = door.get(idx);
						if(key.alpha == temp) {
							door.remove(idx);
							map[key.x][key.y] = '.';
							queue.offer(new Point(key.x, key.y));
							visit[key.x][key.y] = true;
						}
					}					
				}
			}			
		}
	}
}