package week4.BOJ_1495_S1_기타리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		int N = input[0];
		int S = input[1];
		int M = input[2];
		
		int[] dv = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		int[] dp = new int[M+1];
		for (int i = 0; i <= M; i++) {
			dp[i] = -1;
		}
		dp[S] = 0; 

		// 메모리 초과 (for 안에 있는 내용을 지우고 주석 해재한 코드)
//		Queue<Integer> queue = new ArrayDeque<>();
//		Queue<Integer> sub = new ArrayDeque<>();
//		queue.offer(S);
		for (int i = 1; i <= N; i++) {  // 이번 곡에 설정될 수 있는 볼륨 저장
			int v = dv[i-1];
			Queue<Integer> queue = new ArrayDeque<>();
			
			for (int j = 0; j <= M; j++) {
				if (dp[j] == i-1) {
					int v1 = j+v;
					int v2 = j-v;
					if (0<=v1 && v1<=M) {
						queue.offer(v1);
					}
					if (0<=v2 && v2<=M) {
						queue.offer(v2);
					}
				}
			}
			
			while(!queue.isEmpty()) {
				dp[queue.poll()] = i;
			}
			
			// 메모리 초과
//			while (!queue.isEmpty()) {
//				int cur = queue.poll();
//				int v1 = cur + dv[i];
//				int v2 = cur - dv[i];
//				if (0<=v1 && v1<=M) {
//					sub.offer(v1);
//					dp[v1] = i;
//				}
//				if (0<=v2 && v2<=M) {
//					sub.offer(v2);
//					dp[v2] = i;
//				}
//			}
//			while(!sub.isEmpty()) {
//				queue.offer(sub.poll());
//			}			
		}
		
		int result = -1;		
		for (int i = 0; i <= M; i++) {
			if (dp[i] == N) {
				result = i;
			}
		}
		
		System.out.println(result);
		
	}
	
}
