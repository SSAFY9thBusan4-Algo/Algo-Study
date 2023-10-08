package week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9657 {

    static int N;
    static int DP[] = new int[1001];

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        DP[1] = 1; DP[2] = 0; DP[3] = 1; DP[4] = 1; DP[5] = 1;

        for(int i = 6; i <= N; i++) {
            if(DP[i-1] == 0 || DP[i-3] == 0 || DP[i-4] == 0)
                DP[i] = 1;
            else
                DP[i] = 0;
        }

        if (DP[N] == 0)
            System.out.println("CY");
        else
            System.out.println("SK");
    }
}
