package week1.BOJ_3085_S3_사탕게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static String map[][];
	public static int[] dx = { 0, 1 }; // 우, 하
	public static int[] dy = { 1, 0 };
	public static int max = 0;

	// 사탕의 최대 개수 찾는 메소드
	public static void Max() {
		for (int i = 0; i < N; i++) {
			int len_x = 0;
			int len_y = 0;
			for (int j = 0; j < N - 1; j++) {
				// 행검사
				if (map[i][j].equals(map[i][j + 1])) {
					len_x++;
				} else {
					max = Math.max(max, len_x);
					len_x = 0;
				}
				// 열검사
				if (map[j][i].equals(map[j + 1][i])) {
					len_y++;
				} else {
					max = Math.max(max, len_y);
					len_y = 0;
				}
			}
			// 모든 행/열이 같을때 최대값 확인
			max = Math.max(max, len_x);
			max = Math.max(max, len_y);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new String[N][N];

		for (int i = 0; i < N; i++) {
			String arr[] = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = arr[j];
			}
		}

		// 교환전 최대값 찾기
		Max();

		// 사탕 교환 시작
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < dx.length; k++) {
					int n_dx = i+dx[k];
					int n_dy = j+dy[k];
					if (n_dx >= 0 && n_dx < N && n_dy >= 0 && n_dy < N) {
						if(!map[i][j].equals(map[n_dx][n_dy])) {
							// 자리 switch
							String temp = map[i][j]; 
							map[i][j] = map[n_dx][n_dy]; 
							map[n_dx][n_dy] = temp; 
							Max();
							// 자리 원상복구
							map[n_dx][n_dy] = map[i][j]; 
							map[i][j] = temp;	
						}
					}
				}
			}

		}

		System.out.println(max + 1);
	}

}
