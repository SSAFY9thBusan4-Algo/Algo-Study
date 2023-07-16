import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ1744 {
    static int N, num, num1, num2, sum;
    static int zeroCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            num = Integer.parseInt(in.readLine());
            pq.add(num);
            if(num == 0)
                zeroCnt++;
        }

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        while(!pq.isEmpty()) {
            num = pq.poll();
            dq.add(num);
        }
        // 1.
        // 음수가 2개 이상인 경우
        // (음수) x (움수) = (양수)
        while(dq.size() >= 2 && dq.peekFirst() < 0) {
            num1 = dq.pollFirst();
            num2 = dq.pollFirst();
            if(num2 > 0) {
                dq.addFirst(num2);
                dq.addFirst(num1);
                break;
            }
            sum  += (num1 * num2);
        }

        // 2.
        // 음수가 1개 남아 있는 경우
        // 0이 있으면, (음수) x (0) = (0)
        if(!dq.isEmpty() && dq.peekFirst() < 0) {
            if(zeroCnt > 0) {
                dq.pollFirst(); // 음수
                dq.pollFirst(); // 음수를 무효화 시킬 0
            }
            else {
                sum += dq.pollFirst();
            }

        }

        // 3.
        // 1이 아닌 양수는 곱해서 더 크게 만들기
        while(dq.size() >= 2 && dq.peekLast() > 1) {
            num1 = dq.pollLast();
            num2 = dq.pollLast();

            if(num2 <= 1) {
                sum += (num1 + num2);
                break;
            }

            sum += (num1 * num2);
        }

        // 4.
        // 0또는 1을 모두 더하기
        while(!dq.isEmpty())
            sum += dq.poll();

        System.out.println(sum);

    }
}
