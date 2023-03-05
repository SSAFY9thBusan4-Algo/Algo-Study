package week5.SWEA_1953_역테_탈주범검거;
import java.util.*;
import java.io.*;

public class SWEA_1953_역테_탈주범검거 {
	static StringBuilder sb = new StringBuilder();
    static int N, M, R, C, L, map[][];
    static int[][] delta = new int[4][2];

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");

            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 풀이

            if(L==1){
                sb.append(1).append("\n");
            }
            else {
                bfs();
            }
        }
        System.out.println(sb);
    }

    static Queue<Point> queue = new ArrayDeque<>();
    private static void bfs() {
        boolean[][] visited = new boolean[N][M];
        visited[R][C] = true;

        queue.clear();
        queue.offer(new Point(R, C));
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point p = queue.poll();
                int dCnt = 2;
                switch (map[p.x][p.y]) {
                    case 1: // 상하좌우
                        delta = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                        dCnt = 4;
                        break;
                    case 2: // 상하
                        delta = new int[][]{{-1,0},{1,0}};
                        break;
                    case 3: // 좌우
                        delta = new int[][]{{0,-1},{0,1}};
                        break;
                    case 4: // 상우
                        delta = new int[][]{{-1,0},{0,1}};
                        break;
                    case 5: // 하우
                        delta = new int[][]{{1,0},{0,1}};
                        break;
                    case 6: // 하좌
                        delta = new int[][]{{1,0},{0,-1}};
                        break;
                    case 7: // 상좌
                        delta = new int[][]{{-1,0},{0,-1}};
                        break;
                }

                for (int d = 0; d < dCnt; d++) {
                    search(visited, p, d);
                }

            }
            // depth가 탈출 소요시간 L을 넘으면 종료
            depth++;
            if (depth >= L) {
                break;
            }
        }

        int result = 0;
        for (boolean[] visitedX:visited
             ) {
            for (boolean v: visitedX
                 ) {
                if(v){
                    result++;
                }
            }

        }

        sb.append(result).append("\n");
    }


    private static void search (boolean[][] visited, Point p, int d) {
        // 아래로 갔을땐 위 방향을 가지는 터널이 있어야함
        // 왼쪽으로 갔을땐 오른쪽 방향을 가지는 터널이 있어야함
        // 오른쪽으로 갔을땐 왼쪽 방향을 가지는 터널이 있어야함
        // 위로 갔을땐 아래 방향을 가지는 터널이 있어야함

        // 예를 들어
        // 아래 쪽으로 가면 1, 2, 4, 7만 가능
        // 왼쪽으로 가면  1, 3, 4, 5
        // 오른쪽으로 가면 1, 3, 6, 7
        // 위쪽으로 가면 1, 2, 5, 6

        int nx = p.x + delta[d][0];
        int ny = p.y + delta[d][1];

        if (!(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny]!=0)) {
            return;
        }
        int val = map[nx][ny];
        if (visited[nx][ny]) {
            return;
        }

        // 아래쪽인데 1, 2, 4, 7가 아니면 탈출
        if(delta[d][0] == 1 && delta[d][1] == 0 &&
                val!=1 && val!=2 && val!=4 && val!=7){
            return;
        }
        // 왼쪽인데 1, 3, 4, 5가 아니면 탈출
        if(delta[d][0] == 0 && delta[d][1] == -1 &&
                val!=1 && val!=3 && val!=4 && val!=5){
            return;
        }
        // 오른쪽인데 1, 3, 6, 7이 아니면 탈출
        if(delta[d][0] == 0 && delta[d][1] == 1 &&
                val!=1 && val!=3 && val!=6 && val!=7){
            return;
        }
        // 위쪽인데 1, 2, 5, 6이 아니면 탈출
        if(delta[d][0] == -1 && delta[d][1] == 0 &&
                val!=1 && val!=2 && val!=5 && val!=6){
            return;
        }

        visited[nx][ny] = true;
        queue.offer(new Point(nx, ny));
    }
}
