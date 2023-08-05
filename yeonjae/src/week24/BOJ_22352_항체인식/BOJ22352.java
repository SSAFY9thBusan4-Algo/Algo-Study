package week24;

import java.io.*;

public class BOJ22352 {

    static int[][] ori, comp;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static String[] ss;
    static int N, M, changeCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        ori = new int[N][M];
        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                ori[i][j] = Integer.parseInt(ss[j]);
            }
        }

        comp = new int[N][M];
        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                comp[i][j] = Integer.parseInt(ss[j]);
            }
        }

        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(ori[i][j] != comp[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j, ori[i][j], comp[i][j]);
                    changeCnt++;
                }
                if(changeCnt == 1)
                    break;
            }
            if(changeCnt == 1)
                break;
        }

        boolean ans = true;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(ori[i][j] != comp[i][j])
                    ans = false;
            }
        }

        System.out.println(ans ? "YES" : "NO");
    }

    static void dfs(int y, int x, int originNum, int changeNum) {
        ori[y][x] = changeNum;

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= N || nx < 0 || nx >= M)
                continue;
            if(visited[ny][nx])
                continue;
            if(ori[ny][nx] != originNum)
                continue;

            visited[ny][nx] = true;
            dfs(ny, nx, originNum, changeNum);
        }
    }
}
