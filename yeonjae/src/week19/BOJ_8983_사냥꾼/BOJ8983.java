import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ8983 {

    static int M, N, L, ansCnt, y, x, left, right, mid, dis;
    static int[] shootPosition;
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        M = Integer.parseInt(ss[0]);
        N = Integer.parseInt(ss[1]);
        L = Integer.parseInt(ss[2]);

        shootPosition = new int[M];
        ss = in.readLine().split(" ");
        for(int i = 0; i < M; i++) {
            shootPosition[i] = Integer.parseInt(ss[i]);
        }
        Arrays.sort(shootPosition);

        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            x = Integer.parseInt(ss[0]);
            y = Integer.parseInt(ss[1]);
            if(canCatch(y, x)) {
                ansCnt++;
            }
        }

        System.out.println(ansCnt);
    }

    private static boolean canCatch(int y, int x) {

        left = 0;
        right = M - 1;
        while(left <= right) {
            mid = (left + right) / 2;

            dis = y + Math.abs(x - shootPosition[mid]);

            // 거리 안에 들어온 경우
            if(dis <= L) {
                return true;
            }
            // 거리 안에 들어오지 못한 경우
            else {
                // 수평 거리가 없음에도 불구하고(최선의 방법) 거리 안에 못 들어온 경우
                if(shootPosition[mid] == x) {
                    break;
                }
                else if(shootPosition[mid] < x) {
                    left = mid + 1;
                }
                else if(shootPosition[mid] > x) {
                    right = mid - 1;
                }
            }
        }

        return false;
    }
}
