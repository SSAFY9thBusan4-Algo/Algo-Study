package week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BOJ2800 {

    static String s;
    static int[] pair;
    static boolean[] visited;
    static int len;
    static StringBuilder sb = new StringBuilder();
    static Set<String> totalSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        s = in.readLine();
        len = s.length();
        pair = new int[len];
        visited = new boolean[len];

        Stack<Integer> stk = new Stack<>();
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stk.push(i);
            }
            else if(c == ')') {
                int prev = stk.pop();
                pair[prev] = i;
                pair[i] = prev;
            }
        }

        dfs(0);
        totalSet.remove(s);

        List<String> answers = new ArrayList<>(totalSet);
        Collections.sort(answers);
        for(String answer : answers) {
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }

    private static void dfs(int idx) {
        if(idx == len) {
            totalSet.add(sb.toString());
            return;
        }

        char c = s.charAt(idx);
        if(c == '(') {
            visited[idx] = true;
            dfs(idx+1);
            visited[idx] = false;
        }

        if((c == ')') && (visited[pair[idx]])) {
            dfs(idx+1);
            visited[idx] = false;
        }
        else {
            sb.append(c);
            dfs(idx+1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
