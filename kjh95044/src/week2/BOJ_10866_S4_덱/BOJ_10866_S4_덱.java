package week2.BOJ_10866_S4_덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_10866_S4_덱 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		Deque<Integer> dq = new ArrayDeque<>();
		
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int num = 0;
			
			// push front,back 경우에 뒤에 추가적으로 int가 오기 떄문에 이를 st로 해결해줬다.
			while(st.hasMoreTokens()) {
				num = Integer.parseInt(st.nextToken());
			}
			
			// 삼항 연산자를 이용해 비어있을 경우에는 -1을 sb에 저장해줬다.
			switch (cmd) {
			case "push_front":
				dq.offerFirst(num);
				break;
			case "push_back":
				dq.offerLast(num);
				break;
			case "pop_front":
				sb.append((dq.isEmpty()? -1 :dq.pollFirst()) + "\n");
				break;
			case "pop_back":
				sb.append((dq.isEmpty()? -1 : dq.pollLast()) + "\n");
				break;
			case "size":
				sb.append(dq.size() + "\n");
				break;
			case "empty":
				sb.append((dq.isEmpty()? 1 : 0) + "\n");
				break;
			case "front":
				sb.append((dq.isEmpty()? -1 : dq.peekFirst()) + "\n");
				break;
			case "back":
				sb.append((dq.isEmpty()? -1 : dq.peekLast()) + "\n");
				break;

			}
		}
		System.out.println(sb);
	}
}
