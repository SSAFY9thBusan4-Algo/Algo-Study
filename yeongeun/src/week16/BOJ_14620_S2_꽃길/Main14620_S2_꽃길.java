package src.week16.BOJ_14620_S2_꽃길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main14620_S2_꽃길 {
	
	static class Flower{
		int sum;
		int px, py;
		public Flower(int sum, int px, int py) {
			this.sum = sum;
			this.px = px;
			this.py = py;
		}
		@Override
		public String toString() {
			return "[sum=" + sum + ", px=" + px + ", py=" + py + "]";
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		
		for(int i = 0 ; i < n ; i++) {
			String[] line =  br.readLine().split(" ");
			for(int j = 0 ; j < n ; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		// get sum
		list = new Flower[(n-2)*(n-2)];
		int cnt = -1;
		for(int i = 1; i < n-1 ; i++) {
			for(int j = 1;  j < n-1 ; j++) {
				int s = map[i-1][j] + map[i][j-1] + map[i][j] + map[i][j+1] + map[i+1][j];
				list[++cnt] = new Flower(s, i, j);
			}
		}
		
		// select
		Arrays.sort(list, (o1, o2) -> o1.sum - o2.sum);
		min = Integer.MAX_VALUE;
		maxidx = (n-2)*(n-2);
		
		select(0,0,0, new boolean[n][n]);
		System.out.println(min);
	}
	
	private static Flower[] list;
	private static int min;
	private static int maxidx;
	
	private static void select(int idx, int cnt, int sum, boolean[][] visited /*Flower[] selected*/) {

		if(cnt == 3) {
			min = sum < min ? sum : min;
			return;
		}
		if(idx == maxidx || sum > min)return;	//백트래킹
		
		
		for(int i = idx ; i < maxidx ; i++) {
			
			if(!check(list[i].px, list[i].py, visited)) {
				visit(list[i].px, list[i].py, visited);
				select(i+1, cnt+1, sum+list[i].sum, visited);
				unvisit(list[i].px, list[i].py, visited);
			}

			// 지금 넣으려고 하는것 중에 죽는 꽃이 있는가.
//			boolean flag = false;
//			for(int s = 0 ; s < cnt ; s++) {
//				if(isOver(selected[s], list[i])) flag = true;
//			}

			// 없으면  넣기. 있으면 다음 인덱스 확인.
//			if(!flag) {
//				selected[cnt] = list[i];
//				select(i+1, cnt+1, sum+list[i].sum, selected);
//			}
		}
		
	}
	
	private static boolean check(int i , int j, boolean[][] visited) {
		if(visited[i-1][j] | visited[i][j] | visited[i][j-1] | visited[i][j+1] | visited[i+1][j]) return true;
		return false;
	}
	
	private static void visit(int i, int j, boolean[][] visited) {
		visited[i-1][j] = visited[i][j] = visited[i][j-1] = visited[i][j+1] = visited[i+1][j] = true;
	}
	

	private static void unvisit(int i, int j, boolean[][] visited) {
		visited[i-1][j] = visited[i][j] = visited[i][j-1] = visited[i][j+1] = visited[i+1][j] = false;
	}
	
	// 이렇게 체크하려고 했는데 왜 안됨??
//	private static boolean isOver(Flower a, Flower b) {
//		if((Math.abs(a.px - b.py) + Math.abs(a.py - b.py)) <= 2) return true;
//		return false;
//	}
}
