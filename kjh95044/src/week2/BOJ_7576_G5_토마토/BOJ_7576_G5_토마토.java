package week2.BOJ_7576_G5_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_7576_G5_토마토 {
    static StringBuilder sb = new StringBuilder();
    static int M;
    static int N;
    static int[][] map;

    static int totalTomato;
    static int ripeTomatoCnt;
    static int result;
    static boolean[][] isChanged;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static Deque<int[]> q = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        M = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        map = new int[N][M];
        totalTomato = N * M; // 토마토 개수
        ripeTomatoCnt = 0; // 익은 토마토 개수

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;

                if (input == -1) {
                    totalTomato--; // 토마토 존재 하지 않는 만큼 빼준다.
                } else if (input == 1) {
                    q.offer(new int[]{i, j});
                    ripeTomatoCnt++; // 익은 토마토 개수
                }
            }
        }

        // 풀이 시작
        solution();
        sb.append(result);
        System.out.println(sb);
    }

    private static void solution() {
        // 처음부터 토마토 갯수와 익은 토마토 갯수가 같으면 0
        if (totalTomato == ripeTomatoCnt) {
            return;
        }
        // 처음부터 익은 토마토가 하나도 없으면 -1
        if (ripeTomatoCnt == 0) {
            result = -1;
            return;
        }

        // 큐에 아무것도 없을 때 까지 반복문 실행
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                // 익은 토마토 주변에 익지 않은 토마토를 찾는다.
                if ((nx >= 0 && ny >= 0) && (nx < N && ny < M) && map[nx][ny] == 0) {
                    map[nx][ny] = map[x][y] + 1; // 큐에 넣기 전에 이전 토마토 값 +1을 넣어줘서 result 값을 구할때 썼다.
                    q.offer(new int[]{nx, ny});

                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { // 다 돌았는데도 익지 않은 토마토가 있으면 -1 출력
                    result = -1;
                    return;
                } else {
                    result = Math.max(result, map[i][j] - 1);
                }
            }
        }
//        // 그냥 4방 탐색 브루트 포스로 했더니 시간초과가 떴다.
//
//        int changedRipeTomatoCnt = ripeTomatoCnt;
//        // 하루 지났는데도 익은 토마토 갯수가 이전과 같으면 탈출
//        do {
//            ripeTomatoCnt = changedRipeTomatoCnt;
//            // 이번에 익은 토마토인지 확인, 이번에 익은 토마토면 다른 토마토를 익히지 못함.
//            isChanged = new boolean[N][M];
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    if(map[i][j] == 1 && !isChanged[i][j] ){
//                        for (int k = 0; k < 4; k++) {
//                            int nx = dx[k] + i;
//                            int ny = dy[k] + j;
//
//                            if((nx >= 0 && ny >=0) && (nx<N && ny<M) && map[nx][ny] == 0){
//                                isChanged[nx][ny] = true;
//                                changedRipeTomatoCnt ++;
//                                map[nx][ny] = 1;
//                            }
//                        }
//                    }
//
//                }
//            }
//            result++;
//        }
//        while (changedRipeTomatoCnt != ripeTomatoCnt);
//        result--;
//
//        // 다 돌았는데 다 안 익었으면 -1
//        if(totalTomato != ripeTomatoCnt){
//            result = -1;
//        }

    }


}