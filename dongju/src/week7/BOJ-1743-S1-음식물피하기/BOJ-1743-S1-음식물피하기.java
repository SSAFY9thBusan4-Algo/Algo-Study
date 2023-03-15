import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n, m, cnt, ans;
	static boolean[][] arr;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new boolean[n][m]; // 음식물 저장 위치
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
		}
		
		for(int i=0; i<n; i++) { // 완전탐색으로 음식물이 있는 곳부터 탐색 시작
			for(int j=0; j<m; j++) {
				if(arr[i][j]) { // 인접한 곳 중 음식물이 있으면 제거하면서 가장 큰 음식물 크기 저장
					cnt = 0;
					dfs(i, j);
					
					ans = Math.max(ans, cnt);
				}
			}
		}
		
		System.out.println(ans);
	}

	private static void dfs(int x, int y) {
		arr[x][y] = false;
		cnt++;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx>=0 && nx<n && ny>=0 && ny<m && arr[nx][ny]) { // 인접 한 곳 중 음식물이 있는 곳
				dfs(nx, ny);
			}
		}
	}
}
