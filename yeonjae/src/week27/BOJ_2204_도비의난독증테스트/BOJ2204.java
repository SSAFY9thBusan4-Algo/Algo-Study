package week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ2204 {

    static int N;
    static String ori;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            N = Integer.parseInt(in.readLine());
            if(N == 0)
                break;

            Map<String, String> bigAndOri = new HashMap<>();
            List<String> bigAlphas = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                ori = in.readLine();
                bigAndOri.put(ori.toUpperCase(), ori);
                bigAlphas.add(ori.toUpperCase());
            }

            Collections.sort(bigAlphas);
            sb.append(bigAndOri.get(bigAlphas.get(0))).append('\n');
        }

        System.out.println(sb);
    }
}
