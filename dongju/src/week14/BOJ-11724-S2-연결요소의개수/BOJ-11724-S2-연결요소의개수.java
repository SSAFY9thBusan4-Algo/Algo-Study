import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n, cnt;
	static boolean v[], arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new boolean[n][n];
		v = new boolean[n];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			arr[x][y] = true;
			arr[y][x] = true;
		}
		
		for(int i=0; i<n; i++) {
			if(!v[i]) {
				dfs(i);
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	private static void dfs(int cur) {
		if(v[cur]) return;
		
		v[cur] = true;
		for(int i=0; i<n; i++) {
			if(arr[cur][i]) dfs(i);
		}
	}
}
