package week19.BOJ_22945_G4_팀빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22945_G4_팀빌딩 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] input = new int[N];

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = N-1;
		int max = 0;
		
		while(true) {
			if(start==end) { // 투포인터가 만나면 종료
				break;
			}
			
			int diff = end-start-1; // 개발자들 사이에 존재하는 다른 개발자 수
			int min = Math.min(input[start], input[end]); // min
			max = Math.max(max, diff*min);
			
			// 능력치가 더 큰 포인터를 옮겨도 max값은 안되므로 능력치가 더 작은 포인터를 옮김
			if(input[start]>input[end]) {
				end--;
			}
			else {
				start++;
			}
		}
		
		System.out.println(max);
	}

}
