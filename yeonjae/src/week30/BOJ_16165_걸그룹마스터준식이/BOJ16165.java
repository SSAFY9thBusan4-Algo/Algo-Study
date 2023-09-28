package week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ16165 {

    static int N, M, num;
    static String[] ss;
    static Map<String, Integer> teamnameAndIdx = new HashMap<>();
    static String s;
    static StringBuilder sb = new StringBuilder();
    static Map<String, String> membernameAndTeamname = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        List[] teams = new List[N];
        for(int i = 0; i < N; i++) {
            teams[i] = new ArrayList<String>();

            s = in.readLine();
            teamnameAndIdx.put(s, i);

            num = Integer.parseInt(in.readLine());
            for(int j = 0; j < num; j++) {
                String memberName = in.readLine();
                teams[i].add(memberName);
                membernameAndTeamname.put(memberName, s);
            }
            Collections.sort(teams[i]);
        }

        for(int i = 0; i < M; i++) {
            s = in.readLine();
            num = Integer.parseInt(in.readLine());

            if(num == 0) {
                int idx = teamnameAndIdx.get(s);
                for(Object str : teams[idx]) {
                    sb.append(str.toString()).append('\n');
                }
            }
            else {
                sb.append(membernameAndTeamname.get(s)).append('\n');
            }
        }

        System.out.println(sb);
    }
}
