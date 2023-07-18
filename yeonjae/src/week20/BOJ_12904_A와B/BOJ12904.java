import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ12904 {

    static String S, T, midT;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        S = in.readLine();
        T = in.readLine();
        midT = T;

        // midT의 길이가 S와 같아질때까지 반복문 실행
        for(int len = T.length(); len > S.length(); len--) {
            midT = getPrevString(midT);
        }

        System.out.println(midT.equals(S) ? "1" : "0");
    }

    private static String getPrevString(String t) {
        int tLen = t.length();
        char lastChar = t.charAt(tLen - 1);
        String subT = (String) t.subSequence(0, tLen - 1);
        if(lastChar == 'A') {
            return subT;
        }
        else {
            return reverseStr(subT);
        }
    }

    private static String reverseStr(String str) {
        StringBuilder tempStr = new StringBuilder();
        for(int i = str.length() - 1; i >= 0; i--) {
            tempStr.append(str.charAt(i));
        }
        return tempStr.toString();
    }
}
