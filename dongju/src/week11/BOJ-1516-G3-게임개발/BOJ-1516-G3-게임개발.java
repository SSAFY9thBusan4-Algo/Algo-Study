import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static int[] buildTime, inDegree;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		buildTime = new int[n+1];
		inDegree = new int[n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			buildTime[i] = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				if(from == -1) break;
				
				list[from].add(i);
				inDegree[i]++;
			}
		}
		
		topologySort();
		
		for(int i=1; i<=n; i++) {
			sb.append(buildTime[i]).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void topologySort() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		
		for(int i=1; i<=n; i++) {
			if(inDegree[i] == 0) pq.offer(new int[] {i, buildTime[i]});
		}
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			for(int i: list[cur[0]]) {
				if(--inDegree[i] == 0) {
					buildTime[i] += buildTime[cur[0]];
					pq.add(new int[] {i, buildTime[i]});
				}
			}
		}
	}
}
