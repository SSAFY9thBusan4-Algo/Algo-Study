package week6.BOJ_2252_G3_줄세우기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252_G3_줄세우기 {

	public static int N;
	public static int M;

	public static Node[] adjList;
	public static int[] inDegree;

	public static StringBuilder sb = new StringBuilder();

	public static class Node {
		int vertex;
		Node link;

		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 입력이 순서와 방향이 있고 해당 순서대로 차례로 수행해야하므로 위상정렬 사용
		adjList = new Node[N + 1];
		inDegree = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}

		List<Integer> list = topologySort();
		if (list.size() == N) {
			for (Integer vertex : list) {
				sb.append(vertex).append(" ");
			}
		} else {
			sb.append("cycle");
		}

		sb.append("\n");
		System.out.println(sb);
	}

	// 위상정렬
	private static List<Integer> topologySort() {

		List<Integer> orderList = new ArrayList<Integer>();
		Queue<Integer> queue = new ArrayDeque<Integer>();

		// 진입차수가 0인 정점 큐에 넣기
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			orderList.add(cur);

			// 인접정점처리
			for (Node temp = adjList[cur]; temp != null; temp = temp.link) {
				if (--inDegree[temp.vertex] == 0) {
					queue.offer(temp.vertex);
				}
			}
		}

		return orderList;
	}
}
