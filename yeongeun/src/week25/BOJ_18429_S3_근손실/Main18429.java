package src.week25.BOJ_18429_S3_근손실;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main18429 {
	
	private static int[] arr;
	private static int ans = 0, end, N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		K = Integer.parseInt(in[1]);
		
		arr = new int[N];
		in = br.readLine().split(" ");
		for(int i = 0 ; i < N ; i++) arr[i] = Integer.parseInt(in[i]);
		
		end = (1 << N) -1;
		permutation(0, 500);
		System.out.println(ans);
	}

	private static void permutation(int flag, int curWeight) {
		if(flag == end) {
			ans++;
			return;
		}
		
		for(int i = 0 ; i < N ; i++) {
			if(((1<<i) & flag) > 0) continue;
			
			if(curWeight + arr[i] - K >= 500) permutation((1<<i)|flag,curWeight + arr[i] - K);
		}
	}
}
