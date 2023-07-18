import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][], min = Integer.MAX_VALUE;
    static boolean[][] visited;
    static int[][] delta = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 밖으로 벗어나면 꽃이 죽어 버리니
        // 1,1 ~ N-2,N-2 까지만 살펴보면 된다.
        // 순조부 (조합) - 순서는 상관 없으니까

        comb(0, 0);
        System.out.println(min);
    }

    private static void comb(int cnt, int sum) {
        if (cnt == 3) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                int isDup = isDuplicated(i,j);
                if(isDup != -1){
                    checkFlower(i,j);
                    comb(cnt+1, sum + isDup);
                    uncheckFlower(i,j);
                }
            }
        }
    }

    private static int isDuplicated(int x, int y){
        int isDup = 0;

        for (int d = 0; d < 5; d++) {
            int nx = x + delta[d][0];
            int ny = y + delta[d][1];

            if(visited[nx][ny]){
                isDup = -1;
                break;
            }else{
                isDup += map[nx][ny];
            }
        }
        return isDup;
    }

    private static void checkFlower(int x, int y){
        for (int d = 0; d < 5; d++) {
            int nx = x + delta[d][0];
            int ny = y + delta[d][1];

            visited[nx][ny] = true;
        }
    }

    private static void uncheckFlower(int x, int y){
        for (int d = 0; d < 5; d++) {
            int nx = x + delta[d][0];
            int ny = y + delta[d][1];

            visited[nx][ny] = false;
        }
    }
}
