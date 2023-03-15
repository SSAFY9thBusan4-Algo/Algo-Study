package week6.BOJ_9205_G5_맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	static class Store{
		int x;
		int y;
		public Store(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Store [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	private static int n;
	private static Store[] stores;
	private static boolean[] visited;
	private static int[] end;
	private static boolean result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			result = false;
			n = Integer.parseInt(in.readLine());
			
			int[] start = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
			
			// 편의점 배열에 저장
			stores = new Store[n];
			for (int i=0; i<n; i++) {
				int[] xy = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
				stores[i] = new Store(xy[0], xy[1]);
			}
			//for (Store s : stores) System.out.println(s);
			
			end = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
			
			visited = new boolean[n];
			bfs(start[0], start[1]);
			
			if (result) System.out.println("happy");
			else System.out.println("sad");
			
		}
		
	}
	
	private static void bfs(int sx, int sy) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		queue.offer(new int[] {sx, sy, n});
		
		while (!queue.isEmpty()) {
			int[] xyc = queue.poll();
			int x = xyc[0];
			int y = xyc[1];
			int cnt = xyc[2];
			
			// 페스티벌까지의 거리
			int d = Math.abs(end[0]-x) + Math.abs(end[1]-y);
			if (d <= 1000) {
				result = true;
				continue;
			}
			
			// 남은 편의점에서 맥주를 최대한 많이 사도 페스티벌까지 마시며 갈 수 없는 거리이면
			// 편의점을 다 들렸는데 페스티벌까지 거리가 맥주 한통을 다 마셔도 도착하지 못할 거리라면
			if (d > 1000 * (cnt+1) || cnt == 0) {
				continue;
			}
			
			for (int i=0; i<n; i++) {
				if (!visited[i]) {
					// 편의점까지 맥주가 떨어지지 않고 갈 수 있는지 확인
					int dist = Math.abs(stores[i].x-x) + Math.abs(stores[i].y-y);
					// (dist/50 <= 20) 이렇게 했다가 틀렸다.. (int)(dist/50) <= 20 
					if (dist <= 1000) {
						visited[i] = true;
						queue.offer(new int[] {stores[i].x, stores[i].y,cnt-1});						
					}
				}
			}
		}
	}
	 
}
