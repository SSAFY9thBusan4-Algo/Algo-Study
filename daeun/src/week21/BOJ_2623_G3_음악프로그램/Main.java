import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int N, in[];
    static ArrayList<Integer>[] list;
    static ArrayList<Integer> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        in = new int[N+1];
        list = new ArrayList[N+1];
        result = new ArrayList<>();

        for(int i=0;i<N+1;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            split = br.readLine().split(" ");
            int num = Integer.parseInt(split[0]);
            int first = Integer.parseInt(split[1]);
            for(int j=2;j<num+1;j++){
                int next = Integer.parseInt(split[j]);
                list[first].add(next);
                in[next]++;
                first = next;
            }
        }

        topology(); //위상정렬

        // N개가 위상정렬되었음
        if(result.size() == N){
            for(Integer i: result) {
                sb.append(i).append("\n");
            }
            System.out.println(sb);
        }
        else{
            System.out.println(0);
        }

    }

    private static void topology() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            result.add(now);
            for (Integer i : list[now]) {
                if (--in[i] == 0) {
                    queue.offer(i);
                }
            }
        }
    }
}
