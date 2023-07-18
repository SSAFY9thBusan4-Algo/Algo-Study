import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int[][] delta = {{0,1},{1,0},{0,-1},{-1,0}}; // 동남서북
    static int endX, endY, endDir, N, M;
    static class Point{
        int x;
        int y;
        int dir;
        int cnt;

        public Point(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 명령 1. Go k: k는 1, 2 또는 3일 수 있다.
        // 현재 향하고 있는 방향으로 k칸 만큼 움직인다.

        // 명령 2. Turn dir: dir은 left 또는 right 이며,
        // 각각 왼쪽 또는 오른쪽으로 90° 회전한다.

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][M+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int startDir = changeDir(Integer.parseInt(st.nextToken()));
        // 문제에서는 동서남북이지만, 나는 동남서북으로 설정했기 때문에 바꿔준다.


        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());
        endDir = changeDir(Integer.parseInt(st.nextToken()));

        System.out.println(bfs(map, new Point(startX, startY, startDir, 0)));
    }

    static int changeDir(int dir){
        // 문제에서 1부터 오기때문에 0번째는 -1로 설정
        int[] arr = {-1,0,2,1,3};
        return arr[dir];
    }

    private static int bfs(int[][] map, Point p) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(p);

        boolean[][][] visited = new boolean[N+1][M+1][4];
        visited[p.x][p.y][p.dir] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            if(cur.x== endX && cur.y == endY && cur.dir == endDir)
                return cur.cnt;

            for(int n=1; n<=3; n++){

                int nx = cur.x + delta[cur.dir][0] * n;
                int ny = cur.y + delta[cur.dir][1] * n;

                if(!(nx>=1 && ny>=1 && nx<=N && ny<=M)) continue;
                if(map[nx][ny] ==1) break;

                if(!visited[nx][ny][cur.dir]){
                    visited[nx][ny][cur.dir] = true;
                    queue.offer(new Point(nx,ny, cur.dir, cur.cnt+1));
                }


            }

            if(!visited[cur.x][cur.y][(cur.dir+3)%4]){
                visited[cur.x][cur.y][(cur.dir+3)%4] = true;
                queue.offer(new Point(cur.x,cur.y,(cur.dir+3)%4, cur.cnt+1));
            }

            if(!visited[cur.x][cur.y][(cur.dir+1)%4]){
                visited[cur.x][cur.y][(cur.dir+1)%4] = true;
                queue.offer(new Point(cur.x,cur.y,(cur.dir+1)%4, cur.cnt+1));
            }


        }

        return -1;
    }
}
