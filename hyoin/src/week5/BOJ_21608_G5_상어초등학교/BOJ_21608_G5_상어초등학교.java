package week5.BOJ_21608_G5_상어초등학교;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_21608_G5_상어초등학교 {

	// 결과를 한번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();

	public static class Node implements Comparable<Node> {
		int likeCount; // 주변에 좋아하는 학생 수
		int blankCount; // 주변에 비어있는 칸의 수
		int rowNo;
		int colNo;

		public Node(int likeCount, int blankCount, int rowNo, int colNo) {
			super();
			this.likeCount = likeCount;
			this.blankCount = blankCount;
			this.rowNo = rowNo;
			this.colNo = colNo;
		}

		// 자리 선정 기준
		// 1. 좋아하는 학생이 인접한 칸에 가장 많은 칸
		// 2. 인접한 칸 중에서 비어있는 칸이 가장 많은 칸
		// 3. 행의 번호가 가장 작은 칸
		// 4. 열의 번호가 가장 작은 칸
		@Override
		public int compareTo(Node o) {
			if (o.likeCount == this.likeCount) {
				if (o.blankCount == this.blankCount) {
					if (this.rowNo == o.rowNo) {
						return this.colNo - o.colNo;
					} else {
						return this.rowNo - o.rowNo;
					}
				} else {
					return o.blankCount - this.blankCount;
				}
			} else {
				return o.likeCount - this.likeCount;

			}

		}

		@Override
		public String toString() {
			return "Node [likeCount=" + likeCount + ", blankCount=" + blankCount + ", rowNo=" + rowNo + ", colNo="
					+ colNo + "]";
		}

	}

	public static void main(String[] args) throws Exception {

		/*
		 * 1. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		/*
		 * 2. 입력파일 객체화
		 */
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][N + 1];
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		// (학생번호, 좋아하는 학생 번호수)의 입력을 저장하는 hash map
		HashMap<Integer, int[]> students = new HashMap<>();
		for (int i = 0; i < Math.pow(N, 2); i++) {
			st = new StringTokenizer(in.readLine());
			int studentNo = Integer.parseInt(st.nextToken());
			int like[] = new int[4];
			for (int j = 0; j < 4; j++) {
				like[j] = Integer.parseInt(st.nextToken());
			}
			students.put(studentNo, like);

			/*
			 * 3. 알고리즘 풀기
			 */
			// NXN map을 탐색하면서 자리배치가 가능한 위치의 정보(주변의 좋아하는 사람 수, 비어있는 칸의 수, 행번호, 열번호)를 담아서 우선순위 큐에 저장
			// 자리선정 기준으로 자동 정렬해서 우선순위 큐에 담음
			PriorityQueue<Node> queue = new PriorityQueue<>();

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (map[r][c] == 0) { // 현재 칸이 비어있을 때
						int likeCount = 0;
						int blankCount = 0;
						for (int k = 0; k < 4; k++) { // 상하좌우 탐색
							int nx = r + dx[k];
							int ny = c + dy[k];
							if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
								if (map[nx][ny] == like[0] || map[nx][ny] == like[1] || map[nx][ny] == like[2]
										|| map[nx][ny] == like[3]) {
									likeCount++;
								} else if (map[nx][ny] == 0) {
									blankCount++;
								}
							}
						}
						// 현재 자리의 정보를 큐에 담음
						queue.offer(new Node(likeCount, blankCount, r, c));
					}
				}
			}

			Node seat = queue.poll(); // 제일 앞의 자리가 원하는 자리
			map[seat.rowNo][seat.colNo] = studentNo; // 자리배치
		}

		// 만족도 구하기
		int result = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int studentNo = map[r][c];
				int[] like = students.get(studentNo);
				int count = 0;
				for (int k = 0; k < 4; k++) {
					int nx = r + dx[k];
					int ny = c + dy[k];
					if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
						if (map[nx][ny] == like[0] || map[nx][ny] == like[1] || map[nx][ny] == like[2]
								|| map[nx][ny] == like[3]) {
							count++;
						}
					}
				}

				if (count == 1) {
					result += 1;
				} else if (count == 2) {
					result += 10;
				} else if (count == 3) {
					result += 100;
				} else if (count == 4) {
					result += 1000;
				}
			}
		}

		sb.append(result);

		/*
		 * 4. 정답 출력
		 */
		System.out.println(sb);
	}
}
