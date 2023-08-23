package week27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ19637 {

    static class Pair {
        String name;
        int power;

        public Pair(String name, int power) {
            this.name = name;
            this.power = power;
        }
    }

    static int N, M, power;
    static List<Pair> powers = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static String[] ss;
    static String name;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            name = ss[0];
            power = Integer.parseInt(ss[1]);
            powers.add(new Pair(name, power));
        }

        for(int i = 0; i < M; i++) {
            int characterPower = Integer.parseInt(in.readLine());
            goBinarySearch(characterPower);
        }

        System.out.println(sb);
    }

    private static void goBinarySearch(int characterPower) {
        int left = 0, right = powers.size() - 1;
        int mid = 0;

        while(left <= right) {
            mid = (left + right) / 2;
            int midPower = powers.get(mid).power;
            if(midPower < characterPower) {
                left = mid + 1;
            }
            else
                right = mid - 1;
        }

        sb.append(powers.get(right + 1).name).append('\n');
    }
}
