import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ2056 {

    static class Node {
        int num;
        Node link;

        public Node(int num, Node link) {
            this.num = num;
            this.link = link;
        }
    }

    static int N, prevCnt, from, to;
    static int[] times, accumulateTimes;
    static String[] ss;
    static Node[] adjNode;
    static int[] inDegree;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        adjNode = new Node[N + 1];
        inDegree = new int[N + 1];
        accumulateTimes = new int[N + 1];
        times = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            ss = in.readLine().split(" ");
            times[i] = Integer.parseInt(ss[0]);

            prevCnt = Integer.parseInt(ss[1]);
            to = i;
            for(int j = 2; j < 2 + prevCnt; j++) {
                from = Integer.parseInt(ss[j]);
                adjNode[from] = new Node(to, adjNode[from]);
                inDegree[to]++;
            }
        }

        topologySorting();

        int maxTime = 0;
        for(Integer accumulateTime : accumulateTimes)
            maxTime = Math.max(maxTime, accumulateTime);

        System.out.println(maxTime);
    }

    private static void topologySorting() {

        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            if(inDegree[i] == 0) {
                q.add(i);
                accumulateTimes[i] = times[i];
            }
        }

        while(!q.isEmpty()) {
            int nodeNum = q.poll();
            Node node = adjNode[nodeNum];
            if(node != null) {
                accumulateTimes[node.num] = Math.max(accumulateTimes[node.num], accumulateTimes[nodeNum] + times[node.num]);
            }

            for(Node i = node; i != null; i = i.link) {
                inDegree[i.num]--;

                if(i.link != null) {
                    accumulateTimes[i.link.num] = Math.max(accumulateTimes[i.link.num], accumulateTimes[nodeNum] + times[i.link.num]);
                }

                if(inDegree[i.num] == 0) {
                    q.add(i.num);
                }
            }
        }
    }
}