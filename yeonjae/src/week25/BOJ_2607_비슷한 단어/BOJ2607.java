package week25;

import java.io.*;
import java.util.*;

public class BOJ2607 {

    static int N, originLen, ansCnt, cnt;
    static String origin, comp;
    static int[] originAlpha, compAlpha;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        origin = in.readLine();
        originLen = origin.length();
        originAlpha = new int[26];
        for(int i = 0; i < originLen; i++) {
            originAlpha[origin.charAt(i) - 'A']++;
        }

        for(int i = 0; i < N - 1; i++) {
            compAlpha = originAlpha.clone();
            comp = in.readLine();

            cnt = 0;
            for(int j = 0; j < comp.length(); j++) {
                if(compAlpha[comp.charAt(j) - 'A'] > 0) {
                    cnt++;
                    compAlpha[comp.charAt(j) - 'A']--;
                }
            }

            // 길이가 같은 경우 (완전 같거나, 하나의 문자를 바꾸는 경우)
            if(originLen == comp.length()){
                if(cnt == originLen || cnt == originLen - 1)
                    ansCnt++;
            }
            // 길이가 짧은 경우 (하나 빼고는 모두 다 일치해야 함)
            else if(originLen - 1 == comp.length() && cnt == comp.length()) {
                ansCnt++;
            }
            // 길이가 긴 경우 (모두가 일치해야 함)
            else if(originLen + 1 == comp.length()) {
                if(cnt == originLen)
                    ansCnt++;
            }
        }

        System.out.println(ansCnt);
    }
}
