import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2056_G4_작업 {

    public static int N;
    public static int[] times;
    public static Node[] adjList;
    public static int[] inDegree;
    public static int[] dp;

    public static class Node{
        int vertex;
        Node link;

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", link=" + link +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        adjList = new Node[N+1];
        inDegree = new int[N+1];
        times = new int[N+1];
        for(int i=1; i<=N; i++){
            int to = i;
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            times[to]=time;
            int count = Integer.parseInt(st.nextToken());
            for(int j=0; j<count; j++){
                int from = Integer.parseInt(st.nextToken());
                adjList[from] = new Node(to, adjList[from]);
                inDegree[to]++;
            }
        }

        dp = new int[N+1];
        topologySort();

        Arrays.sort(dp);
        System.out.println(dp[N]);
    }

    private static void topologySort() {
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=1; i<=N; i++){
            if(inDegree[i]==0){
                queue.offer(i);
                dp[i] = times[i];
            }
        }

        while (!queue.isEmpty()){
            int cur = queue.poll();

            for(Node temp = adjList[cur]; temp!=null; temp=temp.link){
                dp[temp.vertex] = Math.max(dp[temp.vertex], dp[cur]+times[temp.vertex]);

                if(--inDegree[temp.vertex]==0){
                    queue.offer(temp.vertex);
                }
            }
        }
    }
}
