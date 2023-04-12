import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static class Class implements Comparable<Class>{
		int start;
		int end;
		public Class(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Class o) {
			if(this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Class> pq = new PriorityQueue<>();
    
		for(int i=0;i<N;i++) {
			String[] split = br.readLine().split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			pq.offer(new Class(a, b));
		}
    
		PriorityQueue<Integer> pass = new PriorityQueue<>(); //Integer로
    
		while(!pq.isEmpty()) {
			Class now = pq.poll();
      //통과한 녀석의 끝나는 시간이랑 비교해서 가능한 시간이라면
			if(!pass.isEmpty() && now.start >= pass.peek()) { 
				pass.poll(); 
			}
			pass.offer(now.end);
		}
    
		System.out.println(pass.size());
	}
}
