import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1238 {

    static int N, M, X, start, end, weight, maxDis;
    static String[] ss;
    static int[][] weights;
    static boolean[] visited;
    static int[] minDistances;
    static int[] roundTripDistances;
    static final int INF = Integer.MAX_VALUE;

    static class Vertex implements Comparable<Vertex> {
        public int idx;
        public int minDistance;

        public Vertex(int idx, int minDistance) {
            this.idx = idx;
            this.minDistance = minDistance;
        }


        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.minDistance, o.minDistance);
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);
        X = Integer.parseInt(ss[2]);

        weights = new int[N+1][N+1];
        for(int i = 0; i < M; i++) {
            ss = in.readLine().split(" ");
            start = Integer.parseInt(ss[0]);
            end = Integer.parseInt(ss[1]);
            weight = Integer.parseInt(ss[2]);

            weights[start][end] = weight;
        }



        roundTripDistances = new int[N+1];

        // 시작점이 s인 다익스트라
        for(int s = 1; s <= N; s++) {
            visited = new boolean[N+1];
            minDistances = new int[N+1];
            Arrays.fill(minDistances, INF);
            minDistances[s] = 0;

            PriorityQueue<Vertex> pq = new PriorityQueue<>();
            pq.offer(new Vertex(s, minDistances[X]));

            while(!pq.isEmpty()) {

                Vertex currentV = pq.poll();
                int currentVIdx = currentV.idx;
                if(visited[currentVIdx])
                    continue;

                for(int i = 1; i <= N; i++) {
                    if(visited[i])
                        continue;

                    if(weights[currentVIdx][i] == 0)
                        continue;

                    if(minDistances[currentVIdx] + weights[currentVIdx][i] < minDistances[i]) {
                        minDistances[i] = minDistances[currentVIdx] + weights[currentVIdx][i];
                        pq.add(new Vertex(i, minDistances[i]));
                    }

                }
            }

            // 파티장소인 경우, 파티장소에서 집으로 이동한다고 생각
            if(s == X) {
                for(int i = 1; i <= N; i++) {
                    roundTripDistances[i] += minDistances[i];
                }
            }
            // 파티 장소가 아닌 경우, 집에서 파티장소로 이동한다고 생각
            else {
                roundTripDistances[s] += minDistances[X];
            }
        }

        for(int i = 1; i <= N; i++) {
            maxDis = Math.max(maxDis, roundTripDistances[i]);
        }

        System.out.println(maxDis);
    }
}