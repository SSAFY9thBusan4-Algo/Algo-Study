import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n, m, ans, arr[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 벽장 개수
		
		st = new StringTokenizer(br.readLine()); // 열린 문
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		m = Integer.parseInt(br.readLine()); // 사용할 벽장
		arr = new int[m];
		for(int i=0; i<m; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
		}
		
		ans = Integer.MAX_VALUE;
		
		dfs(a, b, 0, 0);
		
		System.out.println(ans);
	}

	private static void dfs(int a, int b, int openCnt, int cnt) {
		if(openCnt == m) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		int tempA = Math.abs(a - arr[openCnt]);
		int tempB = Math.abs(b - arr[openCnt]);
		
		dfs(arr[openCnt], b, openCnt+1, cnt+tempA);
		dfs(a, arr[openCnt], openCnt+1, cnt+tempB);
	}
}
