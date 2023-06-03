import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
public class Main {
	static class Node implements Comparable<Node>{
		int A, B, C;
		public Node(int a, int b, int c) {
			super();
			A = a;
			B = b;
			C = c;
		}
		@Override
		public int compareTo(Node o) {
			return this.C - o.C;
		}
	}
  
	static ArrayList<Node> list;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		list = new ArrayList<>();
    
		for(int i=0;i<M;i++) {
			split = br.readLine().split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int c = Integer.parseInt(split[2]);
			list.add(new Node(a,b,c));
		}
    
		parent = new int[N+1];
		for(int i=1;i<N+1;i++) {
			parent[i] = i;
		}
		
		Collections.sort(list);
		
		int[] cost = new int[N-1];
		int cnt = 0;
		for(Node now : list) {
			if(find(now.A)!=find(now.B)) {
				cost[cnt] = now.C;
				union(now.A, now.B);
				cnt++;
			}
		}
		
		int result = 0;
		for(int i=0;i<N-2;i++) { 
			result += cost[i];
		}
		
		System.out.println(result);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a!=b) {
			parent[b] = a;
		}
	}
	
	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		else {
			return parent[a] = find(parent[a]);
		}
	}
}
