package week9.BOJ_1005_G3_ACMCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005_G3_ACMCraft {

	public static int N;
	public static int K;
	public static int[] D;
	public static int[] dp;
	public static int W;
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

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			D = new int[N + 1];
			dp = new int[N + 1]; // 해당 index의 건물을 짓기까지 걸리는 시간
			st = new StringTokenizer(in.readLine());
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken());
			}

			adjList = new Node[N + 1]; // 건물 사이의 관계 리스트
			inDegree = new int[N + 1];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adjList[from] = new Node(to, adjList[from]);
				inDegree[to]++;
			}

			st = new StringTokenizer(in.readLine());
			W = Integer.parseInt(st.nextToken());

			topologySort();

			sb.append(dp[W]).append("\n");
		}
		System.out.println(sb);
	}

	private static void topologySort() {
		Queue<Integer> queue = new ArrayDeque<>();

		// 진입차수가 0인 건물 큐에 담기
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				dp[i] = D[i]; // dp 초기값 설정
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int node = queue.poll();

			for (Node temp = adjList[node]; temp != null; temp = temp.link) {
				// temp번 건물을 건설하기 위해 사전에 건설해야했던 건물이 node
				// dp[temp.vertex]에는 temp번 건물을 건설하기 위해 기다려야하는 시간의 최대값+temp번 건물 건설시간이 저장되어있음
				// 기존에 temp번 건물을 건설하기 위해 기다려야하는 시간보다 현재 node에서 걸리는 시간이 크다면 업데이트
				dp[temp.vertex] = Math.max(dp[temp.vertex], dp[node] + D[temp.vertex]);

				if (--inDegree[temp.vertex] == 0) { // 진입차수가 0이면 큐에 담음
					queue.offer(temp.vertex);
				}
			}
		}
	}
}
