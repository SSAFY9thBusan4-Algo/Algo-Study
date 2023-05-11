import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> plusPQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minusPQ = new PriorityQueue<>(Collections.reverseOrder());
		
		int max = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int book = Integer.parseInt(st.nextToken());
			max = Math.max(max, Math.abs(book));
			
			if(book > 0) plusPQ.offer(book);
			else minusPQ.offer(Math.abs(book));
		}
		
		int ans = 0;
		while(!plusPQ.isEmpty()) {
			ans += plusPQ.poll()*2; // 정리하고 다시 와야해서 2번 이동
			for(int i=0; i<m-1; i++) {
				if(!plusPQ.isEmpty()) plusPQ.poll();
			}
		}

		while(!minusPQ.isEmpty()) {
			ans += minusPQ.poll()*2;
			for(int i=0; i<m-1; i++) {
				if(!minusPQ.isEmpty()) minusPQ.poll();
			}
		}
		
		// 가장 큰 값은 마지막에 한번만 이동
		System.out.println(ans - max);
	}
}
