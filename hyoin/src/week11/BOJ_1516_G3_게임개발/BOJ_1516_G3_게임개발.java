package week11.BOJ_1516_G3_게임개발;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1516_G3_게임개발 {

	public static int N;
	public static int[] time;
	public static int[] dp;

	public static Node[] adjList;
	public static int[] inDegree;

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
		time = new int[N + 1]; // 각 건물을 짓는데 걸리는 시간
		dp = new int[N + 1]; // 해당 건물을 짓는데 걸리는 최소의 시간
		adjList = new Node[N + 1];
		inDegree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int to = i;
			time[i] = Integer.parseInt(st.nextToken());

			while (true) {
				int from = Integer.parseInt(st.nextToken());
				if (from == -1) {
					break;
				}
				adjList[from] = new Node(to, adjList[from]);
				inDegree[to]++;
			}
		}

		topologySort();
		for (int i = 1; i <= N; i++) {
			System.out.println(dp[i]);
		}
	}

	private static void topologySort() {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
				dp[i] = time[i];
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// cur -> temp
			for (Node temp = adjList[cur]; temp != null; temp = temp.link) {
				// temp번 건물을 건설하기 위해 사전에 건설해야했던 건물이 cur
				// dp[temp.vertex]에는 temp번 건물을 건설하기 위해 기다려야하는 시간의 최대값+temp번 건물 건설시간이 저장되어있음
				// 기존에 temp번 건물을 건설하기 위해 기다려야하는 시간보다 현재 cur에서 걸리는 시간이 크다면 업데이트
				dp[temp.vertex] = Math.max(dp[cur] + time[temp.vertex], dp[temp.vertex]);
				if (--inDegree[temp.vertex] == 0) {
					queue.offer(temp.vertex);
				}
			}
		}

	}
}
