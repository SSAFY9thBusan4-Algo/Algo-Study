import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N;

    static class Subject implements Comparable<Subject> {
        int start, end;

        public Subject(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Subject o) {
            if(start == o.start)
                return Integer.compare(end, o.end);
            else
                return Integer.compare(start, o.start);

        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Subject[] subjects = new Subject[N];

        for (int n = 0; n < N; n++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            subjects[n] = (new Subject(x, y));
        }
        Arrays.sort(subjects); // 시작 시간 기준으로 정렬, 시작 시간이 같다면 종료 시간 기준으로 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(subjects[0].end);

        for(int i=1; i<N; i++){
            if(pq.peek() <= subjects[i].start){
                pq.poll();
            }
            pq.offer(subjects[i].end);
        }

        System.out.println(pq.size());
    }
    /*
8
1 8
9 16
3 7
8 10
10 14
5 6
6 11
11 12
     */
}
