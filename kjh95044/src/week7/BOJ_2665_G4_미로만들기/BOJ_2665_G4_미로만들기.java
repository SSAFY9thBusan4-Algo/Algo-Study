package week7.BOJ_2665_G4_미로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2665_G4_미로만들기 {
    static int[][] map, visited;
    static int N;
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

        map = new int[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        // 처음 생각은 방문 체크를 하면서 만나는 흰방의 갯수를 세면 되나 생각했는데,
        // 그렇게 하면 최단 경로를 구할수는 있지만, 색을 바꾸는 최소값은 구할수 없었다.
        // 그래서 방문 체크를 할때 여기까지 색을 바꿔온 값중에 최소값을 저장해줬다.

        bfs();

        System.out.println(visited[N-1][N-1]);

    }


    private static void bfs() {

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0,0));
        visited[0][0] = 0;

        while(!q.isEmpty()){
            Point cur = q.poll();
            for(int d=0; d<4; d++){
                int nx = cur.x + delta[d][0];
                int ny = cur.y + delta[d][1];

                if(!(nx>=0 && ny>=0 && nx<N && ny<N)) {
                    continue;
                }

                if(map[nx][ny] == 1){ // 흰 방
                    // 흰 방일때는 색을 바꾸지 않고 이동
                    // 이동할 방이 현재 방보다 크면 현재 값으로 갱신해준다. (최소값)
                    if (visited[nx][ny] > visited[cur.x][cur.y]){
                        visited[nx][ny] = visited[cur.x][cur.y];
                        q.offer(new Point(nx,ny));
                    }
                }
                else { // 검은 방
                    // 검은 방은 흰방과 다르게 색을 바꾸고 이동 하기 때문에 +1 해준다.
                    // 이동할 방이 현재 방보다 크면 현재 값으로 갱신 (최소값)
                    if (visited[nx][ny] > visited[cur.x][cur.y] + 1){
                        visited[nx][ny] = visited[cur.x][cur.y] + 1;
                        q.offer(new Point(nx,ny));
                    }
                }

            }
        }
    }




}