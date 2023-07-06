package week20.BOJ_1188_G4_음식평론가;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1188_G4_음식평론가 {
	
	public static int N; // 소세지 수
	public static int M; // 평론가 수

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		System.out.println(M - gcd(N,M));
	}

	private static int gcd(int n, int m) {// 최대공약수
		while(m>0) {
			int temp = n;
			n=m;
			m = temp%m;
		}
		
		return n;
	}
}
