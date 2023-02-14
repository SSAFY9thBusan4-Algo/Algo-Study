package week2.BOJ_11727_S3_2xn타일링2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		int[] arr;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N]; //N만큼 int배열 생성
		
		arr[0] = 1;
		
		//점화식이 만들어지지 않는 경우의 조건문
		if (N>1) {
			arr[1] = 3;
		}
		if(N>2) {
			arr[2] = 5;
		}
		
		//점화식을 이용해 값 구해서 배열에 저장
		for (int i=3; i<N; i++) {
			arr[i] = (arr[i-1] + 2*arr[i-2]) % 10007;
		}
		System.out.println(arr[N-1]);
	}
}