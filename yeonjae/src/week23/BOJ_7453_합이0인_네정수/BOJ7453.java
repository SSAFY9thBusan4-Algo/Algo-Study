package week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ7453 {

    static int N;
    static long ans;
    static long[] A, B, C, D, AB, CD;
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        A = new long[N];
        B = new long[N];
        C = new long[N];
        D = new long[N];

        for(int i = 0; i < N; i++) {
            ss = in.readLine().split(" ");
            A[i] = Long.parseLong(ss[0]);
            B[i] = Long.parseLong(ss[1]);
            C[i] = Long.parseLong(ss[2]);
            D[i] = Long.parseLong(ss[3]);
        }

        AB = new long[N * N];
        CD = new long[N * N];
        int idx = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        for(int i = 0 ; i < AB.length ; ++i) {
            ans += upper_bound(0, CD.length, -AB[i]) - lower_bound(0, CD.length, -AB[i]);
        }

        System.out.println(ans);
    }

    private static int upper_bound(int left, int right, long target) {
        while(left < right) {
            int mid = (left + right) / 2;
            if(CD[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private static int lower_bound(int left, int right, long target) {
        while(left < right) {
            int mid = (left + right) / 2;
            if(CD[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
