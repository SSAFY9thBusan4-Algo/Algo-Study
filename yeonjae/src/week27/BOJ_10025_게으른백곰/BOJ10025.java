package week27;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10025 {

    static int N, K, g, x;
    static Long maxSum = 0L;
    static Long sum = 0L;
    static final int MAX_POSITION = 1_000_000;
    static int[] ice = new int[MAX_POSITION + 1];
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        K = Integer.parseInt(ss[1]);

        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");

            g = Integer.parseInt(ss[0]);
            x = Integer.parseInt(ss[1]);

            ice[x] = g;
        }

        for(int i = 0; i <= K * 2; i++) {
            if(i > MAX_POSITION)
                break;
            sum += ice[i];
        }

        maxSum = sum;
        for(int i = K * 2 + 1; i <= MAX_POSITION; i++) {
            sum -= ice[i - (K * 2 + 1)];
            sum += ice[i];

            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }
}
