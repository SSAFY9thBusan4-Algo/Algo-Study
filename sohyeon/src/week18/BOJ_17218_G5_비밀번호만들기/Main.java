package week18.BOJ_17218_G5_비밀번호만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] s1 = in.readLine().toCharArray();
		char[] s2 = in.readLine().toCharArray();
		
		int[][] dp = new int[s1.length+1][s2.length+1];
		
		for (int i=1; i<=s1.length; i++) {
			for (int j=1; j<=s2.length; j++) {
				if (s1[i-1]==s2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		
//		for (int i=0; i<=s1.length; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		
		String result = "";
		int r = s1.length;
		int c = s2.length;
		while (true) {
			if (r==0 || c==0) break;
			if (s1[r-1] == s2[c-1]) {
				result = s1[r-1]+result;
				r--;
				c--;
			}
			else {
				if (dp[r][c-1]<dp[r-1][c]) {
					r--;
				}else {
					c--;
				}
			}
		}
		System.out.println(result);
	}

}
