import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int n, m, ans;
	static int[] dx = {-1, 0, 1, 0, -1};
	static int[] dy = {0, 1, 0, -1, 0};
	static int[][] arr;
	static boolean[][] v;

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
		
		v = new boolean[n][m];
		
		dfs(0, 0, 0);
		
		System.out.println(ans);
	}

	private static void dfs(int x, int y, int sum) {
		if(x==n && y==0) {
			ans = Math.max(ans, sum);
			return;
		}
		
		if(!v[x][y]) {
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				
				int mx = x + dx[i+1];
				int my = y + dy[i+1];
				if(mx<0 || mx>=n || my<0 || my>=m) continue;
				
				if(v[nx][ny] || v[mx][my]) continue;
				
				v[x][y] = true;
				v[nx][ny] = true;
				v[mx][my] = true;
				
				if(y < m-1) dfs(x, y+1, sum + (arr[x][y]*2) + arr[nx][ny] + arr[mx][my]);
				else dfs(x+1, 0, sum + (arr[x][y]*2) + arr[nx][ny] + arr[mx][my]);
				
				v[x][y] = false;
				v[nx][ny] = false;
				v[mx][my] = false;
			}
		}
		
		if(y < m-1) dfs(x, y+1, sum);
		else dfs(x+1, 0, sum);
	}	
}
