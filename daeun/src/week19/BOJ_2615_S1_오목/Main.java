import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static class Point implements Comparable<Point>{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			if(this.y == o.y) {
				return this.x - o.x;
			}
			return this.y - o.y;
		}
	}
	static char[][] map;
	static int resultX, resultY;
	static int[] dx = {0, 0, -1, 1, -1, -1, 1, 1}; //좌 우 상 하 좌상 우상 우하 좌하
	static int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1}; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new char[20][20];
		for(int i=0;i<19;i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0;j<19;j++) {
				map[i+1][j+1] = str[j].charAt(0);
			}
		}
		
		//이긴 바둑알의 좌상단 가로, 세로 번호 출력
		boolean flag = false;
		out:
		for(int i=1;i<20;i++) {
			for(int j=1;j<20;j++) {
				if(map[i][j] !='0') {
					for(int d=0;d<8;d++) {
						if(check(i, j, d)) {
							flag = true;
							sb.append(map[i][j]+"\n"+resultX+" "+resultY);
							break out;
						}
					}
				}
			}
		}
		
		if(!flag) {
			System.out.println(0);
		}
		else {
			System.out.println(sb);
		}
		
	}
	
	private static boolean check(int i, int j, int d) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		int cnt = 1;
		boolean result = false;
		int base = map[i][j];
		queue.offer(new Point(i, j));
		int x = i;
		int y = j;
		for(int k=0;k<5;k++) {
			x += dx[d];
			y += dy[d];
			if(x<=0||x>19||y<=0||y>19) {
				break;
			}
			if(map[x][y] != base) {
				break;
			}
			cnt++;
			queue.offer(new Point(x, y));
		}
	
		if(cnt == 5) { //5개면 반대 방향 확인해서 6개인지 체크해야 함
			if(map[i+dx[d]*-1][j+dy[d]*-1] != map[i][j]){
				result = true;
				Point p = queue.poll();
				resultX = p.x;
				resultY = p.y;
			}
		}
		return result;
		
	}
}
