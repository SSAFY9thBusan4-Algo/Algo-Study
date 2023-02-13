package week2.BOJ_7576_G5_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int M;// 가로 = 열
	private static int N;// 세로 = 행
	private static int[][] map;
	private static int[] dx = { 1, -1, 0, 0 }; // 상하좌우
	private static int[] dy = { 0, 0, -1, 1 };
	private static int count; // 익지않은 토마토의 개수
	private static ArrayList<Node> startXY = new ArrayList<>(); // 익은 토마토의 좌표를 담을 list

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 가로 = 열
		N = Integer.parseInt(st.nextToken()); // 세로 = 행
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) { // 익지 않은 토마토들 계산
					count++;
				}
				if (map[i][j] == 1) { // 익은 토마토들을 list에 담음
					startXY.add(new Node(i, j, 0));
				}
			}
		}

		/*
		 * 풀이 시작
		 */
		int result = bfs();
		System.out.println(result);
	}

	/*
	 * 토마토는 Node에 담아 x,y좌표와 해당 토마토 위치에서의 최소일수인 count를 가져옴
	 * 시작할 때 익은 토마토가 여러개일 때 동시에 탐색을 시작해야하므로 queue에 제일 먼저 담음 -> queue에는 익은 토마토가 들어감
	 * 익은 토마토(1)부터 상하좌우순으로 익지 않은 토마토(0) 탐색
	 * 익지 않은 토마토를 모두 발견하고(count가 0일 때) 익은 토마토를 모두 탐색하면 종료
	 */
	public static int bfs() {
		Queue<Node> queue = new ArrayDeque<>();// 익은 토마토를 담을 queue
		for (Node startxy : startXY) { // 입력에서부터 존재했던 익은 토마토들을 queue에 담음
			queue.offer(startxy);
		}

		while (!queue.isEmpty()) {// 익은 토마토 주위를 모두 탐색할 때 까지
			Node node = queue.poll();
			// 토마토가 모두 익었을 때
			// 익지 않은 토마토(0)를 모두 발견하고 익은 토마토(1)를 모두 탐색했을 때 해당 노드의 최소 일수가 정답
			if (count == 0 && queue.isEmpty()) {
				return node.cost;
			}

			// 익은 토마토(1)기준 상하좌우 탐색
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					// 익지 않은 토마토(0) 발견 시 count감소 후 익은 토마토(1)로 변경
					// 이제 익은 토마토(1)가 됐으니 그 전의 토마토의 최소일수+1을 해서 queue에 담음 -> 최소일수 업데이트
					if (map[nx][ny] == 0) {
						count--;
						map[nx][ny] = 1;
						queue.offer(new Node(nx, ny, node.cost + 1));
					}
				}
			}
		}
		// 토마토가 모두 익지 못했을 때
		return -1;
	}

	// 토마토의 x,y좌표와 최소일수
	public static class Node {
		int x; // x좌표
		int y; // y좌표
		int cost; // 해당 좌표에서의 최소일수

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

}
