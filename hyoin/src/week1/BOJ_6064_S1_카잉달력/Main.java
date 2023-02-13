package week1.BOJ_6064_S1_카잉달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 최대공약수 구하기
	public static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());


			int max = M * N / gcd(M, N); // 최소공배수

			
			int year = x;

			while (true) {
				if (year > max) { // 값이 유효하지 않을 때
					year = -1;
					break;
				}
				if (y == year % N || (year % N==0 && y==N)) // year과 y의 연도가 일치할 때
					break;
				else // 연도가 일치하지 않을 때
					year+=M;
			}

			System.out.println(year);
		}
		
	}

}
