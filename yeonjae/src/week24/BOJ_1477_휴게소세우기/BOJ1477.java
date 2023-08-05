package week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1477 {

    static int N, M, L;
    static int[] Ls;
    static int[] dis;
    static String[] ss;
    static Distance[] distances;

    static class Distance implements Comparable<Distance> {
        int dis;
        int count;
        int comDis;

        public Distance(int dis, int count) {
            this.dis = dis;
            this.count = count;
            this.comDis = (int) Math.ceil((float) dis / count);
        }

        public void addCount(){
            this.count++;
            this.comDis = (int) Math.ceil((float) dis / count);
        }

        @Override
        public int compareTo(Distance o) {
            return Integer.compare(o.comDis, this.comDis);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ss = in.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        M = Integer.parseInt(ss[1]);
        L = Integer.parseInt(ss[2]);

        if(N == 0) {
            System.out.println((int) Math.ceil((float) L / (M + 1)));
            return;
        }

        Ls = new int[N + 1];
        ss = in.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            Ls[i] = Integer.parseInt(ss[i]);
        }
        Ls[N] = L;

        Arrays.sort(Ls);

        dis = new int[N + 1];
        dis[0] = Ls[0];
        for(int i = 1; i <= N; i++) {
            dis[i] = Ls[i] - Ls[i - 1];
        }

        PriorityQueue<Distance> pq = new PriorityQueue<>();
        for(int i = 0; i <= N; i++) {
            pq.add(new Distance(dis[i], 1));
        }

        for(int i = 0; i < M; i++) {
            Distance distance = pq.poll();
            if(distance.comDis > 1) {
                pq.add(new Distance(distance.dis, distance.count + 1));
            }

        }

        System.out.println(pq.poll().comDis);
    }
}
