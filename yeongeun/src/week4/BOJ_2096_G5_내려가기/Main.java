package src.week4.BOJ_2096_G5_내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력파일 객체화
		int N = Integer.parseInt(br.readLine()); //N(1 ≤ N ≤ 100,000)
		int[][] map = new int[N][3];
		for(int i = 0 ; i < N ; i++ ) {	// 숫자는 0부터 9까지 이므로
			String input = br.readLine();
			map[i][0] = input.charAt(0) - '0'; // 자리수로 잘라서 넣음.
			map[i][1] = input.charAt(2) - '0';
			map[i][2] = input.charAt(4) - '0';
		}
		// =======================================
		// solve

		// DP
		int[] smalldp = new int[3];
		int[] bigdp = new int[3];
		for(int i = 0 ; i < 3; i++) {
			smalldp[i] = map[0][i];
			bigdp[i] = map[0][i];
		}
		int pre0, pre1, pre2;
		// 한 줄씩 내려가면서 경우의 수 저장
		for(int i = 1 ; i < N; i++) {
			
			// 최대
			pre0 = bigdp[0];
			pre1 = bigdp[1];
			pre2 = bigdp[2];
			// 1. 0에 들어갈 수 있는 경우 - 0, 1
			int max = Math.max(pre0, pre1);
			bigdp[0] = max + map[i][0];

			// 2. 1에 들어갈 수 있는 경우 - 0,1,2
			max = Math.max(max, pre2);
			bigdp[1] = max + map[i][1];

			// 3. 2에 들어갈 수 있는 경우 - 1,2
			max = Math.max(pre1, pre2);
			bigdp[2] = max + map[i][2];
			

			// 최소
			pre0 = smalldp[0];
			pre1 = smalldp[1];
			pre2 = smalldp[2];
			// 1. 0에 들어갈 수 있는 경우 - 0, 1
			int min = Math.min(pre0, pre1);
			smalldp[0] = min + map[i][0];

			// 2. 1에 들어갈 수 있는 경우 - 0,1,2
			min = Math.min(min, pre2);
			smalldp[1] = min + map[i][1];

			// 3. 2에 들어갈 수 있는 경우 - 1,2
			min = Math.min(pre1, pre2);
			smalldp[2] = min + map[i][2];
			

		}

		int max = Math.max(bigdp[0], bigdp[1]);
		max = Math.max(max, bigdp[2]);
		int min = Math.min(smalldp[0], smalldp[1]);
		min = Math.min(min, smalldp[2]);
		
		sb.append(max).append(" ").append(min);
		
		// 출력
		System.out.println(sb);
	}
}