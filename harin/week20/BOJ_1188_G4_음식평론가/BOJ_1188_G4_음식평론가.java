import java.io.*;
import java.util.*;

public class BOJ_1188_G4_음식평론가 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
    // N, M 입력 받기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

    // 최대 공약수 구하기
		int gcd = gcd(N, M);

    // M에서 최대공약수를 뺀 값이 답이다.
		System.out.println(M - gcd);
	}

  // 최대 공약수 구하는 함수
	public static int gcd(int N, int M) {
		if(M == 0) return N;
		return gcd(M, N % M);
	}

}
