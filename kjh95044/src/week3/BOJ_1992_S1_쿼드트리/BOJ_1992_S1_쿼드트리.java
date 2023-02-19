package week3.BOJ_1992_S1_쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992_S1_쿼드트리 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] split = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

	
		zip(map);
		System.out.println(sb);
	}

	private static void zip(int[][] map) {

		

		/*
		 * 2 11 11 입력시 1이 나와야하는데 (1111)이 나왔다.
		 * 처음부터 값들이 모두 같을 때는 따로 처리를 해줬다.
		 */
		boolean canPrint = true;
		int start = map[0][0];
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]!=start) {
					canPrint = false;
					break;
				}
			}
		}
		if(canPrint) {
			sb.append(start);
			return;
		}

		int mid = map.length / 2;
		int[][] newMap = new int[mid][mid];
		
		int x, y;

		sb.append("(");
		// 왼쪽 위
		canPrint = true;
		x = 0;
		for (int i = 0; i < mid; i++) {
			y = 0;
			for (int j = 0; j < mid; j++) {
				newMap[x][y++] = map[i][j];
				if (newMap[0][0] != map[i][j]) {
					canPrint = false;
				}
			}
			x++;
		}

		if (canPrint) {
			sb.append(newMap[0][0]);
		} else {
			zip(newMap);
		}

		// 오른쪽 위
		canPrint = true;
		x = 0;
		for (int i = 0; i < mid; i++) {
			y = 0;
			for (int j = mid; j < map[0].length; j++) {
				newMap[x][y++] = map[i][j];
				if (newMap[0][0] != map[i][j]) {
					canPrint = false;
				}
			}
			x++;
		}

		if (canPrint) {
			sb.append(newMap[0][0]);
		} else {
			zip(newMap);
		}

		// 왼쪽 아래
		canPrint = true;
		x = 0;
		for (int i = mid; i < map.length; i++) {
			y = 0;
			for (int j = 0; j < mid; j++) {
				newMap[x][y++] = map[i][j];
				if (newMap[0][0] != map[i][j]) {
					canPrint = false;
				}
			}
			x++;
		}

		if (canPrint) {
			sb.append(newMap[0][0]);
		} else {
			zip(newMap);
		}

		// 오른쪽 아래
		canPrint = true;
		x = 0;
		for (int i = mid; i < map.length; i++) {
			y = 0;
			for (int j = mid; j < map.length; j++) {
				newMap[x][y++] = map[i][j];
				if (newMap[0][0] != map[i][j]) {
					canPrint = false;
				}
			}
			x++;
		}

		if (canPrint) {
			sb.append(newMap[0][0]);
		} else {

			zip(newMap);

		}
		sb.append(")");
		
	}
}