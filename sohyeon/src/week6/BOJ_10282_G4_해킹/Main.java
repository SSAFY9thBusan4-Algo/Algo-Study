package week6.BOJ_10282_G4_해킹;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static final int INF = Integer.MAX_VALUE;

	private static class Vertex {

		public int no;
		public int weight;
		public Vertex next;

		public Vertex(int vertex, int weight, Vertex next) {
			this.no = vertex;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + no + ", weight=" + weight + ", next=" + next + "]";
		}
	}
	
	public static void main(String args[]) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		
		while(T-- > 0) {
			
			String[] split = in.readLine().split(" ");
			int V = Integer.parseInt(split[0]);  // 정점의 개수
			int E = Integer.parseInt(split[1]);  // 간선의 개수
			int K = Integer.parseInt(split[2]);  // 시작 정점의 번호

			Vertex[] adjList = new Vertex[V + 1];  // 0번 인덱스 사용안함 (정점번호는 1번부터 시작)
			int start = K;

			// 인접 리스트 데이터 입력
			for (int i = 0; i < E; i++) {
				split = in.readLine().split(" ");
				int to = Integer.parseInt(split[0]);
				int from = Integer.parseInt(split[1]);
				int weight = Integer.parseInt(split[2]);

				adjList[from] = new Vertex(to, weight, adjList[from]);
			}

			int[] distance = new int[V + 1];  // 출발지에서 자신으로의 최소(최단) 비용(거리) => 최단 경로 구성 가중치 합
			boolean[] visited = new boolean[V + 1];  // 최단 경로 포함여부
			Arrays.fill(distance, INF);

			distance[start] = 0;  // 시작점의 비용 0으로 설정

			for (int i = 1; i <= V; i++) {
				// 1단계 : 최소비용이 확정되지 않은 정점 중 출발지로부터 최소 비용의 정점 선택
				int min = INF;
				int current = 0;
				for (int j = 1; j <= V; j++) {
					if (!visited[j] && distance[j] < min) {
						min = distance[j];
						current = j;
					}
				}

				visited[current] = true;  // 선택 정점 방문 처리

				// 2단계 : 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른정점의 최소비용을 고려
				for (Vertex temp = adjList[current]; temp != null; temp = temp.next) {
					if (!visited[temp.no] &&
							distance[temp.no] > distance[current] + temp.weight) {
						distance[temp.no] = distance[current] + temp.weight;
					}
				}
			}

			//System.out.println(Arrays.toString(distance));
			int cnt = 0;
			int time = 0;
			for (int d : distance) {
				if (d != INF) {
					cnt++;
					time = Math.max(time, d);
				}
			}
			
			System.out.println(cnt+" "+time);
			
		}

	}
}


