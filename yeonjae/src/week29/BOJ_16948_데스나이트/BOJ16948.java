package week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ16948 {

    static int N;
    static int r1,c1, r2,c2;
    static String[] ss;
    static int[][] DP;
    static int[] dy = {-2, -2, 0, 0, 2, 2};
    static int[] dx = {-1, 1, -2, 2, -1, 1};

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        DP = new int[N][N];

        ss = in.readLine().split(" ");
        r1 = Integer.parseInt(ss[0]);
        c1 = Integer.parseInt(ss[1]);
        r2 = Integer.parseInt(ss[2]);
        c2 = Integer.parseInt(ss[3]);

        if((r1 == r2) && (c1 == c2)) {
            System.out.println(0);
            return;
        }

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(r1, c1));
        DP[r1][c1] = 1;
        while(!q.isEmpty()) {
            Pair p = q.poll();
            int y = p.y;
            int x = p.x;

            for(int i = 0; i < 6; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny < 0 || ny >= N || nx < 0 || nx >= N)
                    continue;
                if((DP[ny][nx] != 0) && (DP[ny][nx] <= DP[y][x] + 1))
                    continue;

                DP[ny][nx] = DP[y][x] + 1;
                q.add(new Pair(ny, nx));
            }
        }

        System.out.println(DP[r2][c2] == 0 ? -1 : DP[r2][c2] - 1);
    }
}
