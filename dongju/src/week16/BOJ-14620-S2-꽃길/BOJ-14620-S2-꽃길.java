import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int n, ans, nums[], arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		nums = new int[3];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		combination(0, 0);
		
		System.out.println(ans);
	}

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	private static void combination(int cnt, int start) {
		if(cnt == 3) {
			boolean[][] v = new boolean[n][n];
			
			int cost = 0;
			for(int num: nums) {
				int x = num / (n-2) + 1;
				int y = (num % (n-2)) + 1;
				
				if(v[x][y]) return;
				
				v[x][y] = true;
				cost += arr[x][y];
				for(int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(v[nx][ny]) return;
					
					v[nx][ny] = true;
					cost += arr[nx][ny];
				}
			}
			
			ans = Math.min(ans, cost);
			return;
		}
		
		for(int i=start; i<(n-2)*(n-2); i++) {
			nums[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
}
