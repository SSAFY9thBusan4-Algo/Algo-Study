import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int no, dist;

		public Node(int no, int dist) {
			super();
			this.no = no;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	private static StringBuilder sb = new StringBuilder();
	static int n, d;
	static int INF = Integer.MAX_VALUE;
	static ArrayList<Node>[] list;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken())-1;
			
			list = new ArrayList[n];
			for(int i=0; i<n; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken())-1;
				int from = Integer.parseInt(st.nextToken())-1;
				
				list[from].add(new Node(to, Integer.parseInt(st.nextToken())));
			}
			
			dijkstra(c);
			
			int cnt = 0;
			int ans = -1;
			for(int i=0; i<n; i++) {
				if(dist[i] != INF) {
					cnt++;
					ans = Math.max(ans, dist[i]);
				}
			}
			
			sb.append(cnt + " " + ans).append("\n");
		}

		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		dist = new int[n]; // 시작 정점에서 해당 정점까지 거리
		Arrays.fill(dist, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dist[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			
			if(dist[temp.no] < temp.dist) continue;
			
      // 연결된 정점 중에서
			for(Node node: list[temp.no]) {
				int cost = dist[temp.no] + node.dist;
				
        // 제일 빨리 해킹되는 시간으로 큐에 추가
				if(cost < dist[node.no]) {
					dist[node.no] = cost;
					pq.offer(new Node(node.no, cost));
				}
			}
		}
	}
}
