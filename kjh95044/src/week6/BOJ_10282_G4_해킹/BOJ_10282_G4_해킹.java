package week6.BOJ_10282_G4_해킹;

import java.util.*;
import java.io.*;

public class BOJ_10282_G4_해킹 {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Node implements Comparable<Node>{
		int vertex;
		Node link;
		int s;
		public Node(int vertex, int s) {
			super();
			this.vertex = vertex;
			this.s = s;
		}
		public Node(int vertex, Node link, int s) {
			super();
			this.vertex = vertex;
			this.link = link;
			this.s = s;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(s, o.s);
		}
		
	}
	
	static int N;
	static Node[] adjList;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			adjList = new Node[N+1];
			
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			
			for(int i = 0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				adjList[to] = new Node(from, adjList[to], s);
			}

			dijkstra(c);
		}
		System.out.println(sb);
	}
	
	private static void dijkstra(int start) {
		dist = new int[N+1];
		Queue<Node> pq = new PriorityQueue<Node>();

		Arrays.fill(dist, INF);

		// 첫 c까지 걸리는 시간은 0
		dist[start] = 0;
		pq.offer(new Node(start,0));

		Node cur = null;
		
		while (!pq.isEmpty()) {
			cur = pq.poll();
			
			for(Node temp= adjList[cur.vertex]; temp!=null; temp = temp.link) {
				if(dist[temp.vertex]> dist[cur.vertex] + temp.s) {
					dist[temp.vertex] = dist[cur.vertex] + temp.s;
					pq.offer(new Node(temp.vertex, dist[temp.vertex]));
				}
			}
		}
		
        int cnt=0;
        int max=0;
        for (int i = 1; i <= N; i++) {
            if(dist[i] != INF){
                cnt++;
                max = Math.max(max,dist[i]);
            }
        }
        sb.append(cnt).append(" ").append(max).append("\n");
	}

}