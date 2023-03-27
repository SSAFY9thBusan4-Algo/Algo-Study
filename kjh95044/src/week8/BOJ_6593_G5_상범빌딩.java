import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, C, R, depth;
    static char [][][] map;
    static boolean [][][] visited;
    static boolean isFound;
    static int[][] delta = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
    static int[] start = new int[3];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            depth = 0;
            isFound = false;
            map = new char[N][C][R];
            visited = new boolean[N][C][R];

            if(N==0 && C==0 && R==0){
                break;
            }

            for (int n = 0; n < N; n++) {
                for (int c = 0; c < C; c++) {
                    String input = br.readLine();
                    for (int r = 0; r < R; r++) {
                        map[n][c][r] = input.charAt(r);
                        if(input.charAt(r)=='S'){
                            start[0] = n;
                            start[1] = c;
                            start[2] = r;
                        }
                    }
                }
                br.readLine(); // 빈줄 제거
            }

            bfs();
            if(isFound)
                sb.append("Escaped in ").append(depth-1).append(" minute(s).").append("\n");
            else{
                sb.append("Trapped!").append("\n");
            }

        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(start);
        visited[start[0]][start[1]][start[2]] = true;

        while(!q.isEmpty()){
            depth ++;
            int size = q.size();


            while(size-->0) {
                int[] now = q.poll();
                if(map[now[0]][now[1]][now[2]] == 'E'){
                    isFound = true;
                    q.clear();
                    break;
                }


                for (int d = 0; d < 6; d++) {
                    int nn = now[0] + delta[d][0];
                    int nx = now[1] + delta[d][1];
                    int ny = now[2] + delta[d][2];

                    if (!(nn >= 0 && nx >= 0 && ny >= 0 && nn < N && nx < C && ny < R)) {
                        continue;
                    }

                    if(visited[nn][nx][ny] || map[nn][nx][ny] == '#'){
                        continue;
                    }

                    q.offer(new int[]{nn,nx,ny});
                    visited[nn][nx][ny] = true;

                }
            }


        }
    }
}
