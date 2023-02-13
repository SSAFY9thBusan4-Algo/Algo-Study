package week2.BOJ_11727_S3_2xn타일링2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();

        // int[] dp = new int[n+1];  이렇게 하니 ArrayOutOfBounds 뜸.. -> n+2 하면됨!
		int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        }
        System.out.println(dp[n]);
		
	}
	
}
