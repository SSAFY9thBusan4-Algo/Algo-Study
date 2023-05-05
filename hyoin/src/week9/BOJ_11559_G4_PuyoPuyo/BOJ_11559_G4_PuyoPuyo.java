package week9.BOJ_11559_G4_PuyoPuyo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BOJ_11559_G4_PuyoPuyo {
	public static char[][] map;
	public static boolean[][] isVisited;
	public static boolean isFind;
	public static int result;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			map[i] = in.readLine().toCharArray();
		}

		isVisited = new boolean[12][6];

		while (true) {
			isFind = false;
			isVisited = new boolean[12][6];
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (!isVisited[i][j] && map[i][j] != '.') { // 탐색하지 않았고 빈공간이 아닐 때
						bfs(i, j);
					}
				}
			}

			if (!isFind) { // 더이상 터질 수 있는 뿌요가 없을 때 종료
				break;
			}

			down(); // 터진 뿌요들 내리기
			result++;
		}

		System.out.println(result);
	}

	// 터진 뿌요들 내리기
	// 터진 뿌요들 때문에 뿌요 사이에 빈공간이 존재하므로 내려줌
	private static void down() {
		Stack<Character> stack;

		// 열별로 뿌요들을 stack에 넣고 마지막행부터 다시 뿌요들을 채움
		for (int i = 0; i < 6; i++) {
			stack = new Stack<>();
			for (int j = 0; j < 12; j++) { // 존재하는 뿌요들 다미
				if (map[j][i] != '.') {
					stack.push(map[j][i]);
				}
			}
			
			if (!stack.isEmpty()) {
				int size = stack.size();
				for (int j = 11; j > 11-size; j--) { // 내려야할 뿌요들을 밑에서부터 채움
					map[j][i] = stack.pop();
				}
				for (int j = 11-size; j >=0; j--) { // 나머지 공간을 빈공간으로 채움
					map[j][i]='.';
				}
			}
		}
	}

	private static void bfs(int x, int y) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(x, y));
		isVisited[x][y] = true;

		List<Node> popList = new ArrayList<>(); // 같은 색의 뿌요들 담는 리스트
		popList.add(new Node(x, y));
		while (!queue.isEmpty()) {
			Node curXY = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = curXY.x + dx[i];
				int ny = curXY.y + dy[i];

				if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !isVisited[nx][ny]) {
					if (map[nx][ny] == map[x][y]) { // 같은 색의 뿌요일 때
						popList.add(new Node(nx, ny));
						isVisited[nx][ny] = true;
						queue.offer(new Node(nx, ny));
					}
				}
			}
		}

		if (popList.size() >= 4) { // 같은 색의 뿌요들이 4개 이상일 때 터트리기(.으로 변경)
			for (int i = 0; i < popList.size(); i++) {
				isFind = true;
				map[popList.get(i).x][popList.get(i).y] = '.';
			}
		}
	}
}
