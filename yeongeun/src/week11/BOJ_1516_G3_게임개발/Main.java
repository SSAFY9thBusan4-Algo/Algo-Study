package week11.BOJ_1516_G3_게임개발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node {
		int n;
		Node next;
		public Node(int n, Node next) {
			this.n = n;
			this.next = next;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] buildTime = new int[N+1];
		int[] precnt = new int[N+1];
		Node[] nextNode = new Node[N+1];
		
		for(int i = 1 ; i <= N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			buildTime[i] = Integer.parseInt(st.nextToken());
			
			while(true) {	// 먼저 지어져야 하는 건물들의 번호
				int n = Integer.parseInt(st.nextToken());
				if(n == -1) break;
				
				precnt[i]++;
				nextNode[n] = new Node(i, nextNode[n]);
			}
		}
		
		// solve
		int[] result = run(N, buildTime, precnt, nextNode);
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= N; i++) {
			sb.append(result[i]).append('\n');
		}
		System.out.println(sb);
		
	}

	private static int[] run(int N, int[] buildTime, int[] precnt, Node[] nextNode) {

		int[] result = new int[N+1];
		int[] enter = new int[N+1];
		
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			if(precnt[i] == 0) queue.offer(i);
		}
		
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			result[cur] = enter[cur] + buildTime[cur];
			
			for(Node node = nextNode[cur]; node != null; node = node.next) {
				enter[node.n] = result[cur] > enter[node.n] ? result[cur] : enter[node.n];
				if(--precnt[node.n] == 0) {
					queue.offer(node.n);
				}
			}
		}
		
		return result;
	}
}

