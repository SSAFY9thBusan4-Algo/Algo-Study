package src.week14.BOJ_2668_G5_숫자고르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
	
	private static int[] numbers;
	private static int[] visited;
	private static TreeSet<Integer> selected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		int N = Integer.parseInt(br.readLine());
		numbers = new int[N+1];
		for(int i = 1; i <= N ; i++) numbers[i] = Integer.parseInt(br.readLine());
		
		// solve
		visited = new int[N+1];
		selected = new TreeSet<>();
		for(int i = 1 ; i <= N ; i++) {
			if(visited[i] == 0) {
				int circle = makeCircle(i, i);
				if(circle > 0) addNum(circle);
			}
		}
		
		// output
		StringBuilder sb = new StringBuilder();
		sb.append(selected.size()).append('\n');
		for(int n : selected) {
			sb.append(n).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int makeCircle(int n, int cur) {
		if(numbers[n] == n) return n;
		if(visited[n] == cur) return n;
		if(visited[n] > 0) return -1;
		
		visited[n] = cur;
		return makeCircle(numbers[n], cur);
	}
	
	private static void addNum(int n) {
		if(selected.contains(n)) return;
		
		selected.add(n);
		addNum(numbers[n]);
	}
}
