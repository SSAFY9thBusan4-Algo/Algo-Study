import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] list;
	static int n, room[];
	static boolean flag, v[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			
			room = new int[n+1];
			list = new ArrayList[n+1];
			v = new boolean[n+1];
			for(int i=0; i<=n; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=1; i<=n; i++) {
				st = new StringTokenizer(br.readLine());
				
				String s = st.nextToken();
				int m = Integer.parseInt(st.nextToken());
				switch (s) {
				case "L": // 레프리콘
					room[i] = m;
					break;
				case "T": // 트롤
					room[i] = -m;
					break;
				}
				
				while(true) {
					int to = Integer.parseInt(st.nextToken());
					if(to == 0) break;
					
					list[i].add(to);
				}
            }

			flag = false;
			v[1] = true;
			dfs(1, 0);
			
			if(flag) sb.append("Yes");
			else sb.append("No");
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int cur, int money) {
		if(room[cur] > 0) { // 래프리콘
			if(money < room[cur]) money = room[cur];
		}
		else if(room[cur] < 0) { // 트롤
			money += room[cur];
			if(money < 0) {
				v[cur] = false;
				return;
			}
		}
		
		if(cur == n) {
			flag = true;
			return;
		}
		
		for(int i: list[cur]) {
			if(!v[i]) {
				v[i] = true;
				dfs(i, money);
				v[i] = false;
			}
		}
	}
}
