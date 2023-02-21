package week3.BOJ_1992_S1_쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static char[][] map;// 입력 배열
	
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		compression(0, 0, N); // 시작위치 (X좌표, Y좌표), 한변의 길이
		
		System.out.println(sb);
	}

	// 분할 정복 -> 재귀
	// (startX, startY) : 정사각형의 왼쪽 위 꼭지점 좌표
	// n : 정사각형 한 변의 길이
	private static void compression(int startX, int startY, int n) {

		/*
		 * 기저부분
		 * 해당 사각형이 모두 같은 숫자로만 이루어져있을 때 재귀 종료
		 */
		char temp = map[startX][startY]; // 숫자 비교할 변수
		boolean check = true; // 모두 같은 숫자로 이루어져 있는지 확인하는 변수

		out:
		for(int i=startX; i<startX+n; i++) {
			for(int j=startY; j<startY+n; j++) {
				// 사각형이 다른 숫자로 이루어져있을 때
				if(map[i][j]!=temp) {
					check=false;
					break out;
				}
			}
		}

		// 같은 숫자로만 이루어져 있을 때 재귀 종료
		if(check) {
			sb.append(temp);
			return;
		}
		

		/*
		 * 유도부분
		 * 왼쪽위, 오른쪽위, 왼쪽아래, 오른쪽아래 순으로 다시 분할 탐색
		 */
		// 왼쪽위
		sb.append("(");
		compression(startX, startY, n/2);

		// 오른쪽위
		compression(startX, startY+n/2, n/2);

		// 왼쪽아래
		compression(startX+n/2, startY, n/2);

		// 오른쪽아래
		compression(startX+n/2, startY+n/2, n/2);
		sb.append(")");
	}
}
