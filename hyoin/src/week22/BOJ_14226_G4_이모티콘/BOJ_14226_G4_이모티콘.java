import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14226_G4_이모티콘 {

	public static int S;

	public static class Node {
		int emoticon;
		int clipboard;
		int depth;

		public Node(int emoticon, int clipboard, int depth) {
			super();
			this.emoticon = emoticon;
			this.clipboard = clipboard;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [emoticon=" + emoticon + ", clipboard=" + clipboard + ", depth=" + depth + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		S = Integer.parseInt(st.nextToken());

		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[2001][2001];
		queue.offer(new Node(1, 0, 0));
		isVisited[1][0] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				int curEmo = cur.emoticon;
				int curClip = cur.clipboard;
				int depth = cur.depth;

				if (curEmo == S) {
					return depth;
				}

				// 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
				if (!isVisited[curEmo][curEmo]) {
					queue.offer(new Node(curEmo, curEmo, depth + 1));
					isVisited[curEmo][curEmo] = true;
				}

				// 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
				if (curEmo + curClip < 2000) { // S의 범위인 1000의 2배를 넘지 않을 때
					if (!isVisited[curEmo + curClip][curClip] && curClip != 0) {
						queue.offer(new Node(curEmo + curClip, curClip, depth + 1));
						isVisited[curEmo + curClip][curClip] = true;
					}
				}

				// 3. 화면에 있는 이모티콘 중 하나를 삭제
				if (curEmo != 0) {
					if (!isVisited[curEmo - 1][curClip]) {
						queue.offer(new Node(curEmo - 1, curClip, depth + 1));
						isVisited[curEmo - 1][curClip] = true;
					}
				}

			}
		}

		return -1;
	}
}
