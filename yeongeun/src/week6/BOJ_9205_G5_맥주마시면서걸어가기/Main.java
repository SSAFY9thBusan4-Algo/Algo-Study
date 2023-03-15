package src.week6.BOJ_9205_G5_맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] startPoint = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[][] store = new int[N][2];
			for(int i = 0 ; i < N ; i++) {
				store[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			int[] endPoint = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			// solve
			boolean result = isTheyHappy(startPoint, store, endPoint);
			if(result) sb.append("happy\n");
			else sb.append("sad\n");
		}
		System.out.println(sb);
	}

	private static boolean isTheyHappy(int[] startPoint, int[][] store, int[] endPoint) {
		
		// 1. 집에서 페스티벌까지 현재 맥주만으로 도착 가능하다면
		if(getDistance(startPoint, endPoint) <= 1000)	// 20개 * 각각 50
			return true;
		
		// 2. 아니면 편의점을 찾는다.
		boolean[] visited = new boolean[store.length];
		Queue<Integer> visitedIndex = new ArrayDeque<Integer>();
		// 2-1. start에서 갈 수 있는 곳 
		for(int i = 0 ; i < store.length; i++) {
			if(getDistance(startPoint, store[i]) <= 1000) {
				visitedIndex.offer(i);
				visited[i] = true;
			}
		}
		// 2-2. visited 한 곳에서 다음으로 이동.
		while(!visitedIndex.isEmpty()) {
			int index = visitedIndex.poll();
			// 현재 편의점에서 페스티벌까지 갈 수 있다면 return true;
			if(getDistance(store[index], endPoint) <= 1000)
				return true;
			// 현재 편의점에서 갈 수 있는 편의점 큐에 넣기.
			for(int i = 0 ; i < store.length; i++) {
				if(!visited[i] && getDistance(store[index], store[i]) <= 1000) {
					visitedIndex.offer(i);
					visited[i] = true;
				}
			}
		}
		
		// 페스티벌에 갈 수 없음.
		return false;
	}

	private static int getDistance(int[] p1, int[] p2) {
		return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);
	}
	
	
}
