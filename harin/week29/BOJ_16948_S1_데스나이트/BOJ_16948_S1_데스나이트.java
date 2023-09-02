package boj;

import java.io.*;
import java.util.*;

public class BOJ_16948_S1_데스나이트 {
	
	static class Pos {
		int r;
		int c;
		int moveCnt;
		
		public Pos(int r, int c, int moveCnt) {
			this.r = r;
			this.c = c;
			this.moveCnt = moveCnt;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "r : " + r + " c : " + c + " moveCnt : " + moveCnt;
		}
	}
	
	static int N;
	static int map[][];
	static boolean isVisited[][];
	
	static int dr[] = {-2, -2, 0, 0, 2, 2};
	static int dc[] = {-1, 1, -2, 2, -1, 1};
	
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		isVisited = new boolean[N][N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		Pos startPos = new Pos(r1, c1, 0);
		Pos endPos = new Pos(r2, c2, 0);
		
		BFS(startPos, endPos);
		
		System.out.println(ans);
	}
	
	static public void BFS(Pos startPos, Pos endPos) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(startPos);
		isVisited[startPos.r][startPos.c] = true;
		boolean isFind = false;
		
		while(!queue.isEmpty()) {
			Pos curPos = queue.poll();
			
			if(curPos.r == endPos.r && curPos.c == endPos.c) {
				isFind = true;
				ans = ans < curPos.moveCnt ? ans : curPos.moveCnt;
			}
			
			for(int i=0; i<6; i++) {
				int nr = curPos.r + dr[i];
				int nc = curPos.c + dc[i];
				
				if(0 <= nr && nr < N && 0 <= nc && nc < N) {
					if(!isVisited[nr][nc]) {
						queue.offer(new Pos(nr, nc, curPos.moveCnt + 1));
						isVisited[nr][nc] = true;
					}
				}
			}
		}
		
		if(!isFind) ans = -1;
	}

}
