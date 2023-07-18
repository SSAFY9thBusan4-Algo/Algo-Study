package week21.BOJ_2623_G3_음악프로그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}		
	}
	
	static int N,M;
	static Node[] adjList;
	static int[] inDegree;

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		int[] PD;
		for (int i=1; i<=M; i++) {
			PD = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
			for (int j=1; j<PD.length-1; j++) {
				adjList[PD[j]] = new Node(PD[j+1], adjList[PD[j]]);
				inDegree[PD[j+1]]++;
			}
		}		

		List<Integer> sorted = topologySort();		
		if (sorted.size()==N) {
			for (Integer n : sorted) System.out.println(n);			
		}else System.out.println(0);
	}

	private static List<Integer> topologySort() {
		
		List<Integer> sorted = new ArrayList<Integer>();
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i=1; i<=N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		Integer cur;
		while(!queue.isEmpty()) {			
			cur = queue.poll();
			sorted.add(cur);
			for (Node n=adjList[cur]; n!=null; n=n.link) {
				if (--inDegree[n.vertex]==0) {
					queue.offer(n.vertex);
				}
			}
			
		}
		return sorted;
	}
	
}
