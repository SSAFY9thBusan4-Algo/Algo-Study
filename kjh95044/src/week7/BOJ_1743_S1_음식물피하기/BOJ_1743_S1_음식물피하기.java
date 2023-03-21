package week7.BOJ_1743_S1_음식물피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1743_S1_음식물피하기 {
    static int[][] map;
    static int N, M, K, result;
    static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int x=0;
        int y=0;

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            map[x-1][y-1] = 1;
        }

        for(int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==1) {
                    dfs(i,j);
                    result = Math.max(result, cnt);
                    cnt = 0;
                }
            }
        }


        System.out.println(result);
    }


    static int cnt;
    private static void dfs(int x, int y) {
        cnt ++;
        map[x][y] = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + delta[d][0];
            int ny = y + delta[d][1];

            if (!(nx >= 0 && ny >= 0 && nx < N && ny < M)) {
                continue;
            }

            if(map[nx][ny] == 1){
                dfs(nx,ny);
            }
        }
    }
}