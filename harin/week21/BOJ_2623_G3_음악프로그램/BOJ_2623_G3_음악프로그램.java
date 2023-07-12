import java.io.*;
import java.util.*;

public class BOJ_2623_G3_음악프로그램 {

  //위상 정렬 문제!!

  //노드 클래스 생성
	static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}
	
	static int N, M;
	static Node[] adjList;
	static int[] inDegree; //진입 차수 
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 가수의 수
		M = Integer.parseInt(st.nextToken()); // 보조 PD의 수
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		int from, to;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int singerCnt = Integer.parseInt(st.nextToken());
			from = Integer.parseInt(st.nextToken());
			for(int j=1; j<singerCnt; j++) {
				to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				inDegree[to]++;
				from = to;
			}
		}
		
		ArrayList<Integer> list = topologySort();
		
		if(list.size() == N) {
			StringBuilder sb = new StringBuilder();
			for(int singer : list) {
				sb.append(singer).append('\n');
			}
			System.out.print(sb);
		}
		else {
			System.out.print(0);
		}
	}

	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<Integer>();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) queue.offer(i);
		} //진입 차수가 0인 정점 큐에 넣기
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			orderList.add(cur);
			
			//현재 정점을 기준으로 인접 정점 처리
			for(Node temp = adjList[cur]; temp != null; temp = temp.link) {
				if(--inDegree[temp.vertex] == 0) {
					queue.offer(temp.vertex);
				}
			}
		}
		
		return orderList;
	}

}
