import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		for(int i=1; i<n+1; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int knowTrueCnt = Integer.parseInt(st.nextToken());
		while(knowTrueCnt-- > 0) {
			parent[Integer.parseInt(st.nextToken())] = 0;
		}
		
		int[][] party = new int[m][];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int comePeople = Integer.parseInt(st.nextToken());
			
			party[i] = new int[comePeople];
			for(int j=0; j<comePeople; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j=0; j<comePeople-1; j++) {
				union(party[i][j], party[i][j+1]);
			}
		}
		
		int ans = m;
		for(int i=0; i<m; i++) {
			for(int j: party[i]) {
                if(find(j) == 0) {
                    ans--;
                    break;
                }
            }
		}
		
		System.out.println(ans);
	}
	
	static int find(int x) {
		if(x == parent[x]) return x;
		
		return find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x > y) parent[x] = y;
		if(x < y) parent[y] = x;
 	}
}
