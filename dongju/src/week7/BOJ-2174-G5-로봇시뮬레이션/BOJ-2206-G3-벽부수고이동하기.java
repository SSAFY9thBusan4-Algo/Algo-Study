import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point {
		int x, y;
		int d;
		int breakCnt;

		public Point(int x, int y, int d, int breakCnt) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.breakCnt = breakCnt;
		}
	}
	
	static int n, m;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] arr;
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new boolean[n][m];
		dist = new int[n][m];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				if(s.charAt(j) == '0') arr[i][j] = true;
				
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Point> que = new ArrayDeque<>();
		
		que.offer(new Point(0, 0, 1, 0));
		dist[0][0] = 0;
		while(!que.isEmpty()) {
			Point point = que.poll();
			
			if(point.x==n-1 && point.y ==m-1) {
				return point.d;
			}
			
			for(int i=0; i<4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				
				if(nx>=0 && nx<n && ny>=0 && ny<m && dist[nx][ny] > point.breakCnt) { // 미방문 정점중 breakCnt가 더 높은 곳들
					if(arr[nx][ny]) { // 벽이 아닐 때
						dist[nx][ny] = point.breakCnt;
						que.offer(new Point(nx, ny, point.d+1, point.breakCnt));
					}
					else { // 벽일 때
						if(point.breakCnt == 0) { // 아직 한번도 벽을 부순적이 없다면
							dist[nx][ny] = point.breakCnt+1;
							que.offer(new Point(nx, ny, point.d+1, point.breakCnt+1));
						}
					}
				}
			}
		}
		
		return -1;
	}
}
