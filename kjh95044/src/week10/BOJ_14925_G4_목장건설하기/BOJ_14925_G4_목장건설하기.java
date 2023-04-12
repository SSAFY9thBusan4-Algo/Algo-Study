package com.jihong.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 목장건설하기 {
	static StringBuilder sb = new StringBuilder();
	static int[][] map, memo;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N][M];
		memo = new int[N][M];
		int max = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 해당 좌표가 0이면 아래를 수행 0이 아니면 0을 저장
		// 왼쪽, 왼쪽위, 위쪽을 계속 확인하면서 0이거나 범위 밖이면 1을 저장
		// 나머지는 3개중 min값에 +1 해주면서 저장해준다.
		// 그중에 max값이 최대 크기 변수가 된다.
		
		int[][] delta = {{0,-1},{-1,-1},{-1,0}};
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if(map[i][j] !=0) { // 0이 아니면 기본값 0 그대로 냅둠
					continue;
				}
				
				memo[i][j] = 99999; // min값 초기화
				
				for (int d = 0;  d< 3; d++) {
					
					int nx = i + delta[d][0];
					int ny = j + delta[d][1];
					
					if(!(nx>=0&&ny>=0)) {
						memo[i][j] = 0; // 아래에서 +1 해주기 때문에 0으로 셋팅
						break;
					}
					
					memo[i][j] = Math.min(memo[i][j], memo[nx][ny]); // 3방향중 min값을 저장
				}
				memo[i][j] +=1; // 저장된 min값 + 1
				max = Math.max(max, memo[i][j]); // max값 갱신
			}
		}

		System.out.println(max);
	}
}
