package week10.BOJ_1715_G4_카드정렬하기;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		
		Queue<Integer> queue = new PriorityQueue<>();
		for (int i=0; i<N; i++) {
			queue.offer(in.nextInt());
		}
		
		// 총 비교한 쵯수
		int sum = 0;
		
		// 제일 작은 두 묶음씩 꺼내서 합치기
		for (int i=0; i<N-1; i++) {
			int A = queue.poll();
			int B = queue.poll();
			sum += A+B;
			queue.offer(A+B);
		}
		
		System.out.println(sum);
	}
	
}