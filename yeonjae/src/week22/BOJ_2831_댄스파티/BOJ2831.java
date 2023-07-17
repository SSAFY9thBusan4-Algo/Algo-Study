import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ2831 {

    static int N, num, woman_height, man_height, pairCnt;
    static PriorityQueue<Integer> woman_minus = new PriorityQueue<>();
    static PriorityQueue<Integer> woman_plus = new PriorityQueue<>();
    static PriorityQueue<Integer> man_minus = new PriorityQueue<>();
    static PriorityQueue<Integer> man_plus = new PriorityQueue<>();
    static String[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());

        // 남자의 키
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            num = Integer.parseInt(ss[i]);
            if(num > 0) {
                man_plus.add(num);
            }
            else {
                man_minus.add(Math.abs(num));
            }
        }

        // 여자의 키
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            num = Integer.parseInt(ss[i]);
            if(num > 0) {
                woman_plus.add(num);
            }
            else {
                woman_minus.add(Math.abs(num));
            }
        }


        // woman_plus와 man_minus 비교
        // 키가 큰 남자와 춤을 추고 싶은 여자와 키가 작은 여자와 춤을 추고 싶은 남자
        while(!woman_plus.isEmpty() && !man_minus.isEmpty()) {
            woman_height = woman_plus.peek();
            man_height = man_minus.peek();

            // 성립하는 경우
            if(woman_height < man_height) {
                woman_plus.poll();
                man_minus.poll();
                pairCnt++;
            }
            else {
                man_minus.poll();
            }
        }

        // woman_minus와 man_plus 비교
        // 키가 작은 남자와 춤을 추고 싶은 여자와 키가 큰 여자와 춤을 추고 싶은 남자
        while(!woman_minus.isEmpty() && !man_plus.isEmpty()) {
            woman_height = woman_minus.peek();
            man_height = man_plus.peek();

            // 성립하는 경우
            if(woman_height > man_height) {
                woman_minus.poll();
                man_plus.poll();
                pairCnt++;
            }
            else {
                woman_minus.poll();
            }
        }

        System.out.println(pairCnt);
    }
}
