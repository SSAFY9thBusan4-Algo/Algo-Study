import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1937 {
    static int N, maxMoveCnt;
    static int[][] a, DP;
    static String[] ss;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        a = new int[N][N];
        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                a[i][j] = Integer.parseInt(ss[j]);
            }
        }

        DP = new int[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(DP[i], -1);
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(DP[i][j] == -1) {
                    go(i, j);
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N ; j++) {
                maxMoveCnt = Math.max(maxMoveCnt, DP[i][j]);
            }
        }

        System.out.println(maxMoveCnt + 1);
    }

    private static int go(int y, int x) {

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N)
                continue;
            if(a[y][x] >= a[ny][nx])
                continue;


            if(DP[ny][nx] != -1){
                DP[y][x] = Math.max(DP[y][x], DP[ny][nx] + 1);
            }
            else {
                DP[y][x] = Math.max(DP[y][x], go(ny, nx) + 1);
            }
        }

        if(DP[y][x] == -1)
            DP[y][x] = 0;

        return DP[y][x];
    }
}
