import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ15684 {

    static int N, M, H, A, B;
    static boolean[][] visited = new boolean[40][40];
    static String[] ss;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);
        H = Integer.parseInt(ss[2]);

        for(int i = 0; i < M; i++) {
            ss = in.readLine().split(" ");
            A = Integer.parseInt(ss[0]);
            B = Integer.parseInt(ss[1]);
            visited[A][B] = true;
        }

        go(1, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void go(int here, int cnt) {
        if(cnt > 3 || cnt >= ans)
            return;

        if (check()) {
            ans = Math.min(ans, cnt);
            return;
        }

        for(int i = here; i <= H; i++) {
            for(int j = 1; j <= N; j++) {
                if(visited[i][j] || visited[i][j-1] || visited[i][j+1]) {
                    continue;
                }

                visited[i][j] = true;
                go(i, cnt + 1);
                visited[i][j] = false;
            }
        }
    }

    private static boolean check() {
        for(int i = 1; i <= N; i++) {
            int start = i;
            for(int j = 1; j <= H; j++) {
                if(visited[j][start])
                    start++;
                else if(visited[j][start-1])
                    start--;
            }
            if(start != i)
                return false;
        }

        return true;
    }
}
