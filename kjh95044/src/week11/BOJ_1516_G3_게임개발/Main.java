import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static Node[] adjList;
    static int[] inDegree, delays;

    static class Node{
        int vertex;
        Node link;

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        delays = new int[N+1];
        inDegree = new int[N+1];
        adjList = new Node[N+1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            delays[i] = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());
                if(num!=-1){
                    adjList[num] = new Node(i, adjList[num]);
                    inDegree[i] ++;
                }
            }
        }
        topologySort();
        System.out.println(sb);
    }

    static void topologySort(){
        int[] result = new int[N+1];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            result[i] = delays[i];

            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            int now = queue.poll();

            for (Node temp = adjList[now]; temp !=null ; temp = temp.link) {
                result[temp.vertex] = Math.max(result[temp.vertex], result[now] + delays[temp.vertex]);

                if(--inDegree[temp.vertex] == 0){
                    queue.offer(temp.vertex);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }



    }
}
