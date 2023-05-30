package week16.BOJ_2166_G5_다각형의면적;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2166_G5_다각형의면적 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		long[] x = new long[N + 1];
		long[] y = new long[N + 1];

		// 신발끈의 공식 사용
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			x[i] = Long.parseLong(st.nextToken());
			y[i] = Long.parseLong(st.nextToken());
		}
		x[N] = x[0];
		y[N] = y[0];

		long sum1 = 0;
		long sum2 = 0;
		for (int i = 0; i < N; i++) {
			long num = x[i] * y[i + 1];
			sum1 += num;
		}

		for (int i = N; i > 0; i--) {
			long num = x[i] * y[i - 1];
			sum2 += num;
		}

		String result = String.format("%.1f", (Math.abs(sum1 - sum2) / 2.0));
		System.out.println(result);
	}
}
