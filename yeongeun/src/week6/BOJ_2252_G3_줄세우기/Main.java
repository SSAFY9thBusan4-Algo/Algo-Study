package src.week6.BOJ_2252_G3_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


public class Main {
	private static class Node {
		int n;
		Node link;
		public Node(int n, Node link) {
			super();
			this.n = n;
			this.link = link;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);	//32,000
		int M = Integer.parseInt(input[1]); //100,000
		
		Node[] list = new Node[N+1];
		int[] seccount = new int[N+1];	// 선행노드 수 카운트
		for(int i = 0 ; i < M; i++) {
			input = br.readLine().split(" ");
			int n1 = Integer.parseInt(input[0]);
			int n2 = Integer.parseInt(input[1]);
			// 1 다음에 2
			list[n1] = new Node(n2, list[n1]);
			seccount[n2]++;
		}
		
		// solve
		int[] result = lining(list, seccount);
		
		// output
		StringBuilder sb = new StringBuilder();
		for(int n : result) {
			sb.append(n).append(' ');
		}
		System.out.println(sb);
	}

	private static int[] lining(Node[] list, int[] seccount) {
		
		Queue<Integer> queue = new ArrayDeque<>();

		// 1. 선행 노드가 없는 노드 찾기.
		for(int i = 1 ; i < seccount.length; i++) {
			if(seccount[i] == 0) queue.offer(i);
		}
		
		// 2. 큐 빼내면서 선행 노드가 모두 나왔으면 큐에 넣기.
		int[] result = new int[list.length-1];
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			
			Integer curidx = queue.poll();
			result[cnt++] = curidx;
			if(cnt == list.length) break;
			
			for(Node next = list[curidx]; next != null; next = next.link) {
				if(--seccount[next.n] == 0) {
					queue.offer(next.n);
				}
			}
			
		}
		
		return result;
	}
}
