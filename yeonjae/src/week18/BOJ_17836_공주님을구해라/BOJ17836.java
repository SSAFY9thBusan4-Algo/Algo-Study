import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ17836 {

    static int N, M, T;
    static int[][] a;
    static int[][] visited;
    static int ansCnt = Integer.MAX_VALUE;
    static Pair sword;
    static String[] ss;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);
        T = Integer.parseInt(ss[2]);


        a = new int[N][M];
        visited = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], -1);
        }

        for (int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(ss[j]);
                if (a[i][j] == 2) {
                    sword = new Pair(i, j);
                }
            }
        }

        // 1. 검을 사용하지 않고 목적지에 가능 경우
        bfs();

        // 2. 검을 사용하는 경우 (출발지 -> 검 -> 목적지)
        int lengthWithSword = 0;

        // 검이 있는 곳에 갈 수 있는 경우
        if(visited[sword.y][sword.x] != -1) {
            lengthWithSword = visited[sword.y][sword.x] + (N - 1 - sword.y) + (M - 1 - sword.x);
            if(lengthWithSword <= T)
                ansCnt = Math.min(ansCnt, lengthWithSword);
        }

        System.out.println(ansCnt == Integer.MAX_VALUE ? "Fail" : ansCnt);
    }

    private static void bfs() {

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, 0));
        visited[0][0] = 0;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int y = pair.y;
            int x = pair.x;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                    continue;
                if (visited[ny][nx] != -1)
                    continue;
                if(a[ny][nx] == 1)
                    continue;

                visited[ny][nx] = visited[y][x] + 1;
                q.add(new Pair(ny, nx));
            }
        }

        if((visited[N-1][M-1] != -1) && (visited[N-1][M-1] <= T))
            ansCnt = visited[N-1][M-1];
    }
}
