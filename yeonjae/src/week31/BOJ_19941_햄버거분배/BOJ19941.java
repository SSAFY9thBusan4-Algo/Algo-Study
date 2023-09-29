package week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ19941 {

    static int N, K;
    static String[] line;
    static boolean[] visited;
    static String[] ss;
    static String POrH;
    static int totalCnt;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        K = Integer.parseInt(ss[1]);

        line = in.readLine().split("");
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
            POrH = line[i];

            if(POrH.equals("H"))
                continue;

            for(int j = (-1) * K; j <= K; j++) {
                if((i + j < 0) || (i + j >= N))
                    continue;

                if((line[i + j].equals("H")) && (!visited[i + j])) {
                    visited[i + j] = true;
                    totalCnt++;
                    break;
                }
            }
        }

        System.out.println(totalCnt);
    }
}