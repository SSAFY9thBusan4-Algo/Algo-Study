package week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ11048 {

    static int N, M;
    static int[][] map;
    static String[] ss;
    static int maxCnt;
    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 1, 0};
    static int[][] DP;

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(ss[j]);
            }
        }

        DP = new int[N][M];
        DP[0][0] = map[0][0];

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                for(int i = 0; i < 3; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny >= N || nx >= M)
                        continue;

                    if(DP[ny][nx] < DP[y][x] + map[ny][nx]) {
                        DP[ny][nx] = DP[y][x] + map[ny][nx];
                    }
                }
            }
        }

        System.out.println(DP[N-1][M-1]);
    }

    private static void go(int y, int x) {

        for(int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny >= N || nx >= M)
                continue;

            if(DP[ny][nx] < DP[y][x] + map[ny][nx]) {
                DP[ny][nx] = DP[y][x] + map[ny][nx];
                go(ny, nx);
            }
        }
    }
}
