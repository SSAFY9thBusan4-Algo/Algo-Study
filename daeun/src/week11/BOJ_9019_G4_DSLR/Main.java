import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Num{
		int num;
		String cmd;
		public Num(int num, String cmd) {
			super();
			this.num = num;
			this.cmd = cmd;
		}
	}
	static Queue<Num> queue;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			String result = "";
			String[] split = br.readLine().split(" ");
			int A = Integer.parseInt(split[0]);
			int B = Integer.parseInt(split[1]);
			visit = new boolean[10000]; //최소 횟수
			
			queue = new ArrayDeque<>();
			queue.offer(new Num(A, ""));
			visit[A] = true;
			while(!queue.isEmpty()) {
				Num now = queue.poll();
				int num = now.num;
				String cmd = now.cmd;
				if(num == B) {
					result = now.cmd;
					break;
				}
				
				int D = (2*num)%10000;
				if(!visit[D]) {
					queue.offer(new Num(D, cmd+"D"));
					visit[D] = true;
				}
				
				int S = (num == 0) ? 9999 : num-1;
				if(!visit[S]) {
					queue.offer(new Num(S, cmd+"S"));
					visit[S] = true;
				}
				
				int L = (num % 1000) * 10 + num/1000;
				if(!visit[L]) {
					queue.offer(new Num(L, cmd+"L"));
					visit[L] = true;
				}
				
				int R = (num % 10) * 1000 + num/10;
				if(!visit[R]) {
					queue.offer(new Num(R, cmd+"R"));
					visit[R] = true;
				}
			}			
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
