import java.io.*;
import java.util.*;

public class BOJ_1726_G3_로봇 {
    //클래스 생성
    public static class Pos {
        int r;
        int c;
        int dir;
        int cmdCnt;

        public Pos(int r, int c, int dir, int cmdCnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cmdCnt = cmdCnt;
        }
    }

    static int M, N;
    static int map[][];

    //BFS 위한 변수
    static boolean isVisited[][][];
    static int dr[] = {0, 0, 0, 1, -1}; // 동 서 남 북
    static int dc[] = {0, 1, -1, 0, 0};
    static Pos departPos, arrivePos;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N]; // map 초기화
        isVisited = new boolean[M][N][5];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<2; i++) {
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            if(i==0) departPos = new Pos(r, c, dir, 0);
            else arrivePos = new Pos(r, c, dir, 0);
        }

        BFS();

    }

    private static void BFS() {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(departPos);
        isVisited[departPos.r][departPos.c][departPos.dir] = true;

        while(!queue.isEmpty()) {
            Pos curPos = queue.poll();

            if(curPos.r == arrivePos.r && curPos.c == arrivePos.c && curPos.dir == arrivePos.dir) {
                System.out.println(curPos.cmdCnt);
                break;
            }

            //Go 명령
            for(int i=1; i<=3; i++) {
                int nr = curPos.r + (dr[curPos.dir] * i);
                int nc = curPos.c + (dc[curPos.dir] * i);

                if(0 <= nr && nr < M && 0 <= nc && nc < N) {
                    if(map[nr][nc] == 0) {
                        if(!isVisited[nr][nc][curPos.dir]) {
                            queue.offer(new Pos(nr, nc, curPos.dir, curPos.cmdCnt + 1));
                            isVisited[nr][nc][curPos.dir] = true;
                        }
                    }
                    else break;
                }
            }


            // Turn 명령
            for(int i=1; i<=4; i++) {
                if(curPos.dir != i && !isVisited[curPos.r][curPos.c][i]) {
                    int turnCnt = 1;
                    switch(curPos.dir) {
                        case 1: // 방향이 동쪽일 경우
                            if(i == 2) turnCnt++;
                            break;
                        case 2: // 방향이 서쪽일 경우
                            if(i==1) turnCnt++;
                            break;
                        case 3:  // 방향이 남쪽일 경우
                            if(i==4) turnCnt++;
                            break;
                        case 4: // 방향이 북쪽일 경우
                            if(i==3) turnCnt++;
                            break;
                    }

                    queue.offer(new Pos(curPos.r, curPos.c, i, curPos.cmdCnt + turnCnt));
                    isVisited[curPos.r][curPos.c][i] = true;
                }
            }
        }
    }

}
