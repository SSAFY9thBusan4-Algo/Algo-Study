import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, K, result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		// 수빈이의 위치가 x일때 걷는다면 1초후에 x-1 또는 x+1로 이동
		// 순간이동을 하는 경우에는 0초후에 2*X의 위치로 이동하게 된다.

		bfs();

	}

	static class Node {
		int v;
		int time;

		public Node(int v, int time) {
			super();
			this.v = v;
			this.time = time;
		}
	}

	private static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(N, 0));
		boolean[] visited = new boolean[100001];
		visited[N] = true;

		while (!queue.isEmpty()) {

			int size = queue.size();

			while (size-- > 0) {
				Node now = queue.poll();
				if (now.v == K) {
					System.out.println(now.time);
					return;
				}
				
				Node[] nodes = new Node[3];
				nodes[0] = new Node(now.v * 2, now.time);
				nodes[1] = new Node(now.v - 1, now.time + 1);
				nodes[2] = new Node(now.v + 1, now.time + 1);
				
//				이렇게 하니까 틀렸다... 순서가 중요하다.
//				nodes[0] = new Node(now.v - 1, now.time + 1);
//				nodes[1] = new Node(now.v + 1, now.time + 1);
//				nodes[2] = new Node(now.v * 2, now.time);
				
				
				for(int i=0; i<3; i++) {
					if(0<= nodes[i].v && nodes[i].v <= 100000 && !visited[nodes[i].v]) {
						queue.offer(nodes[i]);
						visited[nodes[i].v] = true;
					}
				}

			}

		}
	}
}
