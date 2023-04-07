import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		} 
		
		int result = 0;
		while(pq.size() > 1) {
			int one = pq.poll();
			int two = pq.poll();
			result += one+two;
			pq.offer(one+two);
		}
		
		System.out.println(result);
	}
}
