package week8.BOJ_6593_G5_상범빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static class Point{
		int x;
		int y;
		int floor;
		public Point(int x, int y, int floor) {
			super();
			this.x = x;
			this.y = y;
			this.floor = floor;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] split;
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		while(true) {
			split = br.readLine().split(" ");
			int L = Integer.parseInt(split[0]); //층
			int R = Integer.parseInt(split[1]); //행
			int C = Integer.parseInt(split[2]); //열
			if(L==0 && R==0 && C==0) { //0 0 0이면 종료
				break;
			}
			int startX = 0, startY = 0, startF = 0;
			int endX = 0, endY = 0, endF = 0;
			List<boolean[][]> list = new ArrayList<>();
			for(int l=0; l<L;l++) {
				boolean[][] map = new boolean[R][C];
				for(int r=0;r<R;r++) {
					String str = br.readLine();
					for(int c=0;c<C;c++) {
						if(str.charAt(c)=='.') {
							map[r][c] = true;
						}
						else {
							if(str.charAt(c) == 'S') {
								startX = r;
								startY = c;
								startF = l;
								map[r][c] = false;
							}
							else if(str.charAt(c) == 'E') {
								endX = r;
								endY = c;
								endF = l;
								map[r][c] = true; //가야하니까 true
							}
							else{
								map[r][c] = false;
							}
						}
						
					}
				}
				list.add(map); //3차원으로 만들기 위해  map을 list에 넣음
				br.readLine();
			}
			
			Queue<Point> queue = new ArrayDeque<>();
			queue.offer(new Point(startX, startY, startF)); //큐에 넣고
			list.get(startF)[startX][startY] = false; //방문 처리
			
			int tempX=0, tempY=0, result = 0;
			boolean flag = false;
			out:
			while(!queue.isEmpty()) {
				int size = queue.size();
				result++;
				while(size>0) {
					Point now = queue.poll();
					int nowX = now.x;
					int nowY = now.y;
					int nowF = now.floor;
					if(nowX==endX && nowY==endY && nowF==endF) {
						flag = true;
						break out;
					}
					for(int d=0;d<4;d++) { //동서남북
						tempX = nowX+dx[d];
						tempY = nowY+dy[d];
						if(tempX<0 || tempX>=R || tempY<0 || tempY>=C ||!list.get(nowF)[tempX][tempY]) {
							continue;
						}
						queue.offer(new Point(tempX, tempY, nowF));
						list.get(nowF)[tempX][tempY] = false;
					}

					//상
					if(nowF+1<L && list.get(nowF+1)[nowX][nowY]) {
						if(list.get(nowF+1)[nowX][nowY]){
							queue.offer(new Point(nowX, nowY, nowF+1));
							list.get(nowF+1)[nowX][nowY] = false;
						}
					}

					//하
					if(nowF-1>=0 && list.get(nowF-1)[nowX][nowY]) {
						if(list.get(nowF-1)[nowX][nowY]){
							queue.offer(new Point(nowX, nowY, nowF-1));
							list.get(nowF-1)[nowX][nowY] = false;
						}
					}
					size--;
				}
			}
			if(!flag) {
				sb.append("Trapped!").append("\n");
			}
			else {
				sb.append("Escaped in ");
				sb.append(result-1);
				sb.append(" minute(s).").append("\n");
			}
		}
		System.out.println(sb);

	}
}
