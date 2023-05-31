import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static class Point{
		long x;
		long y;
		public Point(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double result = 0;
		int N = Integer.parseInt(br.readLine());
		ArrayList<Point> list = new ArrayList<>();
		for(int i=0;i<N;i++) {
			String[] split = br.readLine().split(" ");
			long x = Long.parseLong(split[0]);
			long y = Long.parseLong(split[1]);
			list.add(new Point(x, y));
		}
		for(int i=1;i<N-1;i++) {
			Point p1 = list.get(0);
			Point p2 = list.get(i);
			Point p3 = list.get(i+1);
			long one = p1.x*p2.y+p2.x*p3.y+p3.x*p1.y;
			long two = p1.x*p3.y+p2.x*p1.y+p3.x*p2.y;
			result += one - two;
		}
		System.out.printf("%.1f",Math.abs(result/2));
	}
}
