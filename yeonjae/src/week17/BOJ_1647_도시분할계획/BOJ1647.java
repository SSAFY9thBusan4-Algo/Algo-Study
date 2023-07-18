import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ1647 {

    static int vertexNum, edgeNum;
    static Edge[] edges;
    static int[] parents;
    static String[] ss;
    static Map<Pair, Integer> minWays;

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Edge implements Comparable<Edge> {

        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static void makeSet() {

        parents = new int[vertexNum + 1];

        for(int i = 1; i <= vertexNum; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int a) {
        if(parents[a] == a)
            return a;
        else
            return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot)
            return false;

        parents[aRoot] = bRoot;
        return true;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        vertexNum = Integer.parseInt(ss[0]);
        edgeNum = Integer.parseInt(ss[1]);

        // 중복 제거
        // from-to가 같은 길이 존재하는 경우 가장 작은 유지비가 드는 길만 선택
        minWays = new HashMap<>();
        for(int i = 0; i < edgeNum; i++){
            ss = in.readLine().split(" ");
            int from = Integer.parseInt(ss[0]);
            int to = Integer.parseInt(ss[1]);
            int weight = Integer.parseInt(ss[2]);

            Pair pair = new Pair(Math.min(from, to), Math.max(from, to));
            // 중복인 경우 (weight가 더 작은 길을 선택)
            if(minWays.containsKey(pair)){
                minWays.put(pair, Math.min(minWays.get(pair), weight));
            }
            // 중복이 아닌 경우
            else {
                minWays.put(pair, weight);
            }
        }


        // 중복이 제거된 길들을 edges에 넣기
        edges = new Edge[minWays.size()];
        int count = 0;
        for(Pair pair : minWays.keySet()) {
            edges[count++] = new Edge(pair.y, pair.x, minWays.get(pair));
        }

        Arrays.sort(edges);

        makeSet();
        int mstWeight = 0;
        int edgeCount = 0;

        for(Edge edge : edges) {
            if(union(edge.from, edge.to)) {
                mstWeight += edge.weight;

                if(++edgeCount == vertexNum - 2)
                    break;
            }
        }

        System.out.println(mstWeight);
    }
}