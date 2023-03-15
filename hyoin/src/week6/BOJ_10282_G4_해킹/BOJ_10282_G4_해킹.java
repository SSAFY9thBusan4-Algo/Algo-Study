package week6.BOJ_10282_G4_해킹;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10282_G4_해킹 {

	public static StringBuilder sb = new StringBuilder();

	public static class Node {
		int vertex;
		Node link;
		int time; // 감염되는 시간

		public Node(int vertex, Node link, int time) {
			super();
			this.vertex = vertex;
			this.link = link;
			this.time = time;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			Node[] adjList = new Node[n + 1]; // 인접리스트
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken()); // to
				int b = Integer.parseInt(st.nextToken()); // from
				int s = Integer.parseInt(st.nextToken()); // time

				adjList[b] = new Node(a, adjList[b], s); // b -> a로 연결해야함
			}

			// 다익스트라 사용
			// c정점에서 시작하여 인접된 모든 정점들의 최단 감염시간을 구함
			// 최단시간을 구하는 이유 : 간선정보가 1->3(time: 8), 1->2(time:2), 2->3(time:4)이고 시작 정점이 1이라고 하자
			// 					 1로 인해 감염되는 컴퓨터는 1,2,3이다. 이 때 2는 감염까지 2초가 걸린다. 3은 1에서 감염되면 8초가 걸리고 1->2->3으로 감염되면 6초가 걸린다.
			//					 즉 1->3(8초)를 통해 감염되기 전에 1->2->3(6초)로 인해 먼저 감염되므로 최단시간경로를 구해야함
			// 시간을 구할 수 있다는 것은 감염이 되었다는 뜻이므로 모든 정점들의 최단시간들 중 제일 큰 값이 최종적으로 총 감염되는 시간
			final int INF = Integer.MAX_VALUE;
			int[] distance = new int[n + 1]; // c정점에서부터 해당 인덱스의 노드까지 소요되는 최단 감염 시간
			boolean[] visited = new boolean[n + 1];  // 중간 경유지로 사용됐는지

			Arrays.fill(distance, INF);
			distance[c] = 0;

			int min, current;
			for (int i = 1; i <= n; i++) {
				// step1 : 경유지로 처리되지 않은 정점 중 출발지에서 가장 가까운 정점 선택
				current = -1;
				min = INF;

				for (int j = 1; j <= n; j++) {
					if (!visited[j] && min > distance[j]) {
						min = distance[j];
						current = j;
					}
				}

				if (current == -1)
					break;
				visited[current] = true;

				// step2 : 위에서 선택된 정점을 경유지로 해서 갈 수 있는 다른 미방문 인접정접과의 비용 최소값 갱신
				for (Node temp = adjList[current]; temp != null; temp = temp.link) {
					if (!visited[temp.vertex] && distance[temp.vertex] > min + temp.time) {
						distance[temp.vertex] = min + temp.time;
					}
				}

			}

			int computer_count = 0; // 총 감염되는 컴퓨터 수
			int time = 0; // 마지막 컴퓨터가 감염되는 시간
			for (int i = 1; i <= n; i++) {
				if (distance[i] != Integer.MAX_VALUE) { // 감염된 컴퓨터만 탐색
					computer_count += 1;
					if (distance[i] > time) { // 감염된 컴퓨터 중 가장 큰 시간이 최종적으로 감염되는 시간
						time = distance[i];
					}
				}
			}

			sb.append(computer_count).append(" ").append(time).append("\n");
		}

		System.out.println(sb);
	}
}
