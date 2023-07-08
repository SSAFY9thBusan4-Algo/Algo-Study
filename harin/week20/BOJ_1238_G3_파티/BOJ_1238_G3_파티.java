import java.io.*;
import java.util.*;

public class BOJ_1238_G3_파티 {
	
	// 노드 클래스 생성
	static class Node {
		int idx;
		int cost;
		
		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}		
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()); // 노드 개수
		int M = Integer.parseInt(st.nextToken()); // 간선 개수
		int X = Integer.parseInt(st.nextToken()); // X번 마을 (방문해야하는 마을)
		
		// 그래프 초기화
		ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		// 그래프에 값 넣기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new Node(end, cost));
		}
		
		int distance[][] = new int[N+1][N+1];
		// 다익스트라 알고리즘 시작
		for(int v=1; v<=N; v++) {
			boolean[] visited = new boolean[N+1];
			
			// 배열 무한대로 초기화
			Arrays.fill(distance[v], Integer.MAX_VALUE);
			// 출발 지점 비용은 0으로 시작
			distance[v][v] = 0;
			
			for(int i=1; i<=N; i++) {
				int currentVal = Integer.MAX_VALUE;
				int nodeIdx = 0;
				
				for(int j=1; j<=N; j++) {
					if(!visited[j] && distance[v][j] < currentVal) {
						currentVal = distance[v][j];
						nodeIdx = j;
					}
				}
				
				visited[nodeIdx] = true;
				
				for(int j=0; j<graph.get(nodeIdx).size(); j++) {
					Node adjNode = graph.get(nodeIdx).get(j);
					if(distance[v][adjNode.idx] > distance[v][nodeIdx] + adjNode.cost) {
						distance[v][adjNode.idx] = distance[v][nodeIdx] + adjNode.cost;
					}
				}
			}
		}
		
		// 오고가는데 가장 많은 시간을 소비하는 학생 구하기
		int ans = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			if(i==X) continue;
			ans = Math.max(ans, distance[i][X] + distance[X][i]);
		}
		System.out.println(ans);
	}

}
