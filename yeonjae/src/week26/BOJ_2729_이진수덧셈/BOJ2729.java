package week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2729 {

    static int N;
    static String num1, num2;
    static StringBuilder sb = new StringBuilder();
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            num1 = ss[0];
            num2 = ss[1];

            String ans = "";
            int num1Idx = num1.length() - 1;
            int num2Idx = num2.length() - 1;
            int sum = 0;
            while((num1Idx >= 0) && (num2Idx >= 0)) {
                if(num1.charAt(num1Idx) == '1' && num2.charAt(num2Idx) == '1') {
                    sum += 2;
                    if(sum == 2) {
                        ans = "0" + ans;
                        sum = 1;
                    }
                    else if(sum == 3) {
                        ans = "1" + ans;
                        sum = 1;
                    }
                }
                else if(num1.charAt(num1Idx) == '1' || num2.charAt(num2Idx) == '1') {
                    sum++;
                    if(sum == 1) {
                        ans = "1" + ans;
                        sum = 0;
                    }
                    else if(sum == 2) {
                        ans = "0" + ans;
                        sum = 1;
                    }
                }
                else {
                    if(sum == 0) {
                        ans = "0" + ans;
                        sum = 0;
                    }
                    else if(sum == 1) {
                        ans = "1" + ans;
                        sum = 0;
                    }
                }

                num1Idx--;
                num2Idx--;
            }

            while(num1Idx >= 0) {
                if(sum == 1) {
                    if(num1.charAt(num1Idx) == '1') {
                        ans = "0" + ans;
                        sum = 1;
                    }
                    else {
                        ans = "1" + ans;
                        sum = 0;
                    }
                }
                else {
                    if(num1.charAt(num1Idx) == '1')
                        ans = "1" + ans;
                    else
                        ans = "0" + ans;
                }
                num1Idx--;
            }

            while(num2Idx >= 0) {
                if(sum == 1) {
                    if(num2.charAt(num2Idx) == '1') {
                        ans = "0" + ans;
                        sum = 1;
                    }
                    else {
                        ans = "1" + ans;
                        sum = 0;
                    }
                }
                else {
                    if(num2.charAt(num2Idx) == '1')
                        ans = "1" + ans;
                    else
                        ans = "0" + ans;
                }
                num2Idx--;
            }

            if(sum == 1) {
                ans = "1" + ans;
            }

            // 앞에 0으로 시작하는거 제거
            int startOneIdx = 0;
            while(ans.charAt(startOneIdx) == '0') {
                startOneIdx++;

                if(startOneIdx == ans.length()) {
                    break;
                }
            }

            if(startOneIdx == ans.length())
                sb.append(0).append('\n');
            else
                sb.append(ans.substring(startOneIdx)).append('\n');
        }
        System.out.println(sb);
    }
}
