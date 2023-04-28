import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);
		int min = 0;
		
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		split = br.readLine().split(" ");
		int temp = Integer.parseInt(split[0]);
		for(int i=1;i<N;i++) {
			int now = Integer.parseInt(split[i]);
			minus.offer(now - temp);
			temp = now;
		}
		
		for(int i=0;i<N-K;i++) {
			min += minus.poll();
		}
		
		System.out.println(min);
	}	
}
