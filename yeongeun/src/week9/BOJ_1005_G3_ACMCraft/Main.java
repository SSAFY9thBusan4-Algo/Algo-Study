package src.week9.BOJ_1005_G3_ACMCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	private static int N, K, W;
	private static int[] craftTime, previousCraft;
	private static Node[] nextAcm;
	
	private static class Node {
		int num;
		Node link;
		public Node(int num, Node link) {
			this.num = num;
			this.link = link;
		}
	}
	
	private static class Craft implements Comparable<Craft>{
		int num, stime, ctime;
		
		public Craft(int num, int stime, int ctime) {
			this.num = num;
			this.stime = stime;
			this.ctime = ctime;
		}

		@Override
		public int compareTo(Craft o) {
			return (stime+ctime) - (o.stime + o.ctime);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T; tc++) {
			input(br);
			
			Queue<Craft> startPoint = getStartPoint();
			int minTime = run(startPoint);
			
			sb.append(minTime).append('\n');
		}
		
		System.out.println(sb);
	}
	
	// 입력받기
	private static void input(BufferedReader br) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		craftTime = new int[N+1];
		input = br.readLine().split(" ");
		for(int i = 1; i <= N; i++) {
			craftTime[i] = Integer.parseInt(input[i-1]);
		}
		
		previousCraft = new int[N+1];
		nextAcm = new Node[N+1];
		
		for(int i = 0 ; i < K ; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			
			nextAcm[x] = new Node(y, nextAcm[x]);
			previousCraft[y]++;
		}
		
		W = Integer.parseInt(br.readLine());
	}


	private static Queue<Craft> getStartPoint() {
		Queue<Craft> list = new PriorityQueue<>();
		
		// 선행조건이 없는 노드 넣기.
		for(int i = 1 ; i <= N; i++) {
			if(previousCraft[i] == 0) {
				list.offer(new Craft(i, 0, craftTime[i]));
			}
		}
		
		return list;
	}

	private static int run(Queue<Craft> queue) {
		
		// 방문
		while(!queue.isEmpty()) {
			Craft cur = queue.poll();
			
			if(cur.num == W) return cur.stime+cur.ctime;
			
			for(Node node = nextAcm[cur.num] ; node != null; node = node.link) {
				if(--previousCraft[node.num] == 0) {	// 0이되면 큐에넣기
					queue.offer(new Craft(node.num, cur.stime+cur.ctime, craftTime[node.num]));
				}
			}
		}
		
		return -1;
	}
}
