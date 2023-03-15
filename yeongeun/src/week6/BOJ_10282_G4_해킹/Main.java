package src.week6.BOJ_10282_G4_해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	private static class Node implements Comparable<Node> {
		int num, timer;
		Node link;
		int pretime;

		public Node(int num, int timer, Node link) {
			super();
			this.num = num;
			this.timer = timer;
			this.link = link;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(pretime + timer, o.pretime + o.timer);
		}

	}

	private static int count, time;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());	//100
		for (int tc = 1; tc <= T; tc++) {

			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int D = Integer.parseInt(input[1]);
			int C = Integer.parseInt(input[2]);

			Node[] nodes = new Node[N + 1];
			for (int i = 0; i < D; i++) {
				input = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				int s = Integer.parseInt(input[2]);

				nodes[b] = new Node(a, s, nodes[b]);
			}

			// ====================================
			// solve

			count = 1;
			time = 0;
			infection(C, nodes);

			sb.append(count).append(' ').append(time).append('\n');
		}
		System.out.println(sb);
	}

	private static void infection(int start, Node[] nodes) {

		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] visited = new boolean[nodes.length];
		visited[start] = true;

		// 첫번째 컴퓨터가 선행조건인 컴퓨터를 큐에 넣는다. 이때 이전 시간은 0.
		for (Node n = nodes[start]; n != null; n = n.link) {
			n.pretime = 0;
			queue.offer(n);
		}

		while (!queue.isEmpty()) {

			Node cur = queue.poll();
			if (visited[cur.num])
				continue;
			
			// 다음 컴퓨터가 감염됨. 이때 시간은 pre + timer
			visited[cur.num] = true;
			time = cur.pretime + cur.timer;
			count++;
			if(count == nodes.length-1) break; 

			// cur 컴퓨터가 선행조건인 컴퓨터 삽입.
			for (Node n = nodes[cur.num]; n != null; n = n.link) {
				if(visited[n.num]) continue;
				n.pretime = time;
				queue.offer(n);
			}
		}

	}
}
