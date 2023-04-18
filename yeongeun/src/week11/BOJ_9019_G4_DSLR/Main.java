pakage src.week11.BOJ_9019_G4_DSLR

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	private static class Num {
		int n;
		String oper;
		public Num(int n, String oper) {
			super();
			this.n = n;
			this.oper = oper;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<= T ; tc++) {
			String[] input = br.readLine().split(" ");
			
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			sb.append(run(a,b)).append('\n');
		}
		System.out.println(sb);
	}
	
	private static String run(int a, int b) {
		boolean[] visited = new boolean[10_001];
		Queue<Num> queue = new ArrayDeque<>();
		queue.offer(new Num(a,""));
		visited[a] = true;
		
		while(!queue.isEmpty()) {
			Num cur = queue.poll();
			if(cur.n == b) return cur.oper;
			
			int next = operD(cur.n);
			if(!visited[next]) {
				queue.offer(new Num(next, cur.oper+'D'));
				visited[next] = true;
			}
			next = operS(cur.n);
			if(!visited[next]) {
				queue.offer(new Num(next, cur.oper+'S'));
				visited[next] = true;
			}
			next = operL(cur.n);
			if(!visited[next]) {
				queue.offer(new Num(next, cur.oper+'L'));
				visited[next] = true;
			}
			next = operR(cur.n);
			if(!visited[next]) {
				queue.offer(new Num(next, cur.oper+'R'));
				visited[next] = true;
			}
		}
		
		return "";
	}
	
	private static int operD(int n) {
		return (n<<1)%10_000;
	}
	
	private static int operS(int n) {
		if(n == 0) return 9999;
		return --n;
	}
	
	private static int operL(int n) {
		int f = n/1000;
		return (n - f*1000)*10 + f;
	}
	
	private static int operR(int n) {
		int e = n%10;
		return n / 10 + e * 1000;
	}
}
