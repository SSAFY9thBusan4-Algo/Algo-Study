import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n, m;
	static int[][] arr;
	static boolean[][] visit;
	static int cheeseCnt = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for(int i=0; i<n; i++) {
			st  = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				int temp = Integer.parseInt(st.nextToken());
				arr[i][j] = temp;
				
				if(temp == 1) cheeseCnt++;
			}
		}
		
		int time = 0;
		while(cheeseCnt > 0) {
			visit = new boolean[n][m];
			dfs(0, 0);
			
			time++;
			meltCheese();
		}
		
		System.out.println(time);
	}

	private static void meltCheese() {
		boolean[][] temp = new boolean[n][m];
		
		for(int i=1; i<n-1; i++) {
			for(int j=1; j<m-1; j++) {
				int cnt = 0;
				
				if(arr[i][j] == 1) {
					for(int idx=0; idx<4; idx++) {
						int nx = i + dx[idx];
						int ny = j + dy[idx];

						if(arr[nx][ny] == 2) {
							cnt++;
						}
					}
				}
				
				if(cnt >= 2) {
					temp[i][j] = true;
				}
			}
		}
		
		for(int i=1; i<n-1; i++) {
			for(int j=1; j<m-1; j++) {
				if(temp[i][j]) {
					arr[i][j] = 0;
					cheeseCnt--;
				}
			}
		}
	}

	private static void dfs(int x, int y) {
		visit[x][y] = true;
		arr[x][y] = 2; // 외부공기: 2
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
			if (visit[nx][ny] || arr[nx][ny] == 1) continue;

			dfs(nx, ny);
		}
	}
}
