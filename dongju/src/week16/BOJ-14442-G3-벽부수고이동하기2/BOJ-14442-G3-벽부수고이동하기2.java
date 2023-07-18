import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point {
		int x, y, d, cnt;

		public Point(int x, int y, int d, int cnt) {
			this.x = x;
			this.y = y;
			this.d = d; // 이동 거리
			this.cnt = cnt; // 부순 횟수
		}
	}

	static StringBuilder sb = new StringBuilder();
	static int n, m, k, arr[][];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Point> que = new ArrayDeque<>();
		boolean[][][] v = new boolean[k+1][n][m]; 
		
		que.offer(new Point(0, 0, 1, 0));
		v[0][0][0] = true;
		while(!que.isEmpty()) {
			Point point = que.poll();
			
			if(point.x==n-1 && point.y==m-1) return point.d;
			
			for(int i=0; i<4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				int breakCnt = point.cnt;
				
				if(nx>=0 && nx<n && ny>=0 && ny<m) {
					if(arr[nx][ny] == 0) {
						if(!v[breakCnt][nx][ny]) {
							que.offer(new Point(nx, ny, point.d+1, breakCnt));
							v[breakCnt][nx][ny] = true;
						}					
					}
					else { // 벽
						if(breakCnt<k && !v[breakCnt+1][nx][ny]) { // 부수고 이동
							que.offer(new Point(nx, ny, point.d+1, breakCnt+1));
							v[breakCnt+1][nx][ny] = true;
						}	
					}
				}
			}
		}
		
		return -1;
	}
}
