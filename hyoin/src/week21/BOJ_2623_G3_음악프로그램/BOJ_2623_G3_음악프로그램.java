import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    public static int N; // 가수 수
    public static int M; // PD 수
    public static Node[] adjList;
    public static int[] inDegree;

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
        M = Integer.parseInt(st.nextToken());

        adjList = new Node[N+1];
        inDegree = new int[N+1];
        int from, to;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            from = Integer.parseInt(st.nextToken());
            for(int j=1; j<count; j++){
                to = Integer.parseInt(st.nextToken());
                adjList[from] = new Node(to, adjList[from]);
                inDegree[to]++;
                from = to;
            }
        }

        ArrayList<Integer> list = topologySort();
        if(list.size()==N) {
            for (Integer vertex : list) {
                System.out.println(vertex);
            }
        }else {
            System.out.println("0");
        }
    }

    private static ArrayList<Integer> topologySort() {
        ArrayList<Integer> orderList = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        // 진입차수가 0인 정점 큐에 넣기
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            orderList.add(cur);

            // 현재 정점 기준으로 인접정점 처리
            for (Node temp = adjList[cur]; temp != null; temp = temp.link) {
                if (--inDegree[temp.vertex] == 0) {
                    queue.offer(temp.vertex);
                }
            }
        }

        return orderList;
    }
}
