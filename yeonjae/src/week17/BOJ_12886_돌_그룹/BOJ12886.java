import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12886 {

    static int A, B, C;
    static String[] ss;
    static boolean[][] visited;
    static final int MAX_NUM = 500;
    static int[] sortedNumbers = new int[3];
    static boolean ans;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        A = Integer.parseInt(ss[0]);
        B = Integer.parseInt(ss[1]);
        C = Integer.parseInt(ss[2]);

        visited = new boolean[MAX_NUM * 4 + 1][MAX_NUM * 4 + 1];
        sortedNumbers[0] = A;
        sortedNumbers[1] = B;
        sortedNumbers[2] = C;
        Arrays.sort(sortedNumbers);

        visited[sortedNumbers[0]][sortedNumbers[2]] = true;
        go(sortedNumbers[0], sortedNumbers[1], sortedNumbers[2]);

        System.out.println(ans ? 1 : 0);
    }

    private static void go(int num1, int num2, int num3) {

        // 돌의 개수가 모두 같은 경우
        if(num1 == num2 && num2 == num3) {
            ans = true;
            return;
        }

        int tempNum1, tempNum2, tempNum3;


        if(num1 != num2) {
            tempNum1 = num1 + num1;
            tempNum2 = num2 - num1;
            tempNum3 = num3;

            if(tempNum1 >= 0 && tempNum2 >= 0 && tempNum3 >= 0) {
                sortedNumbers[0] = tempNum1;
                sortedNumbers[1] = tempNum2;
                sortedNumbers[2] = tempNum3;
                Arrays.sort(sortedNumbers);
                if(!visited[sortedNumbers[0]][sortedNumbers[2]]) {
                    visited[sortedNumbers[0]][sortedNumbers[2]] = true;
                    go(sortedNumbers[0], sortedNumbers[1], sortedNumbers[2]);
                }
            }
        }
        if(num1 != num3) {
            tempNum1 = num1 + num1;
            tempNum2 = num2;
            tempNum3 = num3 - num1;

            if(tempNum1 >= 0 && tempNum2 >= 0 && tempNum3 >= 0) {
                sortedNumbers[0] = tempNum1;
                sortedNumbers[1] = tempNum2;
                sortedNumbers[2] = tempNum3;
                Arrays.sort(sortedNumbers);
                if(!visited[sortedNumbers[0]][sortedNumbers[2]]) {
                    visited[sortedNumbers[0]][sortedNumbers[2]] = true;
                    go(sortedNumbers[0], sortedNumbers[1], sortedNumbers[2]);
                }
            }
        }
        if(num2 != num3) {
            tempNum1 = num1;
            tempNum2 = num2 + num2;
            tempNum3 = num3 - num2;

            if(tempNum1 >= 0 && tempNum2 >= 0 && tempNum3 >= 0) {
                sortedNumbers[0] = tempNum1;
                sortedNumbers[1] = tempNum2;
                sortedNumbers[2] = tempNum3;
                Arrays.sort(sortedNumbers);
                if(!visited[sortedNumbers[0]][sortedNumbers[2]]) {
                    visited[sortedNumbers[0]][sortedNumbers[2]] = true;
                    go(sortedNumbers[0], sortedNumbers[1], sortedNumbers[2]);
                }
            }
        }
    }
}
