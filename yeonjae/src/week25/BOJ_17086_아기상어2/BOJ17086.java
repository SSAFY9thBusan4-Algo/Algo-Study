package week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ17086 {

    static int N, M, maxDis;
    static int[][] map, visited;
    static String[] ss;
    static int[] dy = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dx = {0, 1, 0, -1, -1, 1, -1, 1};
    static List<Pair> sharks = new ArrayList<>();


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

        map = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(ss[j]);
                if(map[i][j] == 1) {
                    sharks.add(new Pair(i, j));
                }
            }
        }

        for(Pair shark : sharks) {
            int sharkY = shark.y;
            int sharkX = shark.x;

            Queue<Pair> q = new ArrayDeque<>();
            visited[sharkY][sharkX] = 1;
            q.add(new Pair(sharkY, sharkX));
            while(!q.isEmpty()) {
                Pair here = q.poll();
                int y = here.y;
                int x = here.x;

                for(int i = 0; i < 8; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny < 0 || ny >= N || nx < 0 || nx >= M)
                        continue;
                    if(map[ny][nx] == 1)
                        continue;
                    if(visited[ny][nx] != 0 && visited[ny][nx] <= visited[y][x] + 1)
                        continue;

                    visited[ny][nx] = visited[y][x] + 1;
                    q.add(new Pair(ny, nx));
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                maxDis = Math.max(maxDis, visited[i][j] - 1);
            }
        }

        System.out.println(maxDis);
    }
}