package week4.BOJ_11057_S1_오르막수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	private static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		BigInteger[] facto = new BigInteger[10+N];
		
		facto[0] = new BigInteger("1");
		for (int i = 1; i < 10+N; i++) {
			facto[i] = facto[i-1].multiply(new BigInteger(Integer.toString(i))) ;
		}
		
		BigInteger result = facto[10+N-1].divide((facto[N].multiply(facto[9])));
		System.out.println(result.remainder(new BigInteger("10007")));
		
	}

}
