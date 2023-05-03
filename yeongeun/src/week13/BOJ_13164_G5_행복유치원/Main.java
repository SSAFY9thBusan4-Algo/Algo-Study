package week13.BOJ_13164_G5_행복유치원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int K = Integer.parseInt(in[1]);
		
		int[] height = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		// 1. 키차이 구하기
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for(int i = 1; i < N ; i++) {
			queue.offer(height[i] - height[i-1]);
		}
		
		int select = N - K;	// 남아 있는 원생 수
		int cost = 0;
		
		// 2. 가장 작은 키차이 선택하면서 더해준다.
		while(--select >= 0) {
			cost += queue.poll();
		}
		
		System.out.println(cost);
	}

}
