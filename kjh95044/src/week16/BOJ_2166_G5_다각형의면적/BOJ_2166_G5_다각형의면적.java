import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        long x,y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        Point[] points = new Point[N+1];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x,y);
        }

        points[N] = new Point(points[0].x, points[0].y);

        long sumA = 0;
        long sumB = 0;

        for(int i=0; i<N; i++){
            sumA += points[i].x * points[i+1].y;
            sumB += points[i+1].x * points[i].y;
        }
        System.out.printf("%.1f%n", (Math.abs(sumA - sumB) * 0.5));

    }
}
