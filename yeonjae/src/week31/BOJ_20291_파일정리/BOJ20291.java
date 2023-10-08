package week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ20291 {

    static Map<String, Integer> formatAndCnt = new HashMap<>();
    static int N;
    static String[] ss;
    static String s;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        for(int i = 0; i < N; i++) {
            ss = in.readLine().split("\\.");
            s = ss[1];
            formatAndCnt.putIfAbsent(s, 0);
            formatAndCnt.put(s, formatAndCnt.get(s) + 1);
        }

        List<String> formats = new ArrayList<>();
        for(String key : formatAndCnt.keySet()) {
            formats.add(key);
        }

        Collections.sort(formats);

        for(String s : formats) {
            sb.append(s).append(' ').append(formatAndCnt.get(s)).append('\n');
        }

        System.out.println(sb);
    }
}
