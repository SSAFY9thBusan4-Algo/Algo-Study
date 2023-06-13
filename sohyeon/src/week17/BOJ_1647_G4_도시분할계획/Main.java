package week17.BOJ_1647_G4_도시분할계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static class Edge implements Comparable<Edge>{

		private int from;
		private int to;
		private int weight;			
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {			
			return this.weight-o.weight;
		}
		
	}
	
	private static int N,M;
	private static Edge[] edgeList;
	private static int[] parents;
	
	private static void makeSet() {
		parents = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			parents[i] =i; 
		}
	}
	
	private static int findSet(int i) {
		if (parents[i] == i) return i;
		
		return parents[i] = findSet(parents[i]);
	}
	
	private static boolean union(int a, int b) {
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[M];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edgeList);
		
		makeSet();
		
		int result = 0;
		int count = 0; 
		int maxw = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				maxw = Math.max(maxw, edge.weight);				
				
//				System.out.println(edge.from+ " "+edge.to+" "+result);
				if (++count >= N-1) {
					break;
				}
			}
		}
		
		System.out.println(result - maxw);
		
	}
	
}