package src.week10.BOJ_1715_G4_카드정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for(int i = 0 ; i < N; i++) {
			queue.offer(Integer.parseInt(br.readLine()));
		}
		
		// solve
		int sum = 0;
		int n1, n2;
		while(!queue.isEmpty()) {
			
			n1 = queue.poll();
			if(queue.isEmpty()) break;
			
			n2 = queue.poll();
			
			sum += n1 + n2;
			queue.offer(n1+n2);
			
		}
		
		System.out.println(sum);
	}
}
