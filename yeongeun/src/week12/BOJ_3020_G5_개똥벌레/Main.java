package src.week12.BOJ_3020_G5_개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int H = Integer.parseInt(in[1]);
		
		// 각 높이별 장애물의 개수 세기
		int[] suc = new int[H+1];
		int[] jong = new int[H+1];
		int n;
		for(int i = 0 ; i < N; ++i) {
			n = Integer.parseInt(br.readLine());
			
			if(i%2 == 0) ++suc[n];	// 석순
			else ++jong[n];			// 종유석
		}

		// 누적합
		for(int i = H-1 ; i > 0; --i) {
			jong[i-1] += jong[i];
			suc[i-1] += suc[i];
		}
		
		// 장애물의 최소값과 구간 개수 구하기
		int min = Integer.MAX_VALUE;
		int count = 0;
		for(int i = 1 ; i <= H ; ++i) {
			int s = suc[i] + jong[H-i+1];
			if(min > s) {
				min = s;
				count = 1;
			}
			else if(min == s) ++count;
		}
		
		System.out.println(min + " " + count);
	}
}
