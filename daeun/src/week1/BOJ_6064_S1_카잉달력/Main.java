package week1.BOJ_6064_S1_카잉달력;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int M = 0, N = 0, x = 0, y = 0;
		for (int t = 0; t < T; t++) {
			String[] split = br.readLine().split(" ");
			M = Integer.parseInt(split[0]);
			N = Integer.parseInt(split[1]);
			x = Integer.parseInt(split[2])-1;
			y = Integer.parseInt(split[3])-1;
			int m = M;
			int n = N;
			int temp;
			
			while (m > 0) {
				temp = n;
				n = m;
				m = temp % m;
			}

			temp = M * N / n; 
			boolean flag = false;
			for(int i=x;i<temp;i+=M) {
				if (i%N == y) {
					flag = true;
					System.out.println(i+1);
					break;
				}
			}
			
			if (!flag) {
				System.out.println(-1);
			}
		}

		System.out.println(sb);
	}
}