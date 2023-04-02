package src.week9.BOJ_21318_S1_피아노체조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");

		int[] list = new int[N+1];
		int pre = Integer.parseInt(input[0]);
		int cur;
		for(int i = 1 ; i < N; i++) {
			cur = Integer.parseInt(input[i]);
			if(pre > cur) list[i] = list[i-1] + 1;
			else list[i] = list[i-1];
			pre = cur;
		}
		list[N] = list[N-1];
		
		// Question
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int q = 0 ; q < Q; q++) {
			
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			
			int count = list[end-1] - list[start-1];
			
			sb.append(count).append('\n');
			
		}
		System.out.println(sb);
		
	}
	/*
	 * 첫번째 쓴 코드
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");

		List<Integer> miss = new ArrayList<>();
		int pre = Integer.parseInt(input[0]);
		int cur;
		for(int i = 1 ; i < N; i++) {
			cur = Integer.parseInt(input[i]);
			if(pre > cur) miss.add(i);
			pre = cur;
		}
		// 배열로
		int[] arr = miss.stream().mapToInt(Integer::intValue).toArray();
		
		// Question
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int q = 0 ; q < Q; q++) {
			
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			
			int count = 0;
			int find = Arrays.binarySearch(arr, start);
			if(find < 0) find = find * -1 -1;
			for(int idx = find ; idx < arr.length && arr[idx] < end; idx++) {
				count++;
			}
			
			sb.append(count).append('\n');
			
		}
		System.out.println(sb);
		
	}
	*/
}
