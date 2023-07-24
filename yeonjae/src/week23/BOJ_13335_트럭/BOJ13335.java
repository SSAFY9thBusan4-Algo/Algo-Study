package week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ13335 {

    static int N, W, L;
    static String[] ss;
    static int[] weights;
    static int cnt, totalWeight;

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        W = Integer.parseInt(ss[1]);
        L = Integer.parseInt(ss[2]);

        weights = new int[N];
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(ss[i]);
        }

        Queue<Integer> q = new ArrayDeque<>();

        int weightIdx = 0;
        while(weightIdx < N) {
            cnt++;

            // 다리의 마지막 지점에 도착한 트럭이 다리를 건너는 행위
            if(q.size() == W) {
                totalWeight -= q.poll();
            }

            // 트럭이 다리에 올라갈 수 있는 경우
            if(totalWeight + weights[weightIdx] <= L) {
                totalWeight+= weights[weightIdx];
                q.add(weights[weightIdx]);
                weightIdx++;
            }
            // 트럭이 다리에 올라갈 수 없는 경우
            else {
                q.add(0);
            }


        }

        // 마지막에 올라간 트럭이 다리를 건너는데 걸리는 시간을 더한다.
        cnt += W;

        System.out.println(cnt);
    }
}
