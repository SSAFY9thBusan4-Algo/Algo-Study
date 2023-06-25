import java.io.*;
import java.util.*;

public class BOJ_14938_G4_서강그라운드 {

	// 노드 클래스 생성
	static class Node {
		int idx;
		int cost;

		public Node(int idx, int cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int V = Integer.parseInt(st.nextToken()); //노드 개수
		int limit = Integer.parseInt(st.nextToken()); //수색 범위
		int E = Integer.parseInt(st.nextToken()); //간선 개수
		int ans = Integer.MIN_VALUE; //예은이가 얻을 수 있는 아이템 최대 개수

		// 그래프 초기화
		ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
		for(int i=0; i<V+1; i++) {
			graph.add(new ArrayList<Node>());
		}

		// 아이템 개수 저장 배열
		int[] items = new int[V+1];
		// 아이템 개수 입력
		st = new StringTokenizer(in.readLine());
		for(int i=1; i<=V; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		// 그래프에 값 넣기
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end  = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 양방향이라고 했으니까 둘 다 넣어준다.
			graph.get(start).add(new Node(end, cost));
			graph.get(end).add(new Node(start, cost));
		}

		/*
		 * 다익스트라 알고리즘 시작
		 * 시작하는 노드는 모든 노드가 될 수 있으니 for문으로 하나씩 봐준다. 
		 */
		for(int v=1; v<=V; v++) {
			boolean[] visited = new boolean[V+1];
			int[] distance = new int[V+1];

			// 배열 무한대 값으로 초기화
			Arrays.fill(distance, Integer.MAX_VALUE);
			// 출발 지점 비용은 0으로 시작
			distance[v] = 0;
			
			for(int i=0; i<V; i++) { //노드 개수만큼 반복
				int currentVal = Integer.MAX_VALUE;
				int nodeIdx = 0;

				
				for(int j=1; j<=V; j++) {
					if(!visited[j] && distance[j] < currentVal) {
						currentVal = distance[j];
						nodeIdx = j;
					}
				}
				
				visited[nodeIdx] = true;
				
				for(int j=0; j<graph.get(nodeIdx).size(); j++) {
					Node adjNode = graph.get(nodeIdx).get(j);
					if(distance[adjNode.idx] > distance[nodeIdx] + adjNode.cost) {
						distance[adjNode.idx] = distance[nodeIdx] + adjNode.cost;
					}
				}
			}

			int itemCnt = 0;
			// 수색 범위 안에 따라 갈 수 있는 곳 확인 후 => 아이템 갯수 계산
			for(int i=1; i<=V; i++) {
				if(distance[i] <= limit) {
					itemCnt += items[i];
				}
			}
			
			ans = Math.max(ans, itemCnt); // 더 큰 값 ans에 저장
		}

		System.out.println(ans);
	}

}
