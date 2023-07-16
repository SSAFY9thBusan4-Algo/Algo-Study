import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();

        boolean[][] visit = new boolean[2001][2001];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 0, 0}); //지금 이모티콘 수, 클립보드 이모티콘 수, 연산 횟수
        visit[1][0] = true;

        int result = 0;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int now = current[0];
            int save = current[1];
            int cnt = current[2];

            if (now == S) {
                result = cnt;
                break;
            }

            //1번
            if (now > 0 && !visit[now][now]) {
                q.offer(new int[]{now, now, cnt + 1});
                visit[now][now] = true;
            }

            //2번
            if (save > 0 && now+save <2000 && !visit[now + save][save]) {
                q.offer(new int[]{now + save, save, cnt + 1});
                visit[now + save][save] = true;
            }

            //3번
            if (now > 0 && !visit[now - 1][save]) {
                q.offer(new int[]{now - 1, save, cnt + 1});
                visit[now - 1][save] = true;
            }
        }

        System.out.println(result);
    }
}
