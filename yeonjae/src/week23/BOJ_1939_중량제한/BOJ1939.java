package week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ1939 {

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

    static int N, M, A, B, C, S, E;
    static String[] ss;
    static Edge[] edges;
    static int[] parents;

    static void makeSet() {
        parents = new int[N + 1];
        for(int i = 1; i <= N; i++) {
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
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);

        edges = new Edge[M];
        for(int i = 0; i < M; i++) {
            ss = in.readLine().split(" ");
            A = Integer.parseInt(ss[0]);
            B = Integer.parseInt(ss[1]);
            C = Integer.parseInt(ss[2]);
            edges[i] = new Edge(A, B, C);
        }

        ss = in.readLine().split(" ");
        S = Integer.parseInt(ss[0]);
        E = Integer.parseInt(ss[1]);

        Arrays.sort(edges, Collections.reverseOrder());

        makeSet();

        int minWeight = 0;
        boolean visitStart = false;
        boolean visitEnd = false;

        for(Edge edge : edges) {
            if(union(edge.from, edge.to)) {
                minWeight = edge.weight;

                if(edge.from == S || edge.to == S)
                    visitStart = true;
                if(edge.from == E || edge.to == E)
                    visitEnd = true;

                if(visitStart && visitEnd && findSet(S) == findSet(E))
                    break;
            }
        }

        System.out.println(minWeight);
    }
}
