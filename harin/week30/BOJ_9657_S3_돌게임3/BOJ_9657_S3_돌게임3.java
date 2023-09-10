import java.io.*;
import java.util.*;

public class BOJ_9657_S3_돌게임3 {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(in.readLine());
        
        boolean dp[] = new boolean[1001];
        dp[1] = true;
        dp[2] = false;
        dp[3] = true;
        dp[4] = true;
        
        for(int i=5; i<=N; i++) {
            if(dp[i-1] == false || dp[i-3] == false || dp[i-4] == false) dp[i] = true;
            else dp[i] = false;
        }
        
        if(dp[N]) System.out.println("SK");
        else System.out.println("CY");
        
    }
}
