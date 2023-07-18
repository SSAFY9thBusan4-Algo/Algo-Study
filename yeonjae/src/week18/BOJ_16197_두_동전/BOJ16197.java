import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ16197 {

    static int N, M, minCnt;
    static Stones stones = new Stones();
    static String[] ss;
    static int[][] a;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static class Stones {
        int y1, x1;
        int y2, x2;
        int moveCnt;

        public Stones() { }

        public Stones(int y1, int x1, int y2, int x2, int moveCnt) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.moveCnt = moveCnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        a = new int[N][M];
        int coinCnt = 0;
        for(int i = 0; i < N; i++) {
            ss = in.readLine().split("");
            for(int j = 0; j < M; j++) {
                // 동전
                if(ss[j].equals("o")) {
                    if(coinCnt == 0) {
                        stones.y1 = i;
                        stones.x1 = j;
                        coinCnt++;
                    }
                    else {
                        stones.y2 = i;
                        stones.x2 = j;
                    }
                    a[i][j] = 0;
                }
                // 빈칸
                else if(ss[j].equals(".")) {
                    a[i][j] = 0;
                }
                // 벽
                else if(ss[j].equals("#")){
                    a[i][j] = 1;
                }
            }
        }

        // BFS
        Queue<Stones> q = new ArrayDeque<>();
        q.add(stones);

        while(!q.isEmpty()) {
            Stones nowStones = q.poll();
            int nowY1 = nowStones.y1;
            int nowX1 = nowStones.x1;
            int nowY2 = nowStones.y2;
            int nowX2 = nowStones.x2;
            int nowMoveCnt = nowStones.moveCnt;
            // 이미 10번 움직인 경우 (더 이상 실행해도 소용없음)
            if(nowMoveCnt == 10)
                break;

            for(int i = 0; i < 4; i++) {
                int dropCnt = 0;
                // 동전1
                int nextY1 = nowY1 + dy[i];
                int nextX1 = nowX1 + dx[i];
                if(nextY1 < 0 || nextX1 < 0 || nextY1 >= N || nextX1 >= M) {
                    dropCnt++;
                }
                else {
                    // 벽인 경우 원복
                    if(a[nextY1][nextX1] == 1) {
                        nextY1 = nowY1;
                        nextX1 = nowX1;
                    }
                }

                // 동전2
                int nextY2 = nowY2 + dy[i];
                int nextX2 = nowX2 + dx[i];

                if(nextY2 < 0 || nextX2 < 0 || nextY2 >= N || nextX2 >= M) {
                    dropCnt++;
                }
                else {
                    // 벽인 경우 원복
                    if(a[nextY2][nextX2] == 1) {
                        nextY2 = nowY2;
                        nextX2 = nowX2;
                    }
                }

                // BFS를 계속 진행해야 하는 경우
                if(dropCnt == 0) {
                    q.add(new Stones(nextY1, nextX1, nextY2, nextX2, nowMoveCnt + 1));
                }
                // 최소 횟수
                else if(dropCnt == 1) {
                    minCnt = nowMoveCnt + 1;
                    break;
                }
                // Queue에 넣을 필요 없음 (굳이 적을 필요는 없지만 구분을 위해서 적음)
                else if (dropCnt == 2) {
                    continue;
                }
            }

            if(minCnt > 0)
                break;
        }

        if (minCnt == 0 || minCnt > 10)
            System.out.println(-1);
        else {
            System.out.println(minCnt);
        }
    }
}
