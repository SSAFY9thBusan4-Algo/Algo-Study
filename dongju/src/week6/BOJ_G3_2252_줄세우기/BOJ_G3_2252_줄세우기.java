import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n, inDegree[];
	static ArrayList<Integer>[] list;
	static ArrayList<Integer> orderList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		inDegree = new int[n+1];
		orderList = new ArrayList<>();
		
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			list[from].add(to);
			inDegree[to]++;
		}

		topologySort();
		
		for(int i: orderList) {
			sb.append(i).append(" ");
		}
		
		System.out.println(sb);
	}

	private static void topologySort() {
		Queue<Integer> que = new ArrayDeque<>();
		
		// 1. 진입 차수가 0인 정점 큐에 넣기
		for(int i=1; i<=n; i++) {
			if(inDegree[i] == 0) que.offer(i);
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			orderList.add(cur);
			
			for(int i: list[cur]) {
				if(--inDegree[i] == 0) { // 인접한 정점 중 진입 차수를 1빼서 0이 된다면
					que.offer(i);
				}
			}
		}
	}
}
