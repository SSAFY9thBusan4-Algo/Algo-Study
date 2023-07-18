import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ14938 {

    static int N, M, R, maxItemCnt, itemCnt;
    static int[] item;
    static int[][] DP;
    static int[] ii;
    static final int INF = 99999;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] nmr = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        N = nmr[0];
        M = nmr[1];
        R = nmr[2];

        item = new int[N+1];
        ii = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        for(int i = 1; i <= N; i++) {
            item[i] = ii[i-1];
        }

        // 방향성이 없으므로 양쪽으로 길이 있다고 하기
        DP = new int[N+1][N+1];
        for(int i = 0; i < R; i++) {
            ii = Arrays.stream(in.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
            DP[ii[0]][ii[1]] = ii[2];
            DP[ii[1]][ii[0]] = ii[2];
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(DP[i][j] == 0)
                    DP[i][j] = INF;
            }
        }

        // 플로이드 와샬
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                if(k == i)
                    continue;
                for(int j = 1; j <= N; j++) {
                    if(k == j || i == j)
                        continue;

                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k][j]);
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            // 자신 노드
            itemCnt = item[i];

            // 자신을 제외한 노드
            for(int j = 1; j <= N; j++) {
                if(DP[i][j] <= M)
                    itemCnt += item[j];
            }

            maxItemCnt = Math.max(maxItemCnt, itemCnt);
        }

        System.out.println(maxItemCnt);
    }
}
