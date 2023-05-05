package week12.BOJ_2310_G4_어드벤처게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2310_G4_어드벤처게임 {

	public static int n;
	public static Node[] adjList;
	public static String result;

	public static class Node {
		int vertex; // 다음 방번호
		char type; // 현재 방 타입
		int amount; // 금액
		Node link;

		public Node(int vertex, char type, int amount, Node link) {
			super();
			this.vertex = vertex;
			this.type = type;
			this.amount = amount;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", type=" + type + ", amount=" + amount + ", link=" + link + "]";
		}

	}

	public static class Info {
		int no; // 현재 방 번호
		int money; // 현재 가진 소지금

		public Info(int no, int money) {
			super();
			this.no = no;
			this.money = money;
		}

		@Override
		public String toString() {
			return "Info [no=" + no + ", money=" + money + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());

			if (n == 0) {
				break;
			}

			adjList = new Node[n + 1];
			for (int i = 1; i <= n; i++) {
				String[] input = in.readLine().split(" ");
				int from = i;
				char type = input[0].charAt(0);
				int amount = Integer.parseInt(input[1]);

				// 인접리스트 생성(방타입과 금액을 같이 들고감)
				for (int j = 2; j < input.length - 1; j++) {
					int to = Integer.parseInt(input[j]);
					adjList[from] = new Node(to, type, amount, adjList[from]);
				}

			}

			result = "No";
			bfs();
			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Info> queue = new ArrayDeque<>();
		int[] isVisited = new int[n + 1];
		queue.offer(new Info(1, 0));
		isVisited[1] = 0;

		while (!queue.isEmpty()) {
			Info cur = queue.poll();

			for (Node temp = adjList[cur.no]; temp != null; temp = temp.link) {
				if (isVisited[temp.vertex] != 0) { // 해당 방을 방문한 적 있을 때
					// 더 큰 돈을 가지고 이동 가능 할 때는 방문했던 곳도 다시 방문 가능
					if (cur.money <= isVisited[temp.vertex]) { // 소지금이 예전보다 작으므로 방문X
						continue;
					}
				}

				int money = cur.money;
				if (temp.type == 'L') { // 레프리콘
					if (cur.money < temp.amount) {
						money = temp.amount;
					}
				} else if (temp.type == 'T') { // 트롤
					if (cur.money < temp.amount) {
						continue;
					} else {
						money -= temp.amount;
					}
				}

				if (cur.no == n) { // n번방에 도착했을 때
					result = "Yes";
					return;
				}

				queue.offer(new Info(temp.vertex, money));
				isVisited[temp.vertex] = money;
			}

		}
	}
}
