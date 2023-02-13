package src.week2.BOJ_11727_S3_2xn타일링2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력파일 객체화
		int N = Integer.parseInt(br.readLine());

		// solve
		/*
		 * n번째 경우 : n-1번째경우에 | 하나 붙인거
		 * 			+ n-2번째 경우에 = 또는 ㅁ 붙인거
		 * 그럼 n = (n-1) + (n-2)*2인가? ㄴㄴ 숫자 커지면 틀림.
		 * 왜? int값이 20억까지니까.
		 * (a+b)mod n = ((a mod n) + (b mod n)) mod n
		 */
		
		
		int n1 = 3;	// n-1번째 수
		int n2 = 1;	// n-2번째 수
		int n = 3;	// n번째 수
		if(N == 1) n = 1;	//1,2일때 for문 안들어가니까 초기화
		
		for(int i = 3 ; i <= N ; i++ ) {
			
			n = ( n1 + ((n2*2) % 10_007) ) % 10_007 ;
			
			n2 = n1;
			n1 = n;
		}
		

		// 출력
		System.out.println(n);
	}
}
