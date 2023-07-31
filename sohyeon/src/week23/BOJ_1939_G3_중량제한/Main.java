package week23.BOJ_1939_G3_중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	
	static class Node implements Comparable<Node>{
		int vertex;
		int cost;
		Node link;
		int minw;
		public Node(int vertex, int cost, Node link) {
			super();
			this.vertex = vertex;
			this.cost = cost;
			this.link = link;
		}
		public Node(int vertex, int minw) {
			super();
			this.vertex = vertex;
			this.minw = minw;
		}
		@Override
		public int compareTo(Node o) {			
			return o.minw-this.minw;
		}			
	}
	
	static int N,M;
	static Node[] adjList;

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		adjList = new Node[N+1];
		int a,b,c;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjList[a] = new Node(b,c,adjList[a]);
			adjList[b] = new Node(a,c,adjList[b]);
		}	
		
		st = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		for (Node n=adjList[start]; n!=null; n=n.link) {
			queue.offer(new Node(n.vertex, n.cost));
		}
		boolean[] visited = new boolean[N+1];
		Node poll;
		int result = 0;
		while(!queue.isEmpty()) {
			
			poll = queue.poll();
			
			if (visited[poll.vertex]) continue;
			visited[poll.vertex] = true;
			
			if (poll.minw<result) continue;
			if (poll.vertex==end) {
				result = poll.minw;
				continue;
			}
			
			for (Node n=adjList[poll.vertex]; n!=null; n=n.link) {
				queue.offer(new Node(n.vertex, Math.min(poll.minw, n.cost)));
			}
			
		}
		
		System.out.println(result);
		
	}
	
}

