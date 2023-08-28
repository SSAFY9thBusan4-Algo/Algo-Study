import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        String[] str = br.readLine().split(" ");
        String[] ing = br.readLine().split(" ");
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0;i<N;i++) { //0이면 큐(FIFO), 1이면 스택(LIFO)
            int what = Integer.parseInt(str[i]);
            int temp = Integer.parseInt(ing[i]);

            //자료구조 특성 상 스택은 바뀌지 않음!
            if(what == 0) {
                deque.addFirst(temp);
            }
        }

        int M = Integer.parseInt(br.readLine());
        str = br.readLine().split(" ");
        for(int i=0;i<M;i++) {
            deque.offer(Integer.parseInt(str[i]));
        }

        for(int i=0;i<M;i++) {
            sb.append(deque.poll()+" ");
        }
        System.out.println(sb);
    }
}
