import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N, M, result;
    static int map[][];
    static boolean isAllMelted;
    static boolean visited[][];
    static int[][] delta = {{0,1},{1,0},{-1,0},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 다 녹을때까지 bfs
        while(!isAllMelted){
            isAllMelted = true;

            // 외부 공기와 내부 공기 분리
            bfs();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] != 1){
                        continue;
                    }

                    int cnt = 0; // 인접한 곳에 외부 공기가 2이상인지 체크
                    for (int d = 0; d < 4; d++) {
                        int nx = i + delta[d][0];
                        int ny = j + delta[d][1];

                        if(!(nx>=0 && ny>=0 && nx<N && ny<M)){
                            continue;
                        }

                        if(map[nx][ny] == 2){
                            cnt++;
                        }
                    }
                    if(cnt>=2){
                        map[i][j] = 0; //
                        isAllMelted = false; // 아직 치즈가 다 녹지 않았으면 반복문 다시 실행
                    }
                }
            }

            result ++;

        }

        System.out.println(result-1);
    }

    private static void bfs() {
        visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0});
        map[0][0] = 2; // 2 : 외부 공기

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int d = 0; d<4; d++){
                int nx = now[0] + delta[d][0];
                int ny = now[1] + delta[d][1];

                if(!(nx>=0 && ny>=0 && nx<N && ny<M)){
                    continue;
                }

                if(visited[nx][ny] || map[nx][ny] == 1) { // 1안에 있으면 내부 공기
                    continue;
                }

                visited[nx][ny] = true;
                map[nx][ny] = 2;
                q.offer(new int[]{nx,ny});
            }

        }
    }

}
