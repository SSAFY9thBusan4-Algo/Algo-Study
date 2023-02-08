package week1.BOJ_6064_S1_카잉달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int gcd(int x, int y) {
        while(y!=0) {
            int tmp = x % y;
            x = y;
            y = tmp;
        }
        return x;
    }

    public static int lcm(int x, int y) {
        return x * y / gcd(x,y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int i=1; i<= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 마지막은 최소 공배수이다.
            // 먼저 x를 찾고, M씩 더해주면서 y를 찾는다.
            // 최소 공배수보다 더 커지면 종료

            int cnt = x;
            while(cnt <= lcm(M,N)) {
                int tmp = cnt % N != 0 ? cnt % N : N;

                if(tmp == y) {
                    break;
                }
                cnt += M;
            }

            if(M==x && N == y) {
                sb.append(lcm(M,N)+ "\n");
            }
            else if(cnt>lcm(M,N)) {
                sb.append("-1\n");
            }
            else {
                sb.append(cnt + "\n");
            }

        }
        System.out.println(sb);
    }
}