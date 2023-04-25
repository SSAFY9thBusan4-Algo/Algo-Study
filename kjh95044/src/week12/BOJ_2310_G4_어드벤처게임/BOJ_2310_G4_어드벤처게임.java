import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static Node[] adjList;
	static char[] cmds;
	static int[] costs;
	static int money;
	static int N;
	static String result;
	static boolean[] visited;

	static class Node {
		int vertex;
		Node link;

		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
		
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		while (true) {
			N = Integer.parseInt(in.readLine());
			
			if(N==0) {
				break;
			}
			
			result = "No";
			adjList = new Node[N + 1];
			cmds = new char[N + 1];
			costs = new int[N + 1];
			visited = new boolean[N+1];

			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(in.readLine());
				cmds[n] = st.nextToken().charAt(0);
				costs[n] = Integer.parseInt(st.nextToken());
				int from = n;

				while (st.hasMoreTokens()) {
					int to = Integer.parseInt(st.nextToken());
					if (to != 0) {
						adjList[from] = new Node(to, adjList[from]);
					}
				}
			}
			visited[1] = true;
			dfs(0,1);
			sb.append(result).append("\n");
			
		}

		System.out.println(sb);
	}

	private static void dfs(int money, int no) {
//		System.out.println(no + " " + money);
		
		if(no == N ) {
			result = "Yes";
			return;
		}
		
		for(Node temp = adjList[no]; temp !=null; temp = temp.link) {
			if(!visited[temp.vertex]) {
				
				char cmd = cmds[temp.vertex];
				int cost = costs[temp.vertex];
				
				if(cmd == 'L') {
					if(money< cost) {
						money = cost;
					}
				}else if(cmd == 'T') {
					if(money < cost) {
						visited[temp.vertex] = false;
						continue;
					}else {
						money -= cost;
					}
				}
				
				visited[temp.vertex] = true;
				dfs(money, temp.vertex);
				visited[temp.vertex] = false;
			}
		}
		
	}
}

/*
3
E 0 2 0
L 10 3 0
T 15 1 2 0
4
E 0 2 3 0
L 201 2 3 0
L 10 4 0
T 15 2 3 1 0
0
*/
