package week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1644 {

    static int N;
    static int[] nums;
    static boolean[] che;
    static int p, ans, sum, from, to;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        nums = new int[N+1];
        che = new boolean[N+1];

        for(int i = 2; i <= N; i++) {
            if(che[i])
                continue;

            for(int j = i * 2; j <= N; j += i) {
                che[j] = true;
            }
        }

        for(int i = 2; i <= N; i++) {
            if(!che[i])
                nums[p++] = i;
        }

        while(true) {
            if(sum == N) {
                ans++;
                sum -= nums[from++];
            }
            else if(sum > N) {
                sum -= nums[from++];
            }
            else if(to == p) {
                break;
            }
            else {
                sum += nums[to++];
            }
        }

        System.out.println(ans);
    }
}
