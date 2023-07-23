import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335_S1_트럭 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		Queue<Integer> truck = new ArrayDeque<>(); // 대기중인 트럭
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<n; i++) {
			int w = Integer.parseInt(st.nextToken());
			truck.offer(w);
		}
		
		Queue<Integer> curTruck = new ArrayDeque<>(); // 현재 다리위에 있는 트럭의 무게(다리길이만큼 들어감)
		// 다리길이만큼 먼저 0으로 채움
		for(int i=0; i<W; i++) {
			curTruck.offer(0);
		}
		
		int curW=0; // 현재 다리위의 무게
		int result=0;
		while(!curTruck.isEmpty()) {
			// 다리위의 트럭 움직이기
			int rmW = curTruck.poll();
			curW-=rmW;
		
			// 대기중인 트럭 올리기
			if(!truck.isEmpty()) {
				if(curW+truck.peek()<=L) { // 대기중인 트럭을 올릴 수 있을 때
					int addW = truck.poll();
					curTruck.offer(addW);
					curW+=addW;
				}
				else {
					curTruck.offer(0);
				}
			}
			
			result++;
		}
		
		System.out.println(result);
	}
}
