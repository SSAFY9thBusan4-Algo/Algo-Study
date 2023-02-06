package week1.BOJ_3085_S3_사탕게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static int max = 0;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = chars[j];
			}
		}

		// 풀이 시작

		// 오른쪽이랑 밑으로만 바꾸고 비교

		char tmp;

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				// 왼쪽 값과 오른쪽 값을 바꿈
				tmp = map[i][j - 1];
				map[i][j - 1] = map[i][j];
				map[i][j] = tmp;
				
				checkMax();
				
				tmp = map[i][j - 1];
				map[i][j - 1] = map[i][j];
				map[i][j] = tmp;

				// 위 값과 아래 값을 바꿈

				tmp = map[j - 1][i];
				map[j - 1][i] = map[j][i];
				map[j][i] = tmp;

				checkMax();
				
				tmp = map[j - 1][i];
				map[j - 1][i] = map[j][i];
				map[j][i] = tmp;
			}
		}

		System.out.println(max);
	}
	
	public static void printMap() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void checkMax() {
		
		for(int i=0; i<map.length; i++) {
			int cnt = 1;
			for(int j=1; j<map.length; j++) {
				if(map[i][j] == map[i][j-1]) {
					cnt++;
				}
				else {
					cnt = 1;
				}
				max = Math.max(cnt, max);
			}
		}
		
		for(int i=0; i<map.length; i++) {
			int cnt = 1;
			for(int j=1; j<map.length; j++) {
				if(map[j][i] == map[j-1][i]) {
					cnt++;
				}
				else {
					cnt = 1;
				}
				max = Math.max(cnt, max);
			}
		}
	}

}
