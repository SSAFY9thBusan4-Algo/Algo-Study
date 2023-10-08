package week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20922 {

    static int N, K;
    static int[] cnt = new int[100_000 + 1];
    static int[] nums;
    static String[] ss;
    static int left, right, maxLen;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        K = Integer.parseInt(ss[1]);

        nums = new int[N];
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(ss[i]);
        }

        left = 0;
        right = 1;
        maxLen = 1;
        cnt[nums[left]]++;

        while(right < N) {
            // 길이 줄이기 (left++)
            if(cnt[nums[right]] + 1> K) {
                cnt[nums[left]]--;
                left++;
            }
            // 길이 늘리기 (right++)
            else {
                cnt[nums[right]]++;
                right++;
                maxLen = Math.max(maxLen, right - left);
            }
        }

        System.out.println(maxLen);
    }
}
