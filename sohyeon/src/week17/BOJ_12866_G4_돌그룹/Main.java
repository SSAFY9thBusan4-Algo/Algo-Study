package week17.BOJ_12866_G4_돌그룹;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {

    static int A, B, C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        System.out.println(bfs(A, B, C) ? 1 : 0);
    }

    private static boolean bfs(int a, int b, int c) {

        if ((a + b + c) % 3 != 0) {
            return false;
        }

        boolean[][] visited = new boolean[1501][1501];
        visited[a][b] = true;
        Queue queue = new ArrayDeque<>();
        queue.offer(new int[] { a, b, c });

        while (!queue.isEmpty()) {
            int[] abc = (int[]) queue.poll();
            int cur_a = abc[0];
            int cur_b = abc[1];
            int cur_c = abc[2];

            if (cur_a == cur_b && cur_b == cur_c) {
                return true;
            }

            // a,b 비교
            if (cur_a > cur_b && !visited[cur_a - cur_b][cur_b * 2]) {
                visited[cur_a - cur_b][cur_b * 2] = true;
                queue.offer(new int[] { cur_a - cur_b, cur_b * 2, cur_c });
            } else if (cur_a < cur_b && !visited[cur_a * 2][cur_b - cur_a]) {
                visited[cur_a * 2][cur_b - cur_a] = true;
                queue.offer(new int[] { cur_a * 2, cur_b - cur_a, cur_c });
            }

            // b,c 비교
            if (cur_b > cur_c && !visited[cur_a][cur_b - cur_c]) {
                visited[cur_a][cur_b - cur_c] = true;
                queue.offer(new int[] { cur_a, cur_b - cur_c, cur_c * 2 });
            } else if (cur_b < cur_c && !visited[cur_a][cur_b * 2]) {
                visited[cur_a][cur_b * 2] = true;
                queue.offer(new int[] { cur_a, cur_b * 2, cur_c - cur_b });
            }

            // a,c 비교
            if (cur_a > cur_c && !visited[cur_a - cur_c][cur_b]) {
                visited[cur_a - cur_c][cur_b] = true;
                queue.offer(new int[] { cur_a - cur_c, cur_b, cur_c * 2 });
            } else if (cur_a < cur_c && !visited[cur_a * 2][cur_b]) {
                visited[cur_a * 2][cur_b] = true;
                queue.offer(new int[] { cur_a * 2, cur_b, cur_c - cur_a });
            }
        }

        return false;
    }
}
