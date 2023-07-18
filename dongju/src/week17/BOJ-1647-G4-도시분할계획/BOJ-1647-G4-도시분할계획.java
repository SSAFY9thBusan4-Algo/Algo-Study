import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int no;
		int weight;
		
		public Node(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	private static StringBuilder sb = new StringBuilder();
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, weight));
			list[to].add(new Node(from, weight));
		}
		
		boolean[] v = new boolean[n+1];
		int ans = 0;
		int max = 0;
		
		// PRIM
		Queue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node minNode = pq.poll(); // 방문하지 않은 정점 중 가장 짧은 정점
			
			if(v[minNode.no]) continue;
			
			v[minNode.no] = true;
			ans += minNode.weight;
			max = Math.max(max, minNode.weight);
			
			for(Node node: list[minNode.no]) {
				if(!v[node.no]) pq.offer(node);
			}
		}
		
		System.out.println(ans - max);
	}
}
