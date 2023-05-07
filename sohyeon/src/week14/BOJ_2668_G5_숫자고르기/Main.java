package week14.BOJ_2668_G5_숫자고르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	static int N, result;
	static int[] numbers;
	static boolean[] visited;
	static List<Integer> resultl = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		numbers = new int[N+1];
		for (int i=1; i<=N; i++) {
			numbers[i] = Integer.parseInt(in.readLine());
		}
		
		visited = new boolean[N+1];
		for (int i=1; i<=N; i++) {
			visited[i] = true;
			dfs(i, i);
			visited[i] = false;
		}
		
		System.out.println(result);
		Collections.sort(resultl);
		for (int i:resultl) System.out.println(i);
	}

	private static void dfs(int cur, int target) {
		if (!visited[numbers[cur]]) {
			visited[numbers[cur]] = true;
			dfs(numbers[cur], target);
			visited[numbers[cur]] = false;
		}
		else if (numbers[cur] == target){ 
			result++;
			resultl.add(target);
		}
	}
	
}
