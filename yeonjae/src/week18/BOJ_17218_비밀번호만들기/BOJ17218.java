import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ17218 {

    static String strA, strB;
    static int[][] DP;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        strA = in.readLine();
        strB = in.readLine();

        int strALen = strA.length();
        int strBLen = strB.length();

        // DP배열에 현재 위치까지의 LCS의 길이를 기록
        DP = new int[strALen + 1][strBLen + 1];
        for(int i = 1; i <= strALen; i++){
            for(int j = 1; j <= strBLen; j++){
                if(strA.charAt(i - 1) == strB.charAt(j - 1))
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                else
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
            }
        }

        // 기록된 DP배열을 이용하여 LCS 찾기
        // LCS의 길이가 증가되는 부분에서의 문자를 기록하면 됨
        int i = strALen;
        int j = strBLen;
        while(true) {
            if(i > 0 && DP[i-1][j] == DP[i][j]) {
                i--;
            }
            else if(j > 0 && DP[i][j-1] == DP[i][j]) {
                j--;
            }
            else {
                sb.append(strA.charAt(i - 1));
                i--;
                j--;
            }

            if(i == 0 && j == 0)
                break;
        }

        System.out.println(sb.reverse());
    }
}