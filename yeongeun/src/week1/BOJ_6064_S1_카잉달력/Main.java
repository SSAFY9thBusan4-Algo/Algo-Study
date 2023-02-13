package week1.BOJ_6064_S1_카잉달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int M, N, cx, cy;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");

			M = Integer.parseInt(input[0]);
			N = Integer.parseInt(input[1]);
			int x = Integer.parseInt(input[2]);
			int y = Integer.parseInt(input[3]);

			// solve

			int count = 1;
			cx = 1; // count x
			cy = 1; // count y

			int temp;	//현재 값에서 주기를 돌리기위해서 더할만큼 필요한 값

			while (true) {
				if ((x - cx) == (y - cy)) break;

				// x,y 중 더 M, N에 가까운것을 선택
				if(M-cx < N- cy) {	//x가 더 가까우면
					if (cx == 1) temp = M;
					else temp = M - cx + 1;
				}
				else {
					if (cy == 1) temp = N;
					else temp = N - cy + 1;
				}
				
				// 한 주기 돌기
				addNum(temp);
				count += temp;
				
				if ((x - cx) == (y - cy)) break;
				//System.out.println(count+" : "+cx+", "+cy+"--------"+temp);

				

				//전체 한바퀴 다 돌았는데도 없으니까
				if(cx == 1 && cy == 1) {
					x = 0;
					cx = 0;
					count = -1;
					break;
				}

			}
			count += Math.abs(x - cx);

			sb.append(count).append("\n");
		}

		System.out.println(sb);
	}
	
	private static void addNum(int num) {
		// cx, cy 에 num만큼 더한다.
		// M, N 을 넘으면 다시 1부터 시작하도록 설정.
		
		cx = (cx + num) % M;
		if(cx == 0) cx = M;
		
		cy = (cy + num) % N;
		if(cy == 0) cy = N;
	}

}
/*
+ 메모리 : 18500 KB
+ 시간 : 448 ms
*/