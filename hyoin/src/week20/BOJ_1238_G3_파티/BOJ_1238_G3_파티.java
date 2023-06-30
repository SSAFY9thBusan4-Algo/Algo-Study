package week20.BOJ_1238_G3_파티;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1238_G3_파티 {

	public static int N; // 학생수
	public static int M; // 방향개수
	public static int X; // 도착지
	public static int[][] adjMatrix;
	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		adjMatrix = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			adjMatrix[start][end] = T;
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			int distance1 = Dijkstra(i, X); // 학생마을 -> X
			int distance2 = Dijkstra(X, i); // X -> 학생마을
			int tempMax = distance1 + distance2;

			if (max < tempMax) {
				max = tempMax;
			}
		}

		System.out.println(max);
	}

	private static int Dijkstra(int start, int end) {
		int[] path = new int[N + 1]; // 경로
		int[] distance = new int[N + 1]; // 출발지에서 자신으로 오는데 소요되는 최소비용
		boolean[] visited = new boolean[N + 1]; // 최단 경로 포함여부
		Arrays.fill(distance, INF); 

		// 출발 정점 처리
		distance[start] = 0;

		// 경로 배열에 초기 경로를 start 정점으로 설정
		Arrays.fill(path, start);

		int min = 0; // 선택된 최소비용
		int current = 0; // 선택된 정점

		for (int cnt = 1; cnt <= N; cnt++) {

			// 1단계 : 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
			// 방문 해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
			current = -1;
			min = INF;

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && distance[i] < min) {
					min = distance[i];
					current = i;
				}
			}

			// 정점을 못 찾은 경우
			if (current == -1) {
				break;
			}

			// 도착지에 도착했을 때
			if (current == end) {
				break;
			}

			// 선택 정점 방문 처리
			visited[current] = true;

			// 2단계: 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고 기존 최적해보다 유리하면 갱신
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[current][i] != 0 && distance[i] > min + adjMatrix[current][i]) {
					distance[i] = min + adjMatrix[current][i];
					path[i] = current;
				}
			}
		}

		return distance[end] != INF ? distance[end] : -1;
	}
}
