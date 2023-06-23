import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2666 {

    static int N, OPEN1, OPEN2, M, minMoveCnt = Integer.MAX_VALUE;
    static int[] toUse;
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine()); // 벽장의 개수
        ss = in.readLine().split(" ");
        OPEN1 = Math.min(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])); // 열려 있는 첫 번째 벽장 (왼쪽)
        OPEN2 = Math.max(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])); // 열려 있는 두 번째 벽장 (오른쪽)

        M = Integer.parseInt(in.readLine()); // 사용할 벽장의 개수
        toUse = new int[M + 1];
        for(int i = 0; i < M; i++) {
            toUse[i] = Integer.parseInt(in.readLine());
        }

        bf(0, OPEN1, OPEN2, 0);

        System.out.println(minMoveCnt);
    }

    private static void bf(int idx, int open1, int open2, int moveCnt) {
        if(idx == M) {
            minMoveCnt = Math.min(minMoveCnt, moveCnt);
            return;
        }

        int nextPosition = toUse[idx];

        // open1이 갈 수 있는 경우 (nextPosition의 위치가 open2 이상이 아니면 갈 수 있음)
        if(!(open2 <= nextPosition)) {
            bf(idx + 1, nextPosition, open2, moveCnt + Math.abs(open1 - nextPosition));
        }

        // open2가 갈 수 있는 경우 (nextPosition의 위치가 open1 이하가 아니면 갈 수 있음)
        if(!(nextPosition <= open1)) {
            bf(idx + 1, open1, nextPosition, moveCnt + Math.abs(open2 - nextPosition));
        }
    }
}
