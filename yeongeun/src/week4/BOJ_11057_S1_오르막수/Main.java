package src.week4.BOJ_11057_S1_오르막수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력파일 객체화
		int N = Integer.parseInt(br.readLine());

		// solve
		// dp
		int[] curNumCnt = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};	// 한자리 수 만들 수 있는 경우 : 0부터 9까지
		int[] nextNumCnt = null;
		for(int n = 1 ; n < N ; n++) {
			
			nextNumCnt = new int[10];
			int cnt = 0;
			
			// 0부터 9까지 현재 만들 수 있는 숫자
			for(int i = 0 ; i < 10; i++) {
				cnt += curNumCnt[i]; 	// 현재 자리가 올 수 있는 경우 = 앞자리 수가 같거나 작은 경우.
				nextNumCnt[i] += cnt;	// 다음 번에 쓰기위해 저장.
				nextNumCnt[i] %= 10_007;	// int 값 넘어갈 수도 있으니까 나눠서 저장.
			}
			curNumCnt = Arrays.copyOf(nextNumCnt, 10);
		}
		
		int ans = 0;
		for(int n : curNumCnt) {
			//System.out.print(n+" ");
			ans += n;
			ans %= 10_007;
		}
		//System.out.println();

		// 출력
		System.out.println(ans);
	}
}