package week25;

import java.io.*;
import java.util.*;

public class BOJ18429 {

    static int N, K, cnt;
    static int[] kits;
    static String[] ss;
    static int[] temp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        K = Integer.parseInt(ss[1]);

        kits = new int[N];
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(ss[i]);
        }

        temp = new int[N];
        visited = new boolean[N];
        permutation(0);

        System.out.println(cnt);
    }

    private static void permutation(int cnt) {
        if(cnt == N) {
            calculateProtein();
            return;
        }

        for(int i = 0; i < N; i++) {
            if(visited[i])
                continue;

            temp[cnt] = kits[i];
            visited[i] = true;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }

    private static void calculateProtein() {
        int total = 0;
        for(int i = 0; i < N; i++) {
            total += temp[i];
            total -= K;
            if(total < 0)
                break;
        }

        if(total > 0)
            cnt++;
    }
}
