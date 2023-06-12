import java.io.IOException;
import java.util.Scanner;

public class Main {
	static boolean[][] visit;
	static int result;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		visit = new boolean[1501][1501];
		
		if((A+B+C)%3 != 0) { //3 그룹으로 나눠지는지
			result = 0;
		}
		else {
			dfs(A, B, C);
		}
		
		System.out.println(result);
	}

	private static void dfs(int a, int b, int c) {
		if(a==b && b==c) {
			result = 1;
			return;
		}
		
		check(a, b, c);
		check(b, c, a);
		check(c, a, b);		
	}

	private static void check(int g1, int g2, int etc) {
		int min = Math.min(g1, g2);
		int max = Math.max(g1, g2);
		
		if(!visit[min+min][max-min]) {
			visit[min+min][max-min] = true;
			dfs(min+min, max-min, etc);
		}
		
	}
}
