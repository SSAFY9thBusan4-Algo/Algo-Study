package week5.SWEA_2383_점심식사시간;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2383_점심식사시간 {

	private static int N;
	private static List<int[]> peopleList; // 사람 위치 정보를 저장하는 배열
	private static List<int[]> stairs; // 계단 위치 정보를 저장하는 배열
	private static int[][] map;

	private static int result;
	private static boolean[] isSelected;

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/2383_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");

			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			peopleList = new ArrayList<>();
			stairs = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						peopleList.add(new int[] { i, j });
					} else if (map[i][j] > 1) {
						stairs.add(new int[] { i, j });
					}
				}
			}

			result = Integer.MAX_VALUE;
			isSelected = new boolean[peopleList.size()];

			subSet(0);

			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static void subSet(int cnt) {

		// 기저부분
		if (cnt == peopleList.size()) {
			List<int[]> list1 = new ArrayList<>();
			List<int[]> list2 = new ArrayList<>();

			for (int i = 0; i < peopleList.size(); i++) {
				if (isSelected[i]) {
					list1.add(peopleList.get(i));
				} else {
					list2.add(peopleList.get(i));
				}
			}

			// 1번 계단에서 소요되는 시간
			int time1 = timeCheck(0, list1);

			// 2번 계단에서 소요되는 시간
			int time2 = timeCheck(1, list2);

			int time = Math.max(time1, time2);
			result = Math.min(result, time);

			return;
		}

		// 유도부분
		isSelected[cnt] = true;
		subSet(cnt + 1);
		isSelected[cnt] = false;
		subSet(cnt + 1);
	}

	private static int timeCheck(int no, List<int[]> list) {
		int stairX = stairs.get(no)[0];
		int stairY = stairs.get(no)[1];
		int[] distance = new int[list.size()];
		// 해당 계단과 사람들 사이의 거리 저장
		for (int i = 0; i < distance.length; i++) {
			int peopleX = list.get(i)[0];
			int peopleY = list.get(i)[1];
			distance[i] = Math.abs(peopleX - stairX) + Math.abs(peopleY - stairY);
		}
		Arrays.sort(distance); // 빨리 도착한 순서대로 계단을 내려가야하므로 정렬사용

		int downstair_time = map[stairX][stairY]; // 계단을 내려가는데 소요되는 시간
		int time = 1; // 계단을 모두 내려오는데 걸리는 시간 -> 0일 때 1초 대기해야하니까 time을 1로 설정
		int start = 0;
		// distance가 0이면 계단에 도착
		// 0일 때 계단을 내려가므로 -downstair_time일 때 계단을 모두 내려감
		while (true) {
			if (start == distance.length) { // 모든 사람이 계단을 내려왔을 때 종료
				break;
			}
			
			// 계단 내려가기 시작
			int temp_start = start;
			for (int i = start; i < start + 3; i++) { // start사람부터 3명의 사람만 계단 내려감
				if (i < distance.length) {
					if (distance[i] <= 0) { // 계단에 도착해서 대기하거나 or 내려가고 있을 때
						distance[i] -= 1;
					}
					if (distance[i] == -downstair_time) { // 계단을 모두 내려갔을 때
						// 대기하던 다음 사람부터 계단 내려가기 시작
						temp_start = i + 1;
					}
				}
			}

			start = temp_start;// 계단 내려가는 사람의 시작 index 업데이트
			
			// 아직 계단에 도착하지 못한 사람들 계단 내려감
			for (int i = 0; i < distance.length; i++) {
				if (distance[i] > 0)
					distance[i] -= 1;
			}
			time++;
		}

		return time;
	}

}
