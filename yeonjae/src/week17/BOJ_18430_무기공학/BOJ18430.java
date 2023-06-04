import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ18430 {

    static int N, M, maxSum;
    static int[][] a;
    static boolean[][] visited;
    static String[] ss;

    static int dy[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int dx[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        a = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            ss = in.readLine().split(" ");
            for(int j = 0; j < M; j++){
                a[i][j] = Integer.parseInt(ss[j]);
            }
        }

        go(0, 0, 0);
        System.out.println(maxSum);
    }

    private static void go(int y, int x, int sum) {

        // 탐색을 완료한 경우
        if((y == N - 1) && (x == M)) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        if(x == M){
            y++;
            x = 0;
        }


        if(!visited[y][x]){
            // 4가지 모양의 부메랑을 모두 탐색
            for(int i = 0; i < 4; i++){
                int ny1 = y + dy[i][0];
                int nx1 = x + dx[i][0];
                int ny2 = y + dy[i][1];
                int nx2 = x + dx[i][1];

                if(ny1 < 0 || nx1 < 0 || ny1 >= N || nx1 >= M)
                    continue;
                if(ny2 < 0 || nx2 < 0 || ny2 >= N || nx2 >= M)
                    continue;

                if(visited[ny1][nx1] || visited[ny2][nx2])
                    continue;

                visited[y][x] = true;
                visited[ny1][nx1] = true;
                visited[ny2][nx2] = true;
                go(y, x + 1, sum + (a[y][x] * 2 + a[ny1][nx1] + a[ny2][nx2]));
                visited[y][x] = false;
                visited[ny1][nx1] = false;
                visited[ny2][nx2] = false;
            }
        }

        go(y, x + 1, sum);
    }
}
