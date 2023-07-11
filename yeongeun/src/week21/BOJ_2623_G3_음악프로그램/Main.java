pakage src.week21.BOJ_2623_G3_음악프로그램

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int n;
        Node next;

        public Node(int n, Node next) {
            this.n = n;
            this.next = next;
        }
    }

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        int[] preN = new int[N+1];
        Node[] link = new Node[N+1];

        for(int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());

            int pre = Integer.parseInt(st.nextToken());
            for(int j = 1 ; j < n ; j++) {
                int cur = Integer.parseInt(st.nextToken());
                preN[cur]++;
                link[pre] = new Node(cur, link[pre]);
                pre = cur;
            }
        }

        Queue<Integer> order = makeOrder(preN, link);
        printOrder(order);
    }

    private static void printOrder(Queue<Integer> order) {
        if(order.size() != N) System.out.println(0);
        else {
            StringBuilder sb = new StringBuilder();
            while(!order.isEmpty()) sb.append(order.poll()).append('\n');
            System.out.println(sb);
        }
    }

    private static Queue<Integer> makeOrder(int[] preN, Node[] link) {
        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Integer> order = new ArrayDeque<>();  // 결과 순서

        // 진입차수가 0인 노드들 큐에 넣기
        for(int i = 1 ; i <= N ; i++) {
            if(preN[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            order.offer(cur);

            for(Node node = link[cur] ; node != null ; node = node.next) {
                if(--preN[node.n] == 0) queue.offer(node.n);
            }
        }

        return order;
    }
}
