package week7.BOJ_2206_G3_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_G3_벽부수고이동하기 {

    static class Node {
        int x, y, dist;
        boolean hasBeenBroken;

        public Node(int x, int y, int dist, boolean hasBeenBroken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.hasBeenBroken = hasBeenBroken;
        }
    }

    static int[][] map;
    static boolean[][][] isVisited;
    static int N, M;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        // 일반적인 bfs만으로는 풀수 없다.
        // 벽을 부술경우 더 빠른 경우가 있기 때문이다.
        // 그래서 그냥 풀었을때는 틀렸다고 나왔다.
        // isVisited를 3차원으로 만들어줬고, 한칸은 벽이 부숴진적이 있는지 없는지를 체크했다.

        bfs();
        System.out.println(result);

    }

    static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();

        // 문제에서 시작하는 칸과 끝나는 칸도 포함해서 센다고 했기 때문에 dist 1부터 시작
        q.add(new Node(0, 0, 1, false));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                result = cur.dist;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = delta[d][0] + cur.x;
                int ny = delta[d][1] + cur.y;

                if (!(nx >= 0 && ny >= 0 && nx < N && ny < M)) {
                    continue;
                }

                if (map[nx][ny] == 0) { // 벽이 아니면
                    if (!cur.hasBeenBroken && !isVisited[0][nx][ny]) { // 부숴진적 없음
                        q.add(new Node(nx, ny, cur.dist + 1, false));
                        isVisited[0][nx][ny] = true;
                    } else if (cur.hasBeenBroken && !isVisited[1][nx][ny]) { // 부숴진적 있음
                        q.add(new Node(nx, ny, cur.dist + 1, true));
                        isVisited[1][nx][ny] = true; // 부숴진적 있고, 방문

                    }
                } else { // 벽이면
                    if (!cur.hasBeenBroken) { // 벽이 부숴진적이 없으면 부순다.
                        q.add(new Node(nx, ny, cur.dist + 1, true));
                        isVisited[1][nx][ny] = true; // 방문 체크, 1은 벽을 부수고 이동
                    }
                }

            }
        }

        result = -1;
    }

}