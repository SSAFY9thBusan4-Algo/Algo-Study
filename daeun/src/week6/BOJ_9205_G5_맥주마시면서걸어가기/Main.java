package week6.BOJ_9205_G5_맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		String[] split = new String[2];
		for(int t=0;t<T;t++) {
			String result = "sad";
			int n = Integer.parseInt(br.readLine());
			Point[] point = new Point[n+2];
			boolean[] visit = new boolean[n+2];
		
			for(int i=0;i<n+2;i++) {
				split = br.readLine().split(" ");
				point[i] = new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])); 
			}
			Queue<Point> queue = new ArrayDeque<>();
			queue.offer(point[0]);
			visit[0] = true;
			Point now;
			while(!queue.isEmpty()) {
				now = queue.poll();				
				for(int i=1;i<n+2;i++) {
					if(visit[i]) continue;
					int dis = Math.abs(now.x-point[i].x)+Math.abs(now.y-point[i].y);
					if(dis<=1000) { 
						if(i==n+1) {
							result = "happy";
							break;
						}
						queue.offer(point[i]);
						visit[i] = true;
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}