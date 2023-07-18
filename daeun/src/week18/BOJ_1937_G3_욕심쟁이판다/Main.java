import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, map[][], result, dp[][];
	static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N]; //최대 이동 칸 저장
		for(int i=0;i<N;i++) {
			String[] split = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				result = Math.max(result, dfs(i, j));
			}
		}
		
		System.out.println(result);
	}
	
	private static int dfs(int i, int j) {
		if(dp[i][j]!=0) {
			return dp[i][j];
		}
		
		dp[i][j] = 1; //첫 방문 시
		
		for(int d=0;d<4;d++) {
			int x = i + dx[d];
			int y = j + dy[d];
			//더 큰 곳만 가니까 굳이 방문 처리할 필요 없음
			if(x>=0 && x<N && y>=0 && y<N && map[x][y]>map[i][j]) {
				dp[i][j] = Math.max(dp[i][j], dfs(x, y)+1);
			}
		}
		return dp[i][j];
	}
}
