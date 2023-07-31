package week23.BOJ_13335_S1_트럭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
				
		int[] trucks = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		
		Queue<Integer> bridge = new ArrayDeque<Integer>();
		for (int i=1; i<w; i++) bridge.offer(0);;
		bridge.offer(trucks[0]);
		int time = 1;
		int tw = trucks[0];
		int next = 1;
		while(!bridge.isEmpty()) {
						
			tw-=bridge.poll();
			
			// 다음 트럭이 들어갔을 때 최대하중을 넘지 않으면 다리위로
			if (next<n) {
				if (trucks[next]+tw<=L) {		
					tw+=trucks[next];
					bridge.offer(trucks[next]);
					next++;
				}else {
					bridge.offer(0);
				}				
			}
			time++;
		}
		
		System.out.println(time);
	}
	
}
