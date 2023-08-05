package week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1701 {

    static String origin, pattern;
    static int[] pi;
    static int maxCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        origin = in.readLine();
        int originLen = origin.length();

        for(int leftStart = 0; leftStart < originLen; leftStart++) {
            pattern = origin.substring(leftStart, originLen);

            int patternLen = pattern.length();
            pi = new int[patternLen];

            int j = 0;
            for(int i = 1; i < patternLen; i++) {
                while(j > 0 && pattern.charAt(i) != pattern.charAt(j))
                    j = pi[j - 1];

                if(pattern.charAt(i) == pattern.charAt(j)) {
                    pi[i] = ++j;
                    maxCnt = Math.max(maxCnt, j);
                }
            }

        }
        System.out.println(maxCnt);
    }
}
