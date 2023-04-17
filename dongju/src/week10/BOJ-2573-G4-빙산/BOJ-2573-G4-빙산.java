import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static int n, m;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		while(true) {
			ans++;
			int flag = bfs();
			
			if(flag == 0) continue;
			else if(flag == 1) break;
			else {
				System.out.println(0);
				return;
			}
		}
		
		System.out.println(ans);
	}

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	private static int bfs() {
		int[][] temp = new int[n][m];
		for(int i=1; i<n-1; i++) {
			for(int j=1; j<m-1; j++) {
				temp[i][j] = arr[i][j];
			}
		}
		
		for(int i=1; i<n-1; i++) {
			for(int j=1; j<m-1; j++) {
				if(arr[i][j] > 0) {
					int cnt = 0;
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if(arr[nx][ny] <= 0) cnt++;
					}
					
					temp[i][j] -= cnt;
				}
			}
		}
		
		for(int i=1; i<n-1; i++) {
			for(int j=1; j<m-1; j++) {
				arr[i][j] = temp[i][j];
			}
		}
		
		Queue<int[]> que = new ArrayDeque<>();
		int cnt = 0;
		for(int i=1; i<n-1; i++) {
			for(int j=1; j<m-1; j++) {
				if(temp[i][j] > 0) {
					cnt++;
					que.offer(new int[] {i, j});
					temp[i][j] = 0;
					
					while(!que.isEmpty()) {
						int[] cur = que.poll();
						
						for(int k=0; k<4; k++) {
							int nx = cur[0] + dx[k];
							int ny = cur[1] + dy[k];
							
							if(temp[nx][ny] > 0) {
								temp[nx][ny] = 0;
								que.offer(new int[] {nx, ny});
							}
						}
					}
				}
			}
		}
		
		if(cnt >= 2) return 1;
		if(cnt == 0) return -1;
		else return 0;
	}
}
