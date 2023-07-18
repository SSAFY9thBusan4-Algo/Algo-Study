import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ1726 {
    static int N, M;
    static int[][] map;
    static int[][][] visited;
    static int sy, sx, sdir, ey, ex, edir;
    static String[] ss;
    // 동(1), 서(2), 남(3), 북(4)
    static int[] dy = {0, 0, 0, 1, -1};
    static int[] dx = {0, 1, -1, 0, 0};
    static int[] turnRight = {0, 3, 4, 2, 1};
    static int[] turnLeft = {0, 4, 3, 1, 2};
    static class Robot {
        int y, x, dir;

        public Robot(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        map = new int[N][M];
        visited = new int[N][M][5];
        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(ss[j]);
            }
        }

        ss = in.readLine().split(" ");
        sy = Integer.parseInt(ss[0]) - 1;
        sx = Integer.parseInt(ss[1]) - 1;
        sdir = Integer.parseInt(ss[2]);

        ss = in.readLine().split(" ");
        ey = Integer.parseInt(ss[0]) - 1;
        ex = Integer.parseInt(ss[1]) - 1;
        edir = Integer.parseInt(ss[2]);

        Queue<Robot> q = new ArrayDeque<>();
        q.add(new Robot(sy, sx, sdir));
        visited[sy][sx][sdir] = 1;
        while(!q.isEmpty()) {
            Robot robot = q.poll();
            int y = robot.y;
            int x = robot.x;
            int dir = robot.dir;

            // Go 1, Go 3, Go 3
            for(int i = 1; i <= 3; i++) {
                int ny = y + dy[dir] * i;
                int nx = x + dx[dir] * i;

                if(ny < 0 || nx < 0 || ny >= N || nx >= M)
                    continue;
                if (blocked(y, x, ny, nx))
                    continue;
                if(visited[ny][nx][dir] != 0
                    && visited[ny][nx][dir] <= visited[y][x][dir] + 1)
                    continue;

                visited[ny][nx][dir] = visited[y][x][dir] + 1;
                q.add(new Robot(ny, nx, dir));
            }

            // Turn right
            int rightDir = turnRight[dir];
            if(visited[y][x][rightDir] == 0
                || visited[y][x][rightDir] > visited[y][x][dir] + 1) {
                visited[y][x][rightDir] = visited[y][x][dir] + 1;
                q.add(new Robot(y, x, rightDir));
            }

            // Turn left
            int leftDir = turnLeft[dir];
            if(visited[y][x][leftDir] == 0
                || visited[y][x][leftDir] > visited[y][x][dir] + 1) {
                visited[y][x][leftDir] = visited[y][x][dir] + 1;
                q.add(new Robot(y, x, leftDir));
            }
        }

        System.out.println(visited[ey][ex][edir] - 1);
    }

    private static boolean blocked(int y, int x, int ny, int nx) {
        int y1 = Math.min(y, ny);
        int x1 = Math.min(x, nx);
        int y2 = Math.max(y, ny);
        int x2 = Math.max(x, nx);

        boolean isBlocked = false;
        for(int i = y1; i <= y2; i++) {
            for(int j = x1; j <= x2; j++) {
                if(map[i][j] == 1)
                    isBlocked = true;
            }
        }

        return isBlocked;
    }
}