import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, arr[][], d[][];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		d = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				ans = Math.max(ans, dfs(i, j));
			}
		}
		
		System.out.println(ans);
	}
	
	private static int dfs(int x, int y) {
		if(d[x][y] != 0) return d[x][y];
		
		d[x][y] = 1;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(arr[nx][ny] <= arr[x][y]) continue;
			
			d[x][y] = Math.max(d[x][y], dfs(nx, ny)+1);
		}
		
		return d[x][y];
	}
}
