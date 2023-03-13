package week5.SWEA_2383_역테_점심식사시간;

import java.util.*;
import java.io.*;

public class SWEA_2383_역테_점심식사시간 {
	static StringBuilder sb = new StringBuilder();
	static Queue<Person>[] qs;
	static List<Person> persons;
	static Stair[] stairs;

	static boolean[] visited;
	static int N, T, result;

	static class Stair {
		int x, y, k;

		Stair(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}

	static class Person {
		int x, y;
		int stair;
		int arriveTime;
		int stairTime;

		Person(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// 계단과 사람 거리
		private void calArriveTime() {
			this.arriveTime = Math.abs(x - stairs[stair].x) + Math.abs(y - stairs[stair].y);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());

			persons = new ArrayList<>();
			qs = new LinkedList[2];
			qs[0] = new LinkedList<>();
			qs[1] = new LinkedList<>();
			stairs = new Stair[2];
			result = Integer.MAX_VALUE;

			int stairIdx = 0;

			for (int i = 1; i <= N; ++i) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; ++j) {
					int num = Integer.parseInt(st.nextToken());

					if (num == 0) {
						continue;
					} else if (num == 1) {
						// 1인 위치 저장
						persons.add(new Person(i, j));
					} else {
						// 두 계단 높이 저장
						stairs[stairIdx++] = new Stair(i, j, num);
					}
				}
			}

			dfs(0);

			sb.append(result);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void dfs(int cnt) {

		if (cnt == persons.size()) {
			visited = new boolean[persons.size()];
			result = Math.min(result, start());
			return;
		}

		// 첫번째 계단
		persons.get(cnt).stair = 0;
		persons.get(cnt).calArriveTime();
		dfs(cnt + 1);

		// 두번째 계단
		persons.get(cnt).stair = 1;
		persons.get(cnt).calArriveTime();
		dfs(cnt + 1);
	}

	private static int start() {
		int cnt = 0;
		int time = 1;

		// 계단 고른 persons 모두 탐색하여 최소값을 구한다.
		while (true) {
			// 계단 내려 가기
			for (Queue<Person> q : qs) {
				int size = q.size();

				for (int i = 0; i < size; ++i) {
					Person p = q.poll();
					Stair s = stairs[p.stair];

					if (p.stairTime + s.k <= time) {
						continue;
					}

					q.offer(p);
				}
			}

			if (cnt == persons.size() && qs[0].isEmpty() && qs[1].isEmpty()) {
				return time;
			}

			for (int i = 0; i < persons.size(); ++i) {
				if (visited[i])
					continue;

				Person p = persons.get(i);
				Queue<Person> q = qs[p.stair];

				if (p.arriveTime + 1 <= time && q.size() < 3) {
					p.stairTime = time;
					visited[i] = true;
					q.offer(p);
					cnt++;
				}
			}
			time++;
		}
	}

}
