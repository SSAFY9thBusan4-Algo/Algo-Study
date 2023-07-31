package week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1522 {

    static char[] charsOrigin, charsDouble;
    static int aCnt, bCnt, minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        charsOrigin = in.readLine().toCharArray();
        int len = charsOrigin.length;
        charsDouble = new char[len * 2];
        for(int i = 0; i < len; i++) {
            charsDouble[i] = charsOrigin[i];
            charsDouble[i + len] = charsOrigin[i];
            if(charsOrigin[i] == 'a')
                aCnt++;
        }

        for(int i = 0; i < len; i++) {
            bCnt = 0;
            for(int j = i; j < i + aCnt; j++) {
                if(charsDouble[j] == 'b')
                    bCnt++;
            }
            minCnt = Math.min(minCnt, bCnt);
        }

        System.out.println(minCnt);
    }
}
