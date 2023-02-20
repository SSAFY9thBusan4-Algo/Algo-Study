package src.week3.BOJ_9084_G5_동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            //입력
            int N = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            int M = Integer.parseInt(br.readLine());

            // solve
            // 1원 ~ M원
            int[] counts = new int[M+1];

            // 모든 동전에 대하여
            for(int i = 0 ; i < N ; i++) {
                int curm = coins[i];
                if(curm > M) continue;
                counts[curm]++;

                // 경우의 수를 구한다.
                for(int j = curm+1; j <= M ; j++) {
                    counts[j] += counts[j - curm];
                }
            }

            // 출력
            sb.append(counts[M]).append("\n");

        }
        System.out.println(sb);
    }

}