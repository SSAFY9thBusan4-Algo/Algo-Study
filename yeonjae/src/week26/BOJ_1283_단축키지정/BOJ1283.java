package week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1283 {

    static int N;
    static List<String> alphas = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static String s;
    static String[] ss;
    static StringBuilder subSb;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        alphas.add(" ");
        N = Integer.parseInt(in.readLine());
        for(int i = 0; i < N; i++) {

            s = in.readLine();
            ss = s.split(" ");

            subSb = new StringBuilder();
            boolean find = false;
            for(int j = 0; j < ss.length; j++) {
                if(find || alphas.contains(Character.toString(ss[j].charAt(0)))) {
                    subSb.append(ss[j]).append(' ');
                    continue;
                }

                subSb.append('[').append(ss[j].charAt(0)).append(']').append(ss[j].substring(1));
                subSb.append(' ');
                alphas.add(String.valueOf(ss[j].charAt(0)).toLowerCase());
                alphas.add(String.valueOf(ss[j].charAt(0)).toUpperCase());
                find = true;
            }

            if(find) {
                sb.append(subSb).append('\n');
            }
            else {
                for(int j = 0; j < s.length(); j++) {
                    if(find || alphas.contains(Character.toString(s.charAt(j)))) {
                        sb.append(s.charAt(j));
                        continue;
                    }

                    sb.append('[').append(s.charAt(j)).append(']');
                    alphas.add(String.valueOf(s.charAt(j)).toLowerCase());
                    alphas.add(String.valueOf(s.charAt(j)).toUpperCase());
                    find = true;
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }
}
