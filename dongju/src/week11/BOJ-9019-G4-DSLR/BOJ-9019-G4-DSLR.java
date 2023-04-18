import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static StringBuilder sb = new StringBuilder();
	static int a, b;
	static String[] ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			bfs(a);
			
			sb.append(ans[b]).append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs(int start) {
		boolean[] v = new boolean[10000];
		ans = new String[10000];
		Arrays.fill(ans, "");
		
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(start);
		v[start] = true;
		while(!que.isEmpty()) {
			int temp = que.poll();
			
			if(temp == b) return;
			
			int D = (temp * 2) % 10000;
			int S = (temp == 0)? 9999: temp-1;
			int L = (temp%1000) * 10 + (temp/1000);
			int R = (temp/10) + (temp%10) * 1000;
			
			if(!v[D]) {
				que.offer(D);
				v[D] = true;
				ans[D] = ans[temp] + "D";
			}
			
			if(!v[S]) {
				que.offer(S);
				v[S] = true;
				ans[S] = ans[temp] + "S";
			}

			if(!v[L]) {
				que.offer(L);
				v[L] = true;
				ans[L] = ans[temp] + "L";
			}

			if(!v[R]) {
				que.offer(R);
				v[R] = true;
				ans[R] = ans[temp] + "R";
			}
		}
	}
}
