import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2448 {

    static int N;
    static final int MAX_INPUT = 3 * 1024;
    static char map[][] = new char[MAX_INPUT][MAX_INPUT * 2 - 1];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());

        mapSetting();

        dfs(N, 0, N - 1);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 2 * N - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void mapSetting() {
        for(int i = 0; i < N; i++) {
            Arrays.fill(map[i], ' ');
        }
    }

    private static void dfs(int len, int y, int x) {
        if(len == 3) {
            makeStar(y, x);
            return;
        }

        int nextLen = len / 2;
        dfs(nextLen, y, x); // 맨 위
        dfs(nextLen, y + nextLen, x - nextLen); // 왼쪽 밑
        dfs(nextLen, y + nextLen, x + nextLen); // 오른쪽 밑
    }

    private static void makeStar(int y, int x) {
        map[y][x] = '*';

        map[y + 1][x - 1] = '*';
        map[y + 1][x + 1] = '*';

        for(int i = 0; i < 5; i++) {
            map[y + 2][x - 2 + i] = '*';
        }
    }
}
