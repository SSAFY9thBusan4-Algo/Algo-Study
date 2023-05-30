import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	static class Node {
		int num;
		Node link;

		public Node(int num, Node link) {
			super();
			this.num = num;
			this.link = link;
		}
	}
	
	static Node[] list;
	static int N, M, result;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		list = new Node[N + 1];
		visit = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			split = br.readLine().split(" ");
			int from = Integer.parseInt(split[0]);
			int to = Integer.parseInt(split[1]);
			list[from] = new Node(to, list[from]);
			list[to] = new Node(from, list[to]);
		}

		for (int i = 1; i < N+1; i++) {
			if(!visit[i]) {
				dfs(i);
				result++;
			}
		}
		System.out.println(result);
	}

	private static void dfs(int i) {
		visit[i] = true;

		for (Node temp = list[i]; temp != null;temp = temp.link) {
			if(!visit[temp.num]) {
				dfs(temp.num);
			}
		}
	}
}
