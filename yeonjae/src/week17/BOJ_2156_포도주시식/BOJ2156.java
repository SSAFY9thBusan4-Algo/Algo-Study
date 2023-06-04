import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2156 {

    static int N, maxSum;
    static int[] weights;
    static int[][] DP;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        weights = new int[N];
        for(int i = 0; i < N; i++){
            weights[i] = Integer.parseInt(in.readLine());
        }

        DP = new int[N + 1][3];
        for(int i = 0; i < N; i++){
            // 마실 수 있는 경우 (현재 연속으로 0잔 또는 1잔만 마신 경우)
            DP[i + 1][1] = DP[i][0] + weights[i];
            DP[i + 1][2] = DP[i][1] + weights[i];

            // 못 마시는 경우 + 안 마시는 경우
            // 연속으로 2잔을 이미 먹은 경우 + (연속으로 0잔을 먹은 경우 + 1잔을 먹은 경우)
            DP[i + 1][0] = Math.max(Math.max(DP[i][0], DP[i][1]), DP[i][2]);
        }

        // 가능한 상태(연속으로 0잔, 1잔, 2잔을 마신 경우)에서 최댓값
        maxSum = Math.max(Math.max(DP[N][0], DP[N][1]), DP[N][2]);
        System.out.println(maxSum);
    }
}