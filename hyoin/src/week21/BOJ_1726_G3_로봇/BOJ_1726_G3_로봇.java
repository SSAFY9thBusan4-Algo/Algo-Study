package week21.BOJ_1726_G3_로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1726_G3_로봇 {

    public static int M;
    public static int N;
    public static int[][] map;
    public static Node start;
    public static Node end;
    public static int[] dx = {0, 0, 0, 1, -1}; // 1번째 index부터 동서남북
    public static int[] dy = {0, 1, -1, 0, 0};

    public static class Node {
        int x;
        int y;
        int direction;

        public Node(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M+1][N+1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(in.readLine());
        start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(in.readLine());
        end = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] isVisited = new boolean[M+1][N+1][5]; // 동서남북 방향 고려
        queue.offer(start);
        isVisited[start.x][start.y][start.direction] = true;
        int depth=0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                int curX = cur.x;
                int curY = cur.y;
                int curD = cur.direction;

                if(curX == end.x && curY == end.y && curD== end.direction){ // 종료조건
                    return depth;
                }

                int nx = curX;
                int ny = curY;
                // 명령1 Go(1칸, 2칸, 3칸)
                for (int j = 0; j < 3; j++) {
                    nx += dx[curD];
                    ny += dy[curD];

                    if (nx >= 1 && nx <= M && ny >= 1 && ny <= N) {
                        if(map[nx][ny]!=0){ // 다음 좌표의 값이 0일 때 다음칸은 볼필요 없음
                            break;
                        }
                        else {
                            if(!isVisited[nx][ny][curD]) { // 방문한 적이 없을 때 큐에 넣기
                                queue.offer(new Node(nx, ny, curD));
                                isVisited[nx][ny][curD] = true;
                            }
                        }
                    }
                    else { // 범위를 벗어났을 때
                        break;
                    }
                }

                // 명령2 회전
                // 동(1), 서(2), 남(3), 북(4)
                // 동 : 서를 제외 => 3,4
                // 서 : 동을 제외 => 3,4
                // 남 : 북을 제외 => 1,2
                // 북 : 남을 제외 => 1,2
                if(curD == 1 || curD == 2){ // 동, 서 방향일 때 남,북 방향 추가
                    if(!isVisited[curX][curY][3]) {
                        queue.offer(new Node(curX, curY, 3));
                        isVisited[curX][curY][3]=true;
                    }

                    if(!isVisited[curX][curY][4]) {
                        queue.offer(new Node(curX, curY, 4));
                        isVisited[curX][curY][4]=true;
                    }
                }
                else{ // 남, 북 방향일 때 동, 서 방향 추가
                    if(!isVisited[curX][curY][1]) {
                        queue.offer(new Node(curX, curY, 1));
                        isVisited[curX][curY][1]=true;
                    }
                    if(!isVisited[curX][curY][2]) {
                        queue.offer(new Node(curX, curY, 2));
                        isVisited[curX][curY][2]=true;
                    }
                }

            }
            depth++;
        }
        return -1;
    }
}
