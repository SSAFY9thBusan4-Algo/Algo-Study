import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = {-2, -2, 0, 0, 2, 2};
        int[] dy = {-1, 1, -2, 2, -1, 1};
        int result = -1;

        int N = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split(" ");
        int r1 = Integer.parseInt(split[0]);
        int c1 = Integer.parseInt(split[1]);
        int r2 = Integer.parseInt(split[2]);
        int c2 = Integer.parseInt(split[3]);

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        queue.offer(new int[] {r1,c1, 0});
        visit[r1][c1] = true;

        out:
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            for(int d=0; d<6;d++) {
                int X = x + dx[d];
                int Y = y + dy[d];
                if(X == r2 && Y == c2){
                    result = now[2]+1;
                    break out;
                }
                if (X < 0 || X >= N || Y < 0 || Y >= N || visit[X][Y]) {
                    continue;
                }
                queue.offer(new int[]{X, Y, now[2]+1});
                visit[X][Y] = true;
            }
        }
        System.out.println(result);
    }
}
