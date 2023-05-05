package week9.BOJ_21318_S1_피아노체조;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] music = new int[N+1];
		int[] dp = new int[N+1];
		int num = 0;
		String[] split = br.readLine().split(" ");
		for(int i=1;i<N+1;i++) {
			num = Integer.parseInt(split[i-1]);
			music[i] = num;
			
			if(music[i-1]>music[i]) {
				dp[i] = dp[i-1]+1;
			}
			else {
				dp[i] = dp[i-1];
			}
		}
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			split= br.readLine().split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			sb.append(dp[y]-dp[x]).append("\n");
		}
		System.out.println(sb);
	}	
}