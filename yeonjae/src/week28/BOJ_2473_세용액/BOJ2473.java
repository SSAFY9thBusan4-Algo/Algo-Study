package week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2473 {

    static int N;
    static Long targetSum = Long.MAX_VALUE;
    static Long[] waters;
    static String[] ss;
    static Long sum;
    static Long targetLeft, targetMid, targetRight;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        waters = new Long[N];

        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            waters[i] = Long.parseLong(ss[i]);
        }

        Arrays.sort(waters);

        for(int i = 0; i < N - 2; i++) {
            int mid = i;
            int left = i + 1;
            int right = N - 1;

            while(left < right) {
                sum = waters[mid] + waters[left] + waters[right];
                if(Math.abs(sum) < Math.abs(targetSum)) {
                    targetLeft = waters[mid];
                    targetMid = waters[left];
                    targetRight = waters[right];
                    targetSum = targetLeft + targetMid + targetRight;
                }
                if(sum < 0) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        System.out.println(targetLeft + " " + targetMid + " " + targetRight);


    }
}
