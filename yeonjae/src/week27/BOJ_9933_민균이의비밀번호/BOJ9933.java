package week27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ9933 {

    static int N;
    static List<String> alphas = new ArrayList<>();
    static String ans;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        for(int i = 0; i < N; i++) {
            alphas.add(in.readLine());
        }

        for(String str : alphas) {
            StringBuilder subSb = new StringBuilder();
            subSb.append(str);
            String reversedStr = subSb.reverse().toString();

            if(alphas.contains(reversedStr)) {
                ans = str;
                break;
            }
        }

        sb.append(ans.length()).append(' ');
        sb.append(ans.charAt(ans.length() / 2));

        System.out.println(sb);
    }
}