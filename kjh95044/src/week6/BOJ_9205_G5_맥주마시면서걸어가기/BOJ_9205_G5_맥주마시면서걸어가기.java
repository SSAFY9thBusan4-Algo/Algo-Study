package week6.BOJ_9205_G5_맥주마시면서걸어가기;

import java.util.*;
import java.io.*;

public class BOJ_9205_G5_맥주마시면서걸어가기 {

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static class Point{
        int x,y;

        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        public int dist(Point p) {
        	return Math.abs(this.x-p.x) + Math.abs(this.y - p.y);
        }
        
        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
    }
    
    static Point home, dest, convStores[];

    static Point getPoint() throws IOException {
        st= new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return new Point(x,y);
    }
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            convStores = new Point[N];

            home = getPoint();
            
            for(int i=0; i<N; i++) {
                convStores[i] = getPoint();
            }

            dest = getPoint();
            
            // 맥주 20개가 들어있는 한 박스를 가지고 출발
            // 50m 걸을때마다 한병씩 마신다
            // 거리가 1000넘으면 안된다.
            // 편의점에 들렸을 때 빈 병과 새 맥주를 교체 할수 있다.
            // -32768 ≤ x, y ≤ 32767

            bfs(N);
//            sb.append(Arrays.toString(convStores)).append("\n");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static HashMap<Point,Boolean> visited = new HashMap<>();
	private static void bfs(int N) {
		visited.clear();
		Queue<Point> queue = new ArrayDeque<>();
		visited.put(home, true);
		queue.offer(home);
		
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			// 현재 지점에서 바로 목표까지 도착할수 있으면 종료
			if(p.dist(dest) <=1000) {
				sb.append("happy");
				return;
			}
			
			for(int i=0; i<convStores.length; i++) {
				// 방문한적 없고, 맥주 20개로 버틸수 있는 곳이면 큐에 넣음
				if(!visited.getOrDefault(convStores[i], false) && convStores[i].dist(p)<=1000) {
					visited.put(convStores[i], true);
					queue.offer(convStores[i]);
				}
			}
		}

		// 갈 수 있는 루트가 없으면 sad로 종료
		sb.append("sad");
	}
}