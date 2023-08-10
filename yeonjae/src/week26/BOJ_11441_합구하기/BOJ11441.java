package week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11441 {

    static int N, M, left, right;
    static String[] ss;
    static int[] sum;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        sum = new int[N + 1];
        ss = in.readLine().split(" ");

        for(int i = 1; i <= N; i++) {
            sum[i] = sum[i-1] + Integer.parseInt(ss[i-1]);
        }

        M = Integer.parseInt(in.readLine());
        for(int i = 0; i < M; i++) {
            ss = in.readLine().split(" ");
            left = Integer.parseInt(ss[0]);
            right = Integer.parseInt(ss[1]);
            sb.append(sum[right] - sum[left - 1]).append('\n');
        }

        System.out.println(sb);
    }
}
