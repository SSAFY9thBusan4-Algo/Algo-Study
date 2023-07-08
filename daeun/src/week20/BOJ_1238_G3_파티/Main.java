import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node>{
		int no, weight;

		public Node(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
	static int N, M, X;
	static int[] t;
	static ArrayList<Node>[] list;
	static int[][] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]); //학생 수
		M = Integer.parseInt(split[1]); //단방향 도로 수
		X = Integer.parseInt(split[2]); //도착지? 파티 장소
		
		distance = new int[N+1][N+1];
		for (int i=1; i<N+1; i++) {
			Arrays.fill(distance[i], -1);
		}
		
		list = new ArrayList[M+1];
		for (int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		int start, end, time;
		for (int i=0; i<M; i++) {
			split = br.readLine().split(" ");
			start = Integer.parseInt(split[0]);
			end = Integer.parseInt(split[1]);
			time = Integer.parseInt(split[2]);
			list[start].add(new Node(end, time)); //단방향 
		}
		
		//다익스트라로 최단시간 구하기
		for (int i=1; i<N+1; i++) {
			PriorityQueue<Node> queue = new PriorityQueue<>();
			queue.add(new Node(i, 0)); //시작점, 시간
			
			while(queue.size()!=0) {
				Node now = queue.poll();
				int no = now.no;
				int weight = now.weight;
				if(distance[i][no] == -1) {
					distance[i][no] = weight;
					for(Node next : list[no]) {
						int cal = weight + next.weight;
						queue.add(new Node(next.no, cal));
					}
				}
			}
		}
		
		int result = 0;
		for (int i=1; i<N+1; i++) {
			result = Math.max(result, distance[i][X] + distance[X][i]);
		}
		
		System.out.println(result);
	}
}
