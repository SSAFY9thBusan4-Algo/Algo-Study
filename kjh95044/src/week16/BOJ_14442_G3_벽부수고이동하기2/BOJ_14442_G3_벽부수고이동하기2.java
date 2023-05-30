import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K,map[][], result=-1;
    static boolean visited[][][];

    static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class InfoPoint{
        Point p;
        int wall;
        int cnt;

        public InfoPoint(Point p, int wall, int cnt) {
            this.p = p;
            this.wall = wall;
            this.cnt = cnt;
        }
    }



    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1][K+1];

        for(int i =1; i<=N; i++){
            String input = in.readLine();
            for(int j=1; j<=M; j++){
                map[i][j] = input.charAt(j-1) - '0';
            }
        }

        bfs();

        System.out.println(result);
    }

    private static void bfs() {
        Queue<InfoPoint> queue = new ArrayDeque<>();
        queue.offer(new InfoPoint(new Point(1,1), 0,1));

        while (!queue.isEmpty()){
            InfoPoint now = queue.poll();

            if(now.p.x == N && now.p.y == M){
                result = now.cnt;
                break;
            }

            for(int d=0; d<4; d++){
                int nx = now.p.x + delta[d][0];
                int ny = now.p.y + delta[d][1];

                if(!(nx>=1 && ny>=1 && nx<=N && ny<=M)) continue;

                if(map[nx][ny] == 0 && !visited[nx][ny][now.wall]){
                    visited[nx][ny][now.wall] = true;
                    queue.offer(new InfoPoint(new Point(nx,ny), now.wall, now.cnt+1));
                }

                else if(map[nx][ny]==1 && now.wall + 1 <= K && !visited[nx][ny][now.wall +1]){
                    visited[nx][ny][now.wall+1] = true;
                    queue.offer(new InfoPoint(new Point(nx,ny), now.wall+1, now.cnt+1));
                }
            }
        }
    }
}
