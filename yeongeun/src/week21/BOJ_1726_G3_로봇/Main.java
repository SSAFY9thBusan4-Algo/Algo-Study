pakage src.week21.BOJ_1726_G3_로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static class Point{
        int x, y, d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    private static int N, M;
    private static final int[][] delta = {{},{0,1},{0,-1},{1,0},{-1,0}}; //동서남북
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        char[][] map = new char[N][];
        for(int i = 0 ; i < N ; i++) {
            map[i] = br.readLine().replace(" ","").toCharArray();
        }

        in = br.readLine().split(" ");
        Point start = new Point(Integer.parseInt(in[0])-1, Integer.parseInt(in[1])-1, Integer.parseInt(in[2]));
        in = br.readLine().split(" ");
        Point end = new Point(Integer.parseInt(in[0])-1, Integer.parseInt(in[1])-1, Integer.parseInt(in[2]));

        int result = find(map, start, end);
        System.out.println(result);
    }

    private static int find(char[][] map, Point start, Point end) {

        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(start);

        int[][][] visited = new int[N][M][5];
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            int cnt = visited[cur.x][cur.y][cur.d];

            if(cur.x == end.x && cur.y == end.y && cur.d == end.d) return cnt;

            // 방향 바꾸기
            int r = right(cur.d);
            if(visited[cur.x][cur.y][r] == 0) {
                visited[cur.x][cur.y][r] = cnt+1;
                queue.offer(new Point(cur.x, cur.y, r));
            }
            int l = left(cur.d);
            if(visited[cur.x][cur.y][l] == 0) {
                visited[cur.x][cur.y][l] = cnt+1;
                queue.offer(new Point(cur.x, cur.y, l));
            }

            // 3까지 현재 방향으로 이동하기
            int k = 0;
            int dx = cur.x;
            int dy = cur.y;
            while(++k <= 3) {
                dx += delta[cur.d][0];
                dy += delta[cur.d][1];

                if(dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] == '0') {
                    if(visited[dx][dy][cur.d] == 0) {
                        visited[dx][dy][cur.d] = cnt+1;
                        queue.offer(new Point(dx, dy, cur.d));
                    }
                }
                else break;
            }

        }

        // 항상 도착 가능하다.
        return -1;
    }

    private static int right(int d) {
        if(d == 1) return 3;
        else if(d == 2) return 4;
        else if(d == 3) return 2;
        else return 1;
    }
    private static int left(int d) {
        if(d == 1) return 4;
        else if(d == 2) return 3;
        else if(d == 3) return 1;
        else return 2;
    }
}
