package week14.BOJ_11724_S2_연결요소의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int N,M;  // 정점, 간선 수
	static int[] parents;
	
	static void makeset() {
		parents = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static int findset(int n1) {
		if (parents[n1] == n1) return n1;
		
		return parents[n1] = findset(parents[n1]);
	}
	
	static boolean union(int n1, int n2) {
		int p1 = findset(n1);
		int p2 = findset(n2);
		
		if (p1 == p2) return false;
		
		parents[p2] = p1;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		makeset();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
		
			union(n1, n2);
		}
		
		Set<Integer> set = new HashSet<Integer>();
		
		for (int i=1; i<=N; i++) {
			set.add(findset(parents[i]));
		}
		
		System.out.println(set.size());
	}
	
}
