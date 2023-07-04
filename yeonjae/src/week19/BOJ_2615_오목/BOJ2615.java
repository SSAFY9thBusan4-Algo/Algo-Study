import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2615 {

    static final int N = 19;
    static int[][] a;
    static boolean[][][] visited;
    static String[] ss;

    static int[] dy = {0, 1, 1, 1}; // 오른쪽(→), 아래(↓), 오른쪽 아래(↘), 왼쪽 아래(↙)
    static int[] dx = {1, 0, 1, -1};
    static int winner, winnerY, winnerX;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        a = new int[N][N];
        visited = new boolean[N][N][4];

        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                a[i][j] = Integer.parseInt(ss[j]);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(a[i][j] > 0) {
                    go(i, j);
                }
            }
            if(winner > 0)
                break;
        }

        if(winner == 0) {
            System.out.println(0);
        }
        else {
            System.out.println(winner);
            System.out.println((winnerY + 1) + " " + (winnerX + 1));
        }
    }

    private static void go(int y, int x) {

        // 오른쪽(→), 아래(↓), 오른쪽 아래(↘), 왼쪽 아래(↙)
        for(int i = 0; i < 4; i++) {
            if(visited[y][x][i])
                continue;

            goOneDirection(y, x, i, 1);
        }
    }

    private static void goOneDirection(int y, int x, int direction, int cnt) {

        int ny = y + dy[direction];
        int nx = x + dx[direction];

        if(ny < 0 || nx < 0 || ny >= N || nx >= N || a[ny][nx] != a[y][x]) {
            if(cnt == 5) {
                if(direction == 3) {
                    winner = a[y][x];
                    winnerY = y;
                    winnerX = x;
                }
                else {
                    winner = a[y][x];
                    winnerY = y - dy[direction] * 4;
                    winnerX = x - dx[direction] * 4;
                }
            }

            return;
        }

        visited[ny][nx][direction] = true;
        goOneDirection(ny, nx, direction, cnt+1);
    }
}
