package week8.BOJ_9328_G1_열쇠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main2 {
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
	static ArrayList<Key> door;
	static boolean[][] visit; 
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
			map = new char[h][w];
			for(int i=0;i<h;i++) {
				str = br.readLine();
				for(int j=0;j<w;j++) {
					map[i][j] = str.charAt(j);
				}
			} //지도 입력이 끝나면
			keyList = new ArrayList<>();
			str = br.readLine();
			if(!str.equals("0")) {
				for(int i=0;i<str.length();i++) {
					keyList.add(Character.toUpperCase(str.charAt(i))); //입력받을 때 대문자로 바꿔버리기
				}
			} // 열쇠 입력도 끝
			
			visit = new boolean[h][w];
			ArrayList<Point> enterance = new ArrayList<>();
			door = new ArrayList<>();
			count = 0;
			for(int i=0;i<h;i++) {
				char checkL = map[i][0]; //왼쪽
				char checkR = map[i][w-1]; //오른쪽				
				if(!visit[i][0]) {
					if(checkL=='.') { //빈 공간
						enterance.add(new Point(i, 0));
						visit[i][0] = true;
					}
					else if(Character.isLowerCase(checkL)) { //소문자
						enterance.add(new Point(i, 0));
						keyList.add(Character.toUpperCase(map[i][0]));
						visit[i][0] = true;
					}
					else if(Character.isUpperCase(checkL)) { //대문자
						if(keyList.contains(checkL)) {
							enterance.add(new Point(i, 0));
							visit[i][0] = true;
						}
						door.add(new Key(i, 0, map[i][0]));
					}
					else if(checkL =='$') {
						count++;
						enterance.add(new Point(i, 0));
						visit[i][0] = true;
					}
				}
				if(!visit[i][w-1]) {
					if(checkR=='.') { //빈 공간
						enterance.add(new Point(i, w-1));
						visit[i][w-1] = true;
					}
					else if(Character.isLowerCase(checkR)) { //소문자
						enterance.add(new Point(i, w-1));
						keyList.add(Character.toUpperCase(map[i][w-1]));
						visit[i][w-1] = true;
					}
					else if(Character.isUpperCase(checkR)) { //대문자
						if(keyList.contains(checkR)) {
							enterance.add(new Point(i, w-1));
							visit[i][w-1] = true;
						}
						door.add(new Key(i, w-1, map[i][w-1]));
					}
					else if(checkR =='$') {
						count++;
						enterance.add(new Point(i, w-1));
						visit[i][w-1] = true;
					}
				}
				
			}
			
			//위, 아래
			for(int i=0;i<w;i++) {
				char checkL = map[0][i]; //위
				char checkR = map[h-1][i]; //아래
				if(!visit[0][i]) {
					if(checkL=='.') { //빈 공간
						enterance.add(new Point(0, i));
						visit[0][i] = true;
					}
					else if(Character.isLowerCase(checkL)) { //소문자
						enterance.add(new Point(0, i));
						keyList.add(Character.toUpperCase(map[0][i]));
						visit[0][i] = true;
					}
					else if(Character.isUpperCase(checkL)) { //대문자
						if(keyList.contains(checkL)) {
							enterance.add(new Point(0, i));
							visit[0][i] = true;
						}
						door.add(new Key(0, i, map[0][i]));
					}
					else if(checkL =='$') {
						count++;
						enterance.add(new Point(0, i));
						visit[0][i] = true;
					}
				}
				if(!visit[h-1][i]) {
					if(checkR=='.') { //빈 공간
						enterance.add(new Point(h-1, i));
						visit[h-1][i] = true;
					}
					else if(Character.isLowerCase(checkR)) { //소문자
						enterance.add(new Point(h-1, i));
						keyList.add(Character.toUpperCase(map[h-1][i]));
						visit[h-1][i] = true;
					}
					else if(Character.isUpperCase(checkR)) { //대문자
						if(keyList.contains(checkR)) {
							enterance.add(new Point(h-1, i));
							visit[h-1][i] = true;
						}
						door.add(new Key(h-1, i, map[h-1][i]));
					}
					else if(checkR =='$') {
						count++;
						enterance.add(new Point(h-1, i));
						visit[h-1][i] = true;
					}
				}
			}
			
			boolean flag = false;
			//입구가 없는 경우
			if(enterance.size()==0) {
				sb.append("0\n");
				flag = true;
				continue; //이 테케는 끝
			}
			
			//입구별로 bfs
			for(Point p: enterance) {
				bfs(p);
			}			
			if(!flag) {
				sb.append(count).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static Queue<Point> queue;
	private static void bfs(Point p) {
		queue = new ArrayDeque<>();
		queue.offer(p);
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			for(int d=0;d<4;d++) {
				int X = now.x+dx[d];
				int Y = now.y+dy[d];
				if(X<0 || X>=h || Y<0 || Y>=w || map[X][Y]=='*' || visit[X][Y]) {//범위 밖이거나 벽이거나 방문했던 곳이면
					continue;
				}
				if(map[X][Y] == '.') { //빈 공간이면
					queue.offer(new Point(X, Y));
					visit[X][Y] = true;
				}
				else if(map[X][Y] == '$') { //문서가 있는 곳이면
					count++;
					map[X][Y] = '.';
					queue.offer(new Point(X, Y));
					visit[X][Y] = true;
				}
				else if(Character.isUpperCase(map[X][Y])){ //문인 곳
					if(keyList.contains(map[X][Y])) { //열 수 있으면
						map[X][Y] = '.';
						queue.offer(new Point(X, Y));
						visit[X][Y] = true;
					}
					else { //열 수 없는 것
						door.add(new Key(X, Y, map[X][Y]));
						visit[X][Y] = true;
					}
				}
				else if(Character.isLowerCase(map[X][Y])){ //열쇠가 나왔다면
					char temp = Character.toUpperCase(map[X][Y]);
					keyList.add(temp);
					map[X][Y] = '.';
					queue.offer(new Point(X, Y));
					visit[X][Y] = true;
					
					for(int idx = door.size()-1;idx>=0;idx--) {
						Key key = door.get(idx);
						if(key.alpha == temp) { //문 중 알파벳이 열쇠와 같다면
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