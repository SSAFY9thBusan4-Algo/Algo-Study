package week9.BOJ_1005_G3_ACMCraft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.io.IOException;

public class Main {
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
	}
	static int N, K, D[], W, in[], result = 0, dp[];
	static Node[] nodes;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());		
		for(int t=0;t<T;t++) {
			String[] split = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			K = Integer.parseInt(split[1]);
			nodes = new Node[N+1];
			D = new int[N+1]; //건설 시간
			in = new int[N+1]; //진입 차수
			dp = new int[N+1]; //소요 시간
			
			split = br.readLine().split(" ");
			for(int i=1;i<N+1;i++) {
				D[i] = Integer.parseInt(split[i-1]);
			}
			
			int from, to;
			for(int i=0;i<K;i++) {
				split = br.readLine().split(" ");
				from = Integer.parseInt(split[0]);
				to = Integer.parseInt(split[1]);
				nodes[from] = new Node(to, nodes[from]);
				in[to]++;
			}
			
			W = Integer.parseInt(br.readLine());
			
			topologySort();
			
			sb.append(dp[W]).append("\n");
		}
		System.out.println(sb);
	}
	
	//위상정렬
	static void topologySort(){
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i=1;i<N+1;i++) {
			if(in[i] == 0) {
				dp[i] = D[i];
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll(); //꺼내면 바로 비교
			for(Node temp = nodes[current]; temp!=null; temp = temp.link) {
				int now = temp.vertex;
				dp[now] = Math.max(dp[current]+D[now], dp[now]);
				if(--in[now] == 0) {
					queue.offer(temp.vertex);
				}
			}
		}
	}
}