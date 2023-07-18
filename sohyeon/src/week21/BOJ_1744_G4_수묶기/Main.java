package week21.BOJ_1744_G4_수묶기;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] numbers = new int[N];
		PriorityQueue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> queue2 = new PriorityQueue<>();
		boolean isZero = false;
		for (int i=0; i<N; i++) {
			numbers[i] = in.nextInt();
			if (numbers[i] > 0) queue1.offer(numbers[i]);
			else if (numbers[i]==0) isZero=true;
			else queue2.offer(numbers[i]);
		}
		
		int result = 0;
		int cur=0, num=0;
		while(!queue1.isEmpty()) {
			cur = queue1.poll();			
			if (queue1.size()==0 && num==0) {
				result += cur;
				break;
			}
			if (num == 0) num = cur;
			else {
				if ((num*cur) < (num+cur)) {
					result+= (num+cur);					
				}
				else result += (num*cur);				
				num = 0;
			}			
		}
		cur=0;
		num=0;
		while(!queue2.isEmpty()) {
			cur = queue2.poll();			
			if (queue2.size()==0 && num==0) {
				if (isZero) break;
				result += cur;
				break;
			}
			if (num == 0) num = cur;
			else {
				result += (num*cur);
				num = 0;
			}			
		}
		System.out.println(result);
		
	}
	
}
