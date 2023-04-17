package week11.BOJ_21611_G1_마법사상어와블리자드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_21611_G1_마법사상어와블리자드 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] magic_dx = { -1, 1, 0, 0 }; // 상하좌우
	private static int[] magic_dy = { 0, 0, -1, 1 };
	private static int[] dx = { 0, 1, 0, -1 }; // 좌하우상
	private static int[] dy = { -1, 0, 1, 0 };
	private static Node sharkXY; // 상어의 위치
	private static Node startXY; // 1번 좌표 위치
	private static int bomb1;
	private static int bomb2;
	private static int bomb3;

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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		startXY = new Node((N + 1) / 2, (N + 1) / 2 - 1);
		sharkXY = new Node((N + 1) / 2, (N + 1) / 2);
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			// 1. 마법 시전
			magic(d, s);
//			System.out.println("마법 시전 후");
//			print();

			// 2. 구슬 빈칸 이동
			moveBlank();
//			System.out.println("빈칸이동");
//			print();

			// 3. 구슬 폭발
			bomb();
//			System.out.println("구슬 폭발");
//			print();

			// 4. 구슬 그룹화
			group();
//			System.out.println("구슬 그룹화");
//			print();
		}

		System.out.println(bomb1 + 2 * bomb2 + 3 * bomb3);
	}

	// 1. 블리자드 마법 실행
	private static void magic(int d, int s) {
		int nx = sharkXY.x;
		int ny = sharkXY.y;
		for (int i = 0; i < s; i++) {
			nx += magic_dx[d - 1];
			ny += magic_dy[d - 1];

			if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
				map[nx][ny] = -1; // 마법을 맞은 부분 구슬 파괴(-1로 설정)
			}
		}
	}

	// 2. 구슬 빈칸 이동
	private static void moveBlank() {
		// 좌하우상
		int d = 0; // 현재 방향
		int cnt = 1; // 현재 방향으로 몇번 움직여야하는지
		int nx = sharkXY.x;
		int ny = sharkXY.y;

		Queue<Integer> queue = new ArrayDeque<>();
		// 현재 존재하는 모든 구슬 queue에 담기
		/*
		 * 달팽이처럼 돌아가는 원리 시작지점 : 상어의 위치 1. 방향(d) : 좌, 움직이는 개수(cnt) : 1 2. 방향 : 하, 움직이는 개수
		 * : 1 3. 방향 : 우, 움직이는 개수 : 2 4. 방향 : 상, 움직이는 개수 : 2 5. 방향 : 좌, 움직이는 개수 : 3 6.
		 * 방향 : 하, 움직이는 개수 : 3 => 방향은 좌하우상으로 반복되면서 움직임 & 움직이는 개수는 방향이 2번 바뀌면 +1씩 늘어남
		 */
		out: while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < cnt; j++) { // 방향변환 주기(해당 반복문이 끝나면 방향 전환)
					nx += dx[d];
					ny += dy[d];

					if (nx < 1 || nx > N || ny < 1 || ny > N || map[nx][ny] == 0) { // 범위를 벗어나면 종료
						break out;
					}

					if (map[nx][ny] == -1) { // 마법으로 없어졌을 때
						map[nx][ny] = 0;
					} else if (map[nx][ny] > 0) { // 구슬이 존재할 때
						queue.offer(map[nx][ny]);
						map[nx][ny] = 0;
					}
				}
				d = (d + 1) % 4; // 방향 전환
			}
			cnt++; // 같은 방향으로 움직일 개수 +1
		}

		// 큐에 담은 구슬 다시 map에 넣기
		d = 0;
		cnt = 1;
		nx = sharkXY.x;
		ny = sharkXY.y;
		out: while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < cnt; j++) { // 방향변환 주기
					nx += dx[d];
					ny += dy[d];

					if (queue.isEmpty()) { // 큐가 비어있으면 종료
						break out;
					}
					map[nx][ny] = queue.poll();
				}
				d = (d + 1) % 4;
			}
			cnt++;
		}

	}

	// 3. 구슬 폭발
	private static void bomb() {
		outout: while (true) {
			int d = 0;
			int cnt = 1;
			int nx = sharkXY.x;
			int ny = sharkXY.y;

			Stack<Node> bombList = new Stack<>(); // 폭발할 구슬 담는 리스트

			int prev = -1; // 이전 구슬 번호
			int continue4 = 0; // 다른 구슬을 만나기전까지 연속된 구슬 개수
			// 연속으로 4개이상 연결된 폭발할 구슬 담기
			out: while (true) {
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < cnt; j++) { // 방향변환 주기
						nx += dx[d];
						ny += dy[d];

						if (nx < 1 || nx > N || ny < 1 || ny > N) { // 범위를 벗어나면 종료
							break out;
						}

						if (prev != map[nx][ny]) { // 구슬 번호가 다를 때(bomblist의 1~continue4만큼 연속된 구슬 존재)
							prev = map[nx][ny]; // 이전 구슬 번호 업데이트
							if (continue4 < 4) { // 구슬이 4개미만으로 연속되어있다면 폭발할 구슬 리스트에서 삭제
								for (int k = 0; k < continue4; k++) {
									bombList.pop();
								}
							}
							continue4 = 1; // 연속된 구슬 개수 초기화
						} 
						else { // 구슬 번호가 같다면 연속된 구슬 개수 +1
							continue4++;
						}

						if (map[nx][ny] == 0) { // 현재 좌표가 0이면 구슬이 더이상 없으므로 현재 반복문 종료
							break out;
						}
						bombList.add(new Node(nx, ny)); // 구슬 담기
					}
					d = (d + 1) % 4;
				}
				cnt++;
			}

			// 구슬 폭발
			int size = bombList.size();
			if (bombList.size() == 0) { // 더이상 폭발 할 구슬이 없으면 완전 종료
				break outout;
			}
			
			for (int i = 0; i < size; i++) {
				Node cur = bombList.pop();
				if (map[cur.x][cur.y] == 1) {
					bomb1++;
				} else if (map[cur.x][cur.y] == 2) {
					bomb2++;
				} else if (map[cur.x][cur.y] == 3) {
					bomb3++;
				}
				map[cur.x][cur.y] = -1; // 구슬 폭발
			}

			moveBlank(); // 빈칸 매우기
		}
	}

	// 4. 구슬 그룹화
	private static void group() {
		int d = 0;
		int cnt = 1;
		boolean[][] isVisited = new boolean[N + 1][N + 1];
		isVisited[sharkXY.x][sharkXY.y] = true;
		isVisited[startXY.x][startXY.y] = true;
		int nx = sharkXY.x;
		int ny = sharkXY.y;

		Queue<Integer> queue = new ArrayDeque<>(); // 구슬개수, 구슬번호를 담는 큐

		int prev = map[startXY.x][startXY.y];
		int continueCo = 1;

		// 구슬개수, 구슬번호 구하기(구슬 그룹화)
		out: while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < cnt; j++) { // 방향변환 주기
					nx += dx[d];
					ny += dy[d];

					if (nx < 1 || nx > N || ny < 1 || ny > N) { // 범위를 벗어나면 종료
						break out;
					}

					if (!isVisited[nx][ny]) {
						if (prev != map[nx][ny]) { // 구슬 번호가 다를 때 이전 구슬 그룹 저장
							queue.offer(continueCo);
							queue.offer(prev);
							prev = map[nx][ny];
							continueCo = 1;
						} 
						else { // 구슬 번호가 같을 때
							continueCo++;
						}
						
						if (map[nx][ny] == 0) {
							break out;
						}
						
						isVisited[nx][ny] = true;
						map[nx][ny] = 0;
					}
				}
				d = (d + 1) % 4;
			}
			cnt++;
		}

		// 그룹화 한 구슬을 map에 업데이트하기
		d = 0;
		cnt = 1;
		nx = sharkXY.x;
		ny = sharkXY.y;
		out: while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < cnt; j++) { // 방향변환 주기
					nx += dx[d];
					ny += dy[d];

					if (nx < 1 || nx > N || ny < 1 || ny > N) { // 범위를 벗어나면 종료
						break out;
					}

					if (queue.isEmpty()) {
						break out;
					}
					map[nx][ny] = queue.poll();
				}
				d = (d + 1) % 4;
			}
			cnt++;
		}
	}

	// 디버깅용 출력문
	private static void print() {
		for (int i = 1; i <= N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
}
