package week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ21756 {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        Queue<Integer> prevQ = new ArrayDeque<>();
        Queue<Integer> nextQ = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            prevQ.add(i);
        }

        while(prevQ.size() != 1) {
            boolean isEven = false;
            while(!prevQ.isEmpty()) {
                int num = prevQ.poll();
                if(isEven) {
                    nextQ.add(num);
                    isEven = false;
                }
                else
                    isEven = true;
            }

            while(!nextQ.isEmpty()) {
                prevQ.add(nextQ.poll());
            }
        }

        System.out.println(prevQ.peek());
    }
}
