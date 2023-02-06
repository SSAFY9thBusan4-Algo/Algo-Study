package week1.BOJ_1476_S5_날짜계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int E = Integer.parseInt(input[0]); // 15
		int S = Integer.parseInt(input[1]); // 28
		int M = Integer.parseInt(input[2]); // 19

		int count = 1;
		while (!(E == 1 && S == 1 && M == 1)) {
			count++;

			if (--E == 0)
				E = 15;
			if (--S == 0)
				S = 28;
			if (--M == 0)
				M = 19;

		}


		System.out.println(count);

	}
}
