package com.jihong.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class 카드정렬 {

	static StringBuilder sb = new StringBuilder();
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Queue<Integer> cards = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			cards.offer(Integer.parseInt(br.readLine())); // 우선순위 큐로 차례대로 정렬
		}
		
		int sum = 0;
		int A = 0, B = 0;

		while(!cards.isEmpty()) {
			A = cards.poll();
			if(!cards.isEmpty()) {
				B = cards.poll();
			}
			else { // 마지막 값이면 종료
				break;
			}
			
			sum += A + B;
			cards.offer(A + B); // 더한 값을 다시 pq에 넣음
		}
		
		System.out.println(sum);
	}
}
