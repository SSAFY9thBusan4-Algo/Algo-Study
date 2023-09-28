package week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ22233 {

    static Map<String, Integer> wordAndIdx = new HashMap<>();
    static boolean[] use;
    static String[] ss;
    static int N, M;
    static String word;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        use = new boolean[N];
        for(int i = 0; i < N; i++) {
            word = in.readLine();
            wordAndIdx.put(word, i);
        }

        int unUsedCount = N;
        for(int i = 0; i < M; i++) {
            ss = in.readLine().split(",");
            for(int j = 0; j < ss.length; j++) {
                word = ss[j];
                if(!wordAndIdx.containsKey(word)) {
                    continue;
                }
                int idx = wordAndIdx.get(word);
                if(use[idx] == false) {
                    use[idx] = true;
                    unUsedCount--;
                }

            }
            sb.append(unUsedCount).append('\n');
        }

        System.out.println(sb);
    }
}
