package week4.BOJ_1495_S1_기타리스트;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	private static int n, s, m, max;
	private static int vol[], visit[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		String[] nsm = br.readLine().split(" ");
		n = Integer.parseInt(nsm[0]);
		s = Integer.parseInt(nsm[1]);
		m = Integer.parseInt(nsm[2]);
		vol = new int[n+1];
		max = -1;
		String[] split = br.readLine().split(" ");
		
		vol[0] = s;
		for (int i = 1; i < n+1; i++) {
			vol[i] = Integer.parseInt(split[i-1]);
		}

		visit = new int[n+2][m+1];
		recursion(1,s);
		
		System.out.println(max);
	}
	
	private static void recursion(int start, int sum) {
		if(start == n+1) {
			max = Math.max(max, sum);
			return;
		}
		visit[start][sum] = 1;
		int plus = sum + vol[start];
		int minus = sum - vol[start];
		if(plus > m && minus < 0) {
			visit[start][sum] = -1;
			return;
		}
		if(plus<=m && visit[start+1][plus]!=1) { 
			recursion(start+1, plus);
		}
		if(minus>=0 && visit[start+1][minus]!=1) {
			recursion(start+1, minus);
		}
		return;
	}	
}