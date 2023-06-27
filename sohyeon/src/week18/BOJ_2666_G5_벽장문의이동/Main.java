package week18.BOJ_2666_G5_벽장문의이동;

import java.util.Scanner;

public class Main {
	
	static int n;  // 벽장의 수
	static int d1,d2;  // 열려있는 벽장 두개
	static int m;  // 사용할 벽장들의 수
	static int[] doors;  // 사용할 벽장들
	static int result; // 문의 최소 이동 횟수 

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		d1 = sc.nextInt();
		d2 = sc.nextInt();
		m = sc.nextInt();
		doors = new int[m+1];
		for (int i=1; i<=m; i++) {
			doors[i] = sc.nextInt();
		}
		
		result = Integer.MAX_VALUE;
		dfs(1, 0);
		
		System.out.println(result);
	}

	private static void dfs(int cur, int cnt) {
		if (cur == m+1) {	
			result = Math.min(result, cnt);
			return;
		}
		
		int a=d1, b=d2;
		d1 = doors[cur];
		dfs(cur+1, cnt+Math.abs(a-doors[cur]));
		d1 = a;
		d2 = doors[cur];
		dfs(cur+1, cnt+Math.abs(b-doors[cur]));
		d2 = b;
	}
}
