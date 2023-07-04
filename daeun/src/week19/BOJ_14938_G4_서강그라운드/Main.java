import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node>{
		int idx;
		int weight;
		public Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	static int n, m, r, item[], minD[];
	static ArrayList<Node>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//최대 아이템 개수
		String[] split = br.readLine().split(" ");
		n = Integer.parseInt(split[0]); //지역 개수
		m = Integer.parseInt(split[1]); //수색범위
		r = Integer.parseInt(split[2]); //길의 개수
		
		split = br.readLine().split(" ");
		item = new int[n];
		list = new ArrayList[n];
		minD = new int[n];
		for(int i=0;i<n;i++) {
			item[i] = Integer.parseInt(split[i]);
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;i<r;i++) {
			split = br.readLine().split(" ");
			int from = Integer.parseInt(split[0])-1;
			int to = Integer.parseInt(split[1])-1;
			int weight = Integer.parseInt(split[2]);
			
			list[from].add(new Node(to, weight));
			list[to].add(new Node(from, weight));
		}
		
		int result = 0;
		for(int i=0;i<n;i++) {
			result = Math.max(result, dijkstra(i));
		}
		
		System.out.println(result);
	}
	
	private static int dijkstra(int i) {
		int cnt = 0;
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Arrays.fill(minD, Integer.MAX_VALUE);
		minD[i] = 0; //시작
		
		queue.add(new Node(i, 0));
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			int idx = now.idx;
			int dis = now.weight;
			
			//왔던 곳은 넘어가기
			if(minD[idx] < dis) continue;
			
			cnt+= item[idx];
			for(Node next: list[now.idx]) {	//연결 노드	
				//다음 노드가 새로운 값보다 크면서 수색범위 안에 있는 경우
				if(minD[next.idx] > minD[now.idx] + next.weight
						&& minD[now.idx] + next.weight <= m) {
					minD[next.idx] = minD[now.idx] + next.weight;
					queue.offer(new Node(next.idx, minD[next.idx]));
				}
			}
		}
		return cnt;
	}
}
