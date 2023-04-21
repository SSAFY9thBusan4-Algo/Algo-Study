import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point implements Comparable<Point>{
		int x;
		int cnt;
		
		public Point(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return cnt - o.cnt;
		}
	}

	private static StringBuilder sb = new StringBuilder();
	static int n, k;
	static boolean[] v = new boolean[2000001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(n));
	}

	private static int bfs(int start) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		pq.offer(new Point(start, 0));
		while(!pq.isEmpty()) {
			Point point = pq.poll();
			
			v[point.x] = true;
			if(point.x == k) return point.cnt;
			
			if(point.x*2 <= 100000 && !v[point.x*2]) {
				pq.offer(new Point(point.x*2, point.cnt));
			}
			if(point.x+1 <= 100000 && !v[point.x+1]) {
				pq.offer(new Point(point.x+1, point.cnt+1));
			}
			if(point.x-1 >= 0 && !v[point.x-1] ) {
				pq.offer(new Point(point.x-1, point.cnt+1));
			}
		}
		
		return -1;
	}
}
