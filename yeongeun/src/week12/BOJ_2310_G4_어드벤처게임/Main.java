package src.week12.BOJ_2310_G4_어드벤처게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

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
	
	private static class Room {
		char type;
		int value;
		public Room(char type, int value) {
			super();
			this.type = type;
			this.value = value;
		}
	}
	
	private static Node[] linkedList;
	private static Room[] roomInfo;
	private static int N;
	
	private static final char EMPTY = 'E', LEFREKON = 'L', TROLL = 'T';
	private static final String YES = "Yes\n";
	private static final String NO = "No\n";
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			input(br);
			if(start()) sb.append(YES);
			else sb.append(NO);
		}
		
		System.out.println(sb);
	}

	private static boolean start() {
		int v = 0;	// 시작 금액.
		if(roomInfo[1].type == TROLL && roomInfo[1].value > 0) return false;
		else if(roomInfo[1].type == LEFREKON) v = roomInfo[1].value;

		Queue<int[]> queue = new ArrayDeque<>();	// 방 번호, 현재 금액
		queue.offer(new int[] {1,v});
		
		int[] visited = new int[N+1];
		Arrays.fill(visited, -1);
		visited[1] = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curN = cur[0];
			int money = cur[1];
			
			if(visited[curN] > money) continue;
			if(curN == N) break;
			
			// 다음 방으로 이동
			for(Node next = linkedList[curN]; next != null ; next = next.link) {
				int n = next.n;
				int nm = money;
				
				switch(roomInfo[next.n].type) {	// 소지금 구하기.
				case LEFREKON :
					if(roomInfo[n].value >= money) nm = roomInfo[n].value;
					break;
				case TROLL :
					if(roomInfo[n].value > money) continue;
					nm -= roomInfo[n].value;
					break;
				}

				if(visited[n] >= nm) continue;
				visited[n] = nm;
				queue.offer(new int[] {n, nm});	// 돈이 더 많을 때 이동.
			}
		}
		
		if(visited[N] == -1) return false;
		else return true;
	}

	private static void input(BufferedReader br) throws IOException {
		roomInfo = new Room[N+1];
		linkedList = new Node[N+1];
		
		for(int i = 1 ; i <= N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			roomInfo[i] = new Room(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
			
			while(true) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 0) break;
				
				linkedList[i] = new Node(n, linkedList[i]);
			}
		}
	}
}
