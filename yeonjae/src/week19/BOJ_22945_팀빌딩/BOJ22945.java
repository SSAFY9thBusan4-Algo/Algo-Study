import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ22945 {
    static int N, maxTeamPower, left, right, dis, smallPower;
    static int[] power;
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());

        power = new int[N];
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            power[i] = Integer.parseInt(ss[i]);
        }

        left = 0;
        right = N - 1;
        while(left < right) {

            dis = right - left - 1;
            smallPower = Math.min(power[left], power[right]);

            maxTeamPower = Math.max(maxTeamPower, dis * smallPower);

            if(power[left] <= power[right])
                left++;
            else
                right--;
        }

        System.out.println(maxTeamPower);
    }
}