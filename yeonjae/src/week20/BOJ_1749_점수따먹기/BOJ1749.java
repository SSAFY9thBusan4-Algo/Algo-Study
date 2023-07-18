import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1749 {

    static int N, M, tempSum, maxSum = Integer.MIN_VALUE;
    static int[][] a, sum;
    static String[] ss;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        a = new int[N+1][M+1];

        for(int i = 1; i <= N; i++) {
            ss = in.readLine().split(" ");
            for(int j = 1; j <= M; j++) {
                a[i][j] = Integer.parseInt(ss[j-1]);
            }
        }

        sum = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + a[i][j];
            }
        }

        for(int sy = 1; sy <= N; sy++) {
            for(int sx = 1; sx <= M; sx++) {
                for(int ey = sy; ey <= N; ey++){
                    for(int ex = sx; ex <= M; ex++) {
                        tempSum = sum[ey][ex] - sum[ey][sx-1] - sum[sy-1][ex] + sum[sy-1][sx-1];
                        maxSum = Math.max(maxSum, tempSum);
                    }
                }
            }
        }

        System.out.println(maxSum);
    }
}
