package week8.BOJ_9328_G1_열쇠;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9328_G1_열쇠 {

	public static int h;
	public static int w;
	public static char[][] map;
	public static boolean[][] isVisited;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static Map<Character, List<int[]>> keyDoorXY; // key: 키와 문의 알파벳, value: 해당 알파벳들의 xy좌표 배열
	public static Queue<int[]> searchXY; // 다음에 탐색을 시작할 좌표들을 담은 리스트
	public static boolean isOpenDoor; // 탐색 도중 열쇠를 찾아서 문을 열었는지(종료조건) 
	public static int result;

	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(in.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h][w];
			keyDoorXY = new HashMap<>();
			searchXY = new ArrayDeque<>();

			List<Character> searchAlpabet = new ArrayList<>(); // 가장자리에 있는 key(알파벳 소문자)들을 담는 리스트
			result = 0;

			for (int i = 0; i < h; i++) {
				map[i] = in.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (Character.isAlphabetic(map[i][j])) { // 키와 문일 때
						if (!keyDoorXY.containsKey(map[i][j])) { // 키가 존재하지 않을 때 좌표 list 선언
							keyDoorXY.put(map[i][j], new ArrayList<>());
						}
						keyDoorXY.get(map[i][j]).add(new int[] { i, j }); // 해당 키에 좌표 추가
					}

					if (i == 0 || i == h - 1 || j == 0 || j == w - 1) { // 가장자리일 때
						// 가장자리가 ., 소문자(key), $ 일 때 탐색시작 가능
						if (map[i][j] == '.') {
							searchXY.add(new int[] { i, j });
						} else if (Character.isLowerCase(map[i][j])) {
							searchXY.add(new int[] { i, j });
							searchAlpabet.add(Character.toUpperCase(map[i][j]));
							map[i][j] = '.';
						} else if (map[i][j] == '$') {
							searchXY.add(new int[] { i, j });
							map[i][j] = '.';
							result++;
						}
					}
				}
			}

			// 입력 가장자리에 key(알파벳 소문자)가 존재할 때 탐색 가능 & 문을 열어야함
			for (int i = 0; i < searchAlpabet.size(); i++) {
				openDoor(searchAlpabet.get(i));
			}

			// 상근이가 가진 키로 열수 있는 문 열기
			String haveKey = in.readLine();
			if (!haveKey.equals("0")) {
				char[] keys = haveKey.toCharArray();
				for (int i = 0; i < keys.length; i++) {
					// map상에 존재하는 똑같은 key들을 중복체크하지 않게 .으로 변경
					modifyDuplicateKey(keys[i]);

					// 상근이가 가진 키로 열수 있는 문 열기
					openDoor(Character.toUpperCase(keys[i]));
				}
			}

			isOpenDoor=true;
			isVisited = new boolean[h][w];
			while (true) {
				int qSize = searchXY.size();
				if (!isOpenDoor) {// 이전 탐색 도중에 열쇠를 찾아서 문을 연적이 없으면 더이상 이동이 불가능하므로 종료
					break;
				}
				
				isOpenDoor=false;
				for (int i = 0; i < qSize; i++) {
					int[] XY = searchXY.poll(); // 탐색을 시작할 좌표
					bfs(XY[0], XY[1]);
				}
			}

			sb.append(result).append("\n");

		}

		System.out.println(sb);

	}

	// map상에 존재하는 똑같은 key들을 중복체크하지 않게 .으로 변경
	private static void modifyDuplicateKey(char key) {
		if (keyDoorXY.containsKey(key)) {
			List<int[]> keyList = keyDoorXY.get(key);
			for (int j = 0; j < keyList.size(); j++) {
				int[] keyXY = keyList.get(j);
				map[keyXY[0]][keyXY[1]] = '.';
			}
		}		
	}

	// alphabet에 해당하는 모든 문 열기
	private static void openDoor(char alphabet) {
		if (keyDoorXY.containsKey(alphabet)) { // alphabet이라는 문이 존재할 때
			List<int[]> doorList = keyDoorXY.get(alphabet); // 모든 문의 좌표 가져오기
			for (int k = 0; k < doorList.size(); k++) {
				int[] doorXY = doorList.get(k);
				map[doorXY[0]][doorXY[1]] = '.'; // 문 열기
				if (doorXY[0] == 0 || doorXY[0] == h - 1 || doorXY[1] == 0 || doorXY[1] == w - 1) { // 문이 가장자리에 있을 때 탐색 시작이 가능하므로 탐색할 리스트에 추가
					searchXY.offer(new int[] { doorXY[0], doorXY[1] });
				}
			}
		}
	}

	private static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		isVisited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] curXY = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = curXY[0] + dx[i];
				int ny = curXY[1] + dy[i];

				if (nx >= 0 && nx < h && ny >= 0 && ny < w && !isVisited[nx][ny]) {
					// 다음값이 ., $, 소문자 이면 이동 가능
					// 다음값이 대문자이면 나중에 문이 열릴지도 모르니 현재 좌표를 다음탐색리스트(searchXY)에 추가
					if (map[nx][ny] == '.') {
						isVisited[nx][ny] = true;
						queue.offer(new int[] { nx, ny });
					} else if (map[nx][ny] == '$') {
						result++;
						isVisited[nx][ny] = true;
						queue.offer(new int[] { nx, ny });
						map[nx][ny] = '.';
					} else if (Character.isLowerCase(map[nx][ny])) { // 다음값이 소문자일 때 대문자 알파벳 문열기
						isVisited[nx][ny] = true;
						queue.offer(new int[] { nx, ny });

						isOpenDoor=true;
						char door = Character.toUpperCase(map[nx][ny]);
						openDoor(door);
						map[nx][ny]='.';
					} else if (Character.isUpperCase(map[nx][ny])) {// 다음값이 대문자일 때 현재 좌표를 다음탐색리스트에 추가
						searchXY.offer(new int[] { curXY[0], curXY[1] });
					}
				}
			}
		}

	}
}

