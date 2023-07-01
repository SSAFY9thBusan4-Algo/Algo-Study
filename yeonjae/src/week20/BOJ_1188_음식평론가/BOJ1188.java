import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1188 {

    static int N, M;
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        int gcd = gcd(N, M);

        // M - 1 - (gcd - 1) = M - gcd
        System.out.println(M - gcd);
    }

    private static int gcd(int a, int b) {
        if(a % b == 0)
            return b;

        return gcd(b, a % b);

    }
}