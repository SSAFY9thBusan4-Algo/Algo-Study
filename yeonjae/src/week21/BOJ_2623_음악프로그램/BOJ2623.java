import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BOJ2623 {

    static class Node {
        int vertex;
        Node link;

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }
    }

    static int N, M, cnt, start, end;
    static Node[] adjNode;
    static int[] inDegree;
    static String[] ss;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        adjNode = new Node[N + 1];
        inDegree = new int[N + 1];

        for(int i = 0; i < M; i++) {
            ss = in.readLine().split(" ");
            cnt = Integer.parseInt(ss[0]);

            for(int j = 1; j < cnt; j++) {
                start = Integer.parseInt(ss[j]);
                end = Integer.parseInt(ss[j + 1]);

                adjNode[start] = new Node(end, adjNode[start]);
                inDegree[end]++;
            }
        }

        ArrayList<Integer> resultFromTopologySort = topologySortByBFS();

        if(resultFromTopologySort.size() != N) {
            sb.append(0);
        }
        else {
            for(Integer i : resultFromTopologySort) {
                sb.append(i).append('\n');
            }
        }

        System.out.println(sb);
    }

    static ArrayList<Integer> topologySortByBFS() {
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            if(inDegree[i] == 0)
                q.offer(i);
        }

        ArrayList<Integer> visitedNode = new ArrayList<>();
        while(!q.isEmpty()) {
            int currentNode = q.poll();
            visitedNode.add(currentNode);

            for(Node temp = adjNode[currentNode]; temp != null; temp = temp.link) {
                inDegree[temp.vertex]--;

                if(inDegree[temp.vertex] == 0) {
                    q.offer(temp.vertex);
                }

            }
        }

        return visitedNode;
    }
}
