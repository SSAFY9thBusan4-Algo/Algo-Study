package week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ15565 {

    static int N, K, lionCnt, minSize = Integer.MAX_VALUE;
    static int[] lionPosition;
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        K = Integer.parseInt(ss[1]);

        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            if(ss[i].equals("1"))
                lionCnt++;
        }

        if(lionCnt < K) {
            System.out.println(-1);
            return;
        }

        lionPosition = new int[lionCnt];
        int lionIdx = 0;
        for(int i = 0; i < N; i++) {
            if(ss[i].equals("1")) {
                lionPosition[lionIdx++] = i;
            }
        }

        for(int i = K - 1; i < lionCnt; i++) {
            int startIdx = i - (K - 1);
            int endIdx = i;
            minSize = Math.min(minSize, lionPosition[endIdx] - lionPosition[startIdx] + 1);
        }

        System.out.println(minSize);
    }
}
