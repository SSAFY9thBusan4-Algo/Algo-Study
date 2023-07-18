import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static boolean ans, v[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		v = new boolean[1500][1500];
		
		if((a+b+c) % 3 == 0) {
			dfs(a, b, c);
			dfs(a, c, b);
			dfs(c, a, b);			
		}
		
		if(ans) System.out.println(1);
		else System.out.println(0);
	}

	private static void dfs(int a, int b, int c) {
		if(v[a][b]) return;
		v[a][b] = true;
		
		if(a==b && b==c) {
			ans = true;
			return; 
		}
		
		if(a > b) {
			dfs(a-b, b+b, c);
			dfs(a-b, c, b+b);
			dfs(b+b, c, a-b);
		}
		else if(a < b) {
			dfs(a+a, b-a, c);
			dfs(a+a, c, b-a);
			dfs(b-a, c, a+a);
		}
	}
}
