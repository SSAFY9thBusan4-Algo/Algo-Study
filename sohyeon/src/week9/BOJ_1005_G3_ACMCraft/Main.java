package week9.BOJ_1005_G3_ACMCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, W;
	static ArrayList<Integer>[] adjList; 
	static int[] costs;  // 건물 짓는 비용
	static int[] maxCosts;  // 각 위치까지 건물을 짓는 최대 비용
	static int[] inDegree;  // 각 위치의 진입 간선 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			costs = new int[N+1];
			maxCosts = new int[N+1];
			inDegree = new int[N+1];
			
			st = new StringTokenizer(in.readLine(), " ");
			for (int i=1; i<=N; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
				adjList[i] = new ArrayList<>();
			}
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from].add(to);
				inDegree[to]++;
			}
			
			W = Integer.parseInt(in.readLine());
			
			topologySort();
			System.out.println(maxCosts[W]);
		}
		
	}

	private static void topologySort() {
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for (int i=1; i<=N; i++) {
			if (inDegree[i]==0) {
				maxCosts[i] = costs[i];
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()){
			
			int cur = queue.poll();
			for (int i=0; i<adjList[cur].size(); i++) {
				int next = adjList[cur].get(i);
				maxCosts[next] = Math.max(maxCosts[next], maxCosts[cur]+costs[next]);
				inDegree[next]--;
				if (inDegree[next]==0) queue.offer(next);
			}
			
		}
		
	}
	
}
