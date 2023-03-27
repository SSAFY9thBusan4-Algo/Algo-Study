import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M, result;
	static char[][] map;
	static boolean[][] visited;
	static String keys;
	static HashMap<Character, List<Point>> lockedDoors;

	static int[][] delta = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result = 0;

			keys = "";

			map = new char[N][M];
			visited = new boolean[N][M];
			lockedDoors = new HashMap<>();

			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = input.charAt(j);
				}
			}

			String keyInput = br.readLine();
			if (!keyInput.equals("0")) {
				keys = keyInput;
			}
			
			// 
			// 

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (i >= 1 && i < N - 1 && j >= 1 && j < M - 1) {
						continue;
					}
					char now = map[i][j];
					
					if(visited[i][j] || now == '*') {
						continue;
					}

					// .일때만 체크가 아니라 달러나 문이나 열쇠 일때도 체크하고 넘겨야한다.
					
					if (now == '.') {
						bfs(new Point(i, j));
					}
					
					if(now == '$') {
						result++;
						map[i][j] = '.'; // 빈공간으로 변경
						bfs(new Point(i,j));
					}
					
					else if (Character.isUpperCase(now)) { // 문을 발견하면 열쇠로 열수 있는지 확인
						Character key = Character.toLowerCase(now); // 열쇠 확인 하기 위해 소문자로 변경

						if (keys.contains(key.toString())) { // 열쇠 있으면 이동
							map[i][j] = '.'; // 빈공간으로 변경
							bfs(new Point(i,j));
						} else { // 열쇠 없으면 나중에 열쇠 찾았을때 다시 확인 하기 위해 저장 해놓는다.
							List<Point> pList;
							if (lockedDoors.containsKey(now)) {
								pList = lockedDoors.get(now);
							} else {
								pList = new ArrayList<>();
							}
							pList.add(new Point(i, j));
							lockedDoors.put(now, pList);
						}
					} else if (Character.isLowerCase(now)) { // 열쇠 발견하면 열쇠 넣고, 열쇠가 없어서 못열던 문 다시 확인
						keys += now;
						List<Point> pList = lockedDoors.getOrDefault(now, null);
						if(pList!=null) {
							for (Point point : pList) {
								int x = point.x;
								int y = point.y;
								map[x][y] = '.'; // 빈공간으로 변경
								bfs(new Point(i,j));
							}
						}
					}
				}
			}
			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs(Point p) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(p);
		visited[p.x][p.y] = true;

		while (!queue.isEmpty()) {
			Point tempP = queue.poll();

			// 사방탐색
			for (int d = 0; d < 4; d++) {
				int nx = tempP.x + delta[d][0];
				int ny = tempP.y + delta[d][1];
				

				if (!(nx >= 0 && nx < N && ny >= 0 && ny < M)) { // 범위 밖이면
					continue;
				}
				
				char nextBuilding = map[nx][ny];

				if (visited[nx][ny] || nextBuilding == '*') { // 방문했거나, 벽이면 pass
					continue;
				}

				if (nextBuilding == '.') { // 빈공간이면
					visited[nx][ny] = true;
					queue.offer(new Point(nx, ny));
				}

				else if (nextBuilding == '$') { // 문서 찾음
					result++;
					map[nx][ny] = '.'; // 빈공간으로 변경
					visited[nx][ny] = true;
					queue.offer(new Point(nx, ny));
				}

				else if (Character.isUpperCase(nextBuilding)) { // 문을 발견하면 열쇠로 열수 있는지 확인
					Character key = Character.toLowerCase(nextBuilding); // 열쇠 확인 하기 위해 소문자로 변경

					if (keys.contains(key.toString())) { // 열쇠 있으면 이동
						map[nx][ny] = '.'; // 빈공간으로 변경
						visited[nx][ny] = true;
						queue.offer(new Point(nx, ny));
					} else { // 열쇠 없으면 나중에 열쇠 찾았을때 다시 확인 하기 위해 저장 해놓는다.
						List<Point> pList;
						if (lockedDoors.containsKey(nextBuilding)) {
							pList = lockedDoors.get(nextBuilding);
						} else {
							pList = new ArrayList<>();
						}
						pList.add(new Point(nx, ny));
						lockedDoors.put(nextBuilding, pList);
					}
				} else if (Character.isLowerCase(nextBuilding)) { // 열쇠 발견하면 열쇠 넣고, 열쇠가 없어서 못열던 문 다시 확인
					keys += nextBuilding;
					map[nx][ny] = '.'; // 빈공간으로 변경
					visited[nx][ny] = true;
					queue.offer(new Point(nx, ny));
					
					Character door = Character.toUpperCase(nextBuilding);
					List<Point> pList = lockedDoors.getOrDefault(door, null);
					if(pList!=null) {
						for (Point point : pList) {
							int x = point.x;
							int y = point.y;
							map[x][y] = '.'; // 빈공간으로 변경
							visited[x][y] = true;
							queue.offer(new Point(x, y));
						}
					}
					
				}

			}
		}
	}
	
	

}
