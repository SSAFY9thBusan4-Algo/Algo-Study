pakage src.week21.BOJ_1744_G4_수묶기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // input
        PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minus = new PriorityQueue<>();
        for(int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0) plus.offer(num);
            else minus.offer(num);
        }

        // solve
        int sum = 0;
        sum += getSum(plus);
        sum += getSum(minus);

        System.out.println(sum);
    }

    private static int getSum(PriorityQueue<Integer> queue) {
        if(queue.isEmpty()) return 0;
        int sum = 0;

        while(!queue.isEmpty()) {
            int n1 = queue.poll();
            if(queue.isEmpty()) {
                sum += n1;
            }
            else {
                int n2 = queue.poll();

                if(n1 * n2 > n1 + n2)
                    sum += n1 * n2;
                else sum += n1 + n2;
            }
        }

        return sum;
    }
}
