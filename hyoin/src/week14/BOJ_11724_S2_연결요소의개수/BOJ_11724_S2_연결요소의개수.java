package week14.BOJ_11724_S2_연결요소의개수;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_11724_S2_연결요소의개수 {

	private static int N;
	private static int M;
	private static Node[] adjList;
	private static boolean[] isVisited;

	public static class Node {
		int vertex;
		Node link;

		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new Node[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// 양방향 인접리스트
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}

		int result = 0;
		isVisited = new boolean[N + 1]; 
		for (int i = 1; i <= N; i++) {
			if (!isVisited[i]) { // 해당 정점을 방문한 적 없을 때 
				bfs(i);
				result++;
			}
		}

		System.out.println(result);
	}

	private static void bfs(int node) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(node);
		isVisited[node] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (Node temp = adjList[cur]; temp != null; temp = temp.link) { // 인접리스트 탐색
				if (!isVisited[temp.vertex]) {
					isVisited[temp.vertex] = true;
					queue.offer(temp.vertex);
				}
			}
		}
	}
}
