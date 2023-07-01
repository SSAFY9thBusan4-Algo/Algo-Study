package week19.BOJ_24938_G4_서강그라운드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_24938_G4_서강그라운드 {

	private static int n;
	private static int m;
	private static int r;
	private static int[] items;
	private static int[][] adjMatrix;

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken()); // 정점 개수
		m = Integer.parseInt(st.nextToken()); // 수색범위
		r = Integer.parseInt(st.nextToken()); // 가중치개수

		items = new int[n + 1];
		st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		adjMatrix = new int[n + 1][n + 1];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(in.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			adjMatrix[node1][node2] = dist;
			adjMatrix[node2][node1] = dist;
		}

		int result = 0;
		for (int i = 1; i <= n; i++) {
			result = Math.max(result, Dijkstra(i));
		}
		
		System.out.println(result);

	}

	private static int Dijkstra(int start) {
		int[] path = new int[n+1]; // 경로 (인덱스는 도착 정점번호, 값은 시작 정점번호)
		int[] distance = new int[n+1]; // 출발지에서 자신으로 오는데 소요되는 최소비용
		boolean[] visited = new boolean[n+1]; // 최단 경로 포함여부
		Arrays.fill(distance, INF); // 무한대 설정

		// 출발 정점 처리
		distance[start] = 0;

		// 경로 배열에 초기 경로를 start 정점으로 설정
		Arrays.fill(path, start);

		int min = 0; // 선택된 최소비용
		int current = 0; // 선택된 정점

		for (int cnt = 1; cnt <= n; cnt++) {

			// 1단계 : 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
			// 방문 해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
			current = -1;
			min = INF;

			for (int i = 1; i <= n; i++) {
				if (!visited[i] && distance[i] < min) {
					min = distance[i];
					current = i;
				}
			}

			// (선택사항) 가지치기: 정점을 못 찾은 경우
			if (current == -1) {
				break;
			}

			// 방문처리
			visited[current] = true; // 선택 정점 방문 처리

			// 2단계: 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고
			// 기존 최적해보다 유리하면 갱신
			// 아래 if문에서 !visited[j]는 생략 가능
			// 이유: distance[j] > min + adjMatrix[current][j] 조건에서 걸러지기 때문
			for (int i = 1; i <= n; i++) {
				if (!visited[i] && adjMatrix[current][i] != 0 && distance[i] > min + adjMatrix[current][i]) {
					distance[i] = min + adjMatrix[current][i];
					path[i] = current;
				}
			}
		}

		int max = 0;

		// 수색 범위 내로 습득할 수 있는 아이템 개수
		for (int i = 1; i <= n; i++) {
			if (distance[i] <= m) {
				max += items[i];
			}
		}

		return max;
	}

}
