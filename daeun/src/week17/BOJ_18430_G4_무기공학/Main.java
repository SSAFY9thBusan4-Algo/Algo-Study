import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, M, map[][], result;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		map = new int[N][M];
		visit = new boolean[N][M];
		result = 0;
		for(int i=0;i<N;i++) {
			split = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		dfs(0, 0);
		System.out.println(result);
	}
	private static void dfs(int cnt, int sum) {
		int i = cnt/M;
		int j = cnt%M;
		if(cnt == N*M) {
			result = Math.max(sum, result);
			return;
		}
		if(!visit[i][j]) {
      visit[i][j] = true;
			//아래, 왼
			if(i+1 < N && j-1 >= 0 && !visit[i+1][j] && !visit[i][j-1]) {
				visit[i+1][j] = true;
				visit[i][j-1] = true;
				dfs(cnt+1, sum+map[i][j]*2 + map[i+1][j] + map[i][j-1]);
				visit[i+1][j] = false;
				visit[i][j-1] = false;
			}
			//위, 왼
			if(i-1 >= 0 && j-1 >= 0 && !visit[i-1][j] && !visit[i][j-1]) {
				visit[i-1][j] = true;
				visit[i][j-1] = true;
				dfs(cnt+1, sum+map[i][j]*2 + map[i-1][j] + map[i][j-1]);
				visit[i-1][j] = false;
				visit[i][j-1] = false;
			}
			//위, 오
			if(i-1 >= 0 && j+1 < M && !visit[i-1][j] && !visit[i][j+1]) {
				visit[i-1][j] = true;
				visit[i][j+1] = true;
				dfs(cnt+1, sum+map[i][j]*2 + map[i-1][j] + map[i][j+1]);
				visit[i-1][j] = false;
				visit[i][j+1] = false;
			}
			//아래, 오
			if(i+1 < N && j+1 < M && !visit[i+1][j] && !visit[i][j+1]) {
				visit[i+1][j] = true;
				visit[i][j+1] = true;
				dfs(cnt+1, sum+map[i][j]*2 + map[i+1][j] + map[i][j+1]);
				visit[i+1][j] = false;
				visit[i][j+1] = false;
			}
      visit[i][j] = false;
		}
		dfs(cnt+1, sum); //부메랑 안 만들고 넘어가는 경우
	}
}
