package week6.BOJ_2252_G3_줄세우기;

import java.util.*;
import java.io.*;

public class BOJ_2252_G3_줄세우기 {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}

	static int N,M;
	static Node[] adjList;
	static int[] inDegree; // 진입차수

	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		

		adjList = new Node[N + 1];
		inDegree = new int[N + 1];
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}

		ArrayList<Integer> list = topologySort();
		if (list.size() == N) {
			for (Integer vertex : list) {
				sb.append(vertex + " ");
			}
			sb.append("\n");
		} 
		System.out.println(sb);
	}

	static ArrayList<Integer> topologySort() {

		ArrayList<Integer> orderList = new ArrayList<Integer>();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);
		} // 진입차수가 0인 정점 큐에 넣기
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			orderList.add(cur);

			// 현재 정점 기준으로 인접정점 처리
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if (--inDegree[temp.vertex] == 0) {
					queue.offer(temp.vertex);
				}
			}

		}
		return orderList;
	}

}