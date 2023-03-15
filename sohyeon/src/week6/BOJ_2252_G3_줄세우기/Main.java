package week6.BOJ_2252_G3_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static class Node {
		public int vertex;
		public Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}			
	}
	
	private static int N, M;
	private static Node[] adjList;
	private static int[] inDegree;

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N + 1];
		inDegree = new int[N + 1];
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}
		
		List<Integer> list = topologySort();
		for (Integer vertex : list) {
			System.out.print(vertex + " ");
		}
		
	}
	
	private static List<Integer> topologySort() {
		List<Integer> orderList = new ArrayList<>();
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if (inDegree[i]==0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			Integer cur = queue.poll();
			orderList.add(cur);
			
			for (Node temp = adjList[cur]; temp != null; temp = temp.link) {
				if (--inDegree[temp.vertex]==0) {
					queue.offer(temp.vertex);
				}
			}
		}
		return orderList;
	}
	
}
