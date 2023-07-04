import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14938_G4_서강그라운드 {
    static int n, m, r, t[];
    static int[][] dist;
    static int INF = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 지역의 개수 n
        m = Integer.parseInt(st.nextToken()); // 수색범위 m
        r = Integer.parseInt(st.nextToken()); // 길의 개수 r

        t = new int[n+1];
        dist = new int[n+1][n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            t[i] = Integer.parseInt(st.nextToken());
        }

        // dist 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                }else{
                    dist[i][j] = INF;
                }
            }
        }

        // 양끝 지역 A,B와 길의 길이가 주어진다. ex) 1 4 5
        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            dist[A][B] = len;
            dist[B][A] = len;
        }

        // 플로이드 워샬 알고리즘
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                for(int k=1; k<=n; k++){
                    if (dist[j][i] != INF && dist[i][k] != INF) { // 길이 없는 경우는 빼줘야한다.
                        dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                    }
                }
            }
        }

        int result = 0;
        for(int i=1; i<=n; i++){
            int cnt = 0;
            for(int j=1; j<=n; j++){
                if(dist[i][j] <= m){
                    cnt += t[j];
                }
            }

            result = Math.max(result, cnt);
        }

        System.out.println(result);
    }
}
