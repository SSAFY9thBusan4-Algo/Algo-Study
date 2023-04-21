import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        
        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            String[] cmd = new String[10000];
            boolean[] visited = new boolean[10000];
            Queue<Integer> q = new ArrayDeque<>();

            visited[A] = true;
            Arrays.fill(cmd, "");
            q.offer(A);
            while(!q.isEmpty() && !visited[B]){
              /*
                D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
                S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
                L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
                R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
              */
                int now = q.poll();
                // 명령어 실행
                int D = (2*now)%10000;
                int S = (now == 0) ? 9999:now-1;
                int L = (now%1000) * 10 + now/1000;
                int R = (now%10) * 1000 + now/10;

                if(!visited[D]){
                    q.offer(D);
                    visited[D]=true;
                    cmd[D]=cmd[now] + "D";
                }

                if(!visited[S]){
                    q.offer(S);
                    visited[S]=true;
                    cmd[S]=cmd[now] + "S";
                }
                if(!visited[L]){
                    q.offer(L);
                    visited[L]=true;
                    cmd[L]=cmd[now] + "L";
                }
                if(!visited[R]){
                    q.offer(R);
                    visited[R]=true;
                    cmd[R]=cmd[now] + "R";
                }

            }
            sb.append(cmd[B]).append("\n");
        }
        System.out.println(sb);
    }
}
