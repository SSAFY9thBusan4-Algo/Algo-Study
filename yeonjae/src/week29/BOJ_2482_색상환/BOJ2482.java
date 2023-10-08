package week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2482 {

    static int N, K;
    static int[][] DP;
    static int MOD = 1_000_000_003;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        K = Integer.parseInt(in.readLine());

        DP = new int[N][K+1];
        for(int i = 0; i < N; i++) {
            DP[i][0] = 1; // 0개를 뽑는 경우의 수 = 1
            DP[i][1] = i; // i개에서 1개를 뽑는 경우의 수 = i
        }

        for(int i = 2; i < N; i++) {
            for(int j = 2; j <= K; j++) {
                // i개에서 j개를 뽑는 경우의 수 = (i-2개에서 j-1개를 뽑고 i번째에 1개를 뽑는 경우의 수) + (i-1개까지 j개를 다 뽑는 경우의 수)
                DP[i][j] = (DP[i-2][j-1] + DP[i-1][j]) % MOD;
            }
        }

        // 위에까지는 선형으로 생각하였음
        // 하지만, 문제는 원형이므로 1번째와 N번째가 겹쳐서는 안됨
        // N번째 칸을 칠하는 경우, 첫번째 칸을 칠하면 안되므로, 2~(N-1)까지의 칸을 K-1개로 칠해야함
        // N번째 칸을 칠하지 않는 경우, 첫번째 칸을 칠할 수 있으므로, 1~(N-1)까지의 칸을 K개로 칠해야함
        System.out.println((DP[N-3][K-1] + DP[N-1][K]) % MOD);
    }
}
