import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static class Node{
        int vertex;
        Node link;

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }
    }

    static Node[] adjList;
    static int[] inDegree, delay;
    static int K, N, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            delay = new int[N+1];
            inDegree = new int[N+1];
            adjList = new Node[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                delay[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList[from] = new Node(to, adjList[from]);
                inDegree[to]++;
            }

            target = Integer.parseInt(br.readLine());

            topologySort();



        }
        System.out.println(sb);
    }

    static void topologySort(){
        int[] result = new int[N+1];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            result[i] = delay[i];

            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            int now = queue.poll();

            for (Node temp = adjList[now]; temp !=null ; temp = temp.link) {
                result[temp.vertex] = Math.max(result[temp.vertex], result[now] + delay[temp.vertex]);

                if(--inDegree[temp.vertex] == 0){
                    queue.offer(temp.vertex);
                }
            }
        }
        sb.append(result[target]).append("\n");


    }
}
