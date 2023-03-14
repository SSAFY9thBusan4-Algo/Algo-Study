package week6.BOJ_2252_G3_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

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

	static int N, M, inDegree[];
	static Node[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]); //학생 수
		M = Integer.parseInt(split[1]); //비교
		
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		
		int from = 0, to = 0;
		for(int i=0;i<M;i++) {
			split = br.readLine().split(" ");
			to = Integer.parseInt(split[0]); //학생 수
			from = Integer.parseInt(split[1]);
			adjList[to] = new Node(from, adjList[to]);
			inDegree[from]++;
		}
		
		ArrayList<Integer> list = topologysort();
		
		for(int i: list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	private static ArrayList<Integer> topologysort() {
		ArrayList<Integer> order = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i=1;i<N+1;i++) {
			if(inDegree[i]==0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			order.add(current);
			
			for(Node temp = adjList[current]; temp!=null; temp=temp.link) {
				if(--inDegree[temp.vertex] == 0) {
					queue.offer(temp.vertex);
				}
			}
			
		}
		return order;
	}
}
