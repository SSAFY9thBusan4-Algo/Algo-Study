import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int n;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] arr;
	static int[][] dist; // 시작점에서 해당 정점까지 검은 방 변경 최소횟수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new boolean[n][n];
		dist = new int[n][n];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				if(s.charAt(j) == '1') {
					arr[i][j] = true; // 흰방: true, 검은방: false
				} 
				
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		bfs();
		
		System.out.println(dist[n-1][n-1]);
	}

	private static void bfs() {
		Queue<Point> que = new ArrayDeque<>();
		
		que.offer(new Point(0, 0));
		dist[0][0] = 0;
		while(!que.isEmpty()) {
			Point point = que.poll();
			
			for(int i=0; i<4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				
				if(nx>=0 && nx<n && ny>=0 && ny<n && dist[nx][ny] > dist[point.x][point.y]) { // 다음 정점의 검은방 변경횟수가 높은경우만 바꾸어줌 
					if(arr[nx][ny]) dist[nx][ny] = dist[point.x][point.y]; // 흰방일 경우 검은 방 횟수 변경X
					else dist[nx][ny] = dist[point.x][point.y]+1; // 검은방 일 경우 검은방 횟수 +1
					
					que.offer(new Point(nx, ny));
				}
			}
		}
	}
}
