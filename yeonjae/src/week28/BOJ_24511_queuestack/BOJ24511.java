package week28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ24511 {

    static int N, M;
    static int[] type;
    static int[] num;
    static StringBuilder sb = new StringBuilder();
    static String[] ss;
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());

        type = new int[N];
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            type[i] = Integer.parseInt(ss[i]);
        }

        num = new int[N];
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(ss[i]);
        }


        for(int i = N - 1; i >= 0; i--) {
            if(type[i] == 0)
                q.add(num[i]);
        }

        M = Integer.parseInt(in.readLine());
        ss = in.readLine().split(" ");
        for(int i = 0; i < M; i++) {
            q.add(Integer.parseInt(ss[i]));
            sb.append(q.poll()).append(' ');
        }

        System.out.println(sb);
    }
}
