import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,M;

    static class Node implements Comparable<Node>{
        int no;
        int weight;

        public Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];

        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));

        }
        prim();
    }
    private static void prim() {

        Queue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];

        pq.offer(new Node(1,0));

        int result =0;
        int max = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.no]) continue;
            visited[now.no] = true;

            result += now.weight;
            max = Math.max(now.weight, max); // 가중치 가장 높은 친구를 마지막에 빼주면 두 마을로 나눴을때 최소 값이 된다.

            for(Node node : adjList[now.no]){
                if(!visited[node.no]){
                    pq.offer(node);
                }
            }

        }

        System.out.println(result - max);

    }
}
