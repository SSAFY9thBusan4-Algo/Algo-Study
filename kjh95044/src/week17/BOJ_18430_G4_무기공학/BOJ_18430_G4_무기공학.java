import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N + 1][M + 1];

        dfs(0, 0);

        System.out.println(result);
    }

    private static void dfs(int cnt, int sum) {
        if (cnt == N * M) {
            result = Math.max(result, sum);
            return;
        }

        int x = cnt / M;
        int y = cnt % M;

        // 방문하지 않았다면
        if (!visited[x][y]) {

            // 모든 부메랑을 체크 한다.

            // 1번 부메랑
            if (x + 1 < N && y - 1 >= 0 && !visited[x + 1][y] && !visited[x][y - 1]) {
                visited[x][y] = true;
                visited[x + 1][y] = true;
                visited[x][y - 1] = true;

                dfs(cnt + 1, sum + 2 * (map[x][y]) + map[x + 1][y] + map[x][y - 1]);

                visited[x][y] = false;
                visited[x + 1][y] = false;
                visited[x][y - 1] = false;
            }

            // 2번 부메랑
            if (x - 1 >= 0 && y - 1 >= 0 && !visited[x - 1][y] && !visited[x][y - 1]) {
                visited[x][y] = true;
                visited[x - 1][y] = true;
                visited[x][y - 1] = true;

                dfs(cnt + 1, sum + 2 * (map[x][y]) + map[x - 1][y] + map[x][y - 1]);

                visited[x][y] = false;
                visited[x - 1][y] = false;
                visited[x][y - 1] = false;
            }

            // 3번 부메랑
            if (x - 1 >= 0 && y + 1 < M && !visited[x - 1][y] && !visited[x][y + 1]) {
                visited[x][y] = true;
                visited[x - 1][y] = true;
                visited[x][y + 1] = true;

                dfs(cnt + 1, sum + 2 * (map[x][y]) + map[x - 1][y] + map[x][y + 1]);

                visited[x][y] = false;
                visited[x - 1][y] = false;
                visited[x][y + 1] = false;
            }

            // 4번 부메랑
            if (x + 1 < N && y + 1 < M && !visited[x + 1][y] && !visited[x][y + 1]) {
                visited[x][y] = true;
                visited[x + 1][y] = true;
                visited[x][y + 1] = true;

                dfs(cnt + 1, sum + 2 * (map[x][y]) + map[x + 1][y] + map[x][y + 1]);

                visited[x][y] = false;
                visited[x + 1][y] = false;
                visited[x][y + 1] = false;
            }
        }
        dfs(cnt+1, sum);
    }
}
