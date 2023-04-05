import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] list;
	static int n, k, w, ans;
	static int[] buildTime, inDegree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n+1];
			for(int i=1; i<=n; i++) {
				list[i] = new ArrayList<>();
			}
			
			buildTime = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}
			
			inDegree = new int[n+1];
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				list[from].add(to);
				inDegree[to]++;
			}
			
			w = Integer.parseInt(br.readLine());
			
			topologySort();
			
			sb.append(buildTime[w]).append("\n");
		}

		System.out.println(sb);
	}

	private static void topologySort() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		for(int i=1; i<=n; i++) {
			if(inDegree[i] == 0) pq.offer(new int[] {i, buildTime[i]});
		}
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();

			for (int i : list[cur[0]]) {
				if (--inDegree[i] == 0) {
					buildTime[i] += buildTime[cur[0]];
					pq.offer(new int[] {i, buildTime[i]});
				}
			}
		}
	}
}
