package src.week20.BOJ_1188_G4_음식평론가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1188 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);
		
		int result = 0;
		if(N % M != 0) {	// 나눠 떨어지면 자를 필요 없음.
			// M개로 나누는 횟수 - 이미 잘려져 있는 조각
			result = M-1 - (GCD(N,M) -1);
		}
		
		System.out.println(result);
	}

	private static int GCD(int n, int m) {

		while(m != 0) {
			int t = n % m;
			n = m;
			m = t;
		}
		return n;
	}
}
