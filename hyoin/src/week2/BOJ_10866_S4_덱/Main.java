package week2.BOJ_10866_S4_덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();

		int N = Integer.parseInt(st.nextToken());
		int X;
		Integer pop;

		ArrayDeque<Integer> deque = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			switch (command) {
			case "push_front":
				X = Integer.parseInt(st.nextToken());
				deque.addFirst(X);
				break;
			case "push_back":
				X = Integer.parseInt(st.nextToken());
				deque.addLast(X);
				break;
			case "pop_front":
				pop = deque.pollFirst();
				if(pop==null) {
					pop=-1;
				}
				sb.append(pop+"\n");
				break;
			case "pop_back":
				pop = deque.pollLast();
				if(pop==null) {
					pop=-1;
				}
				sb.append(pop+"\n");
				break;
			case "size":
				sb.append(deque.size()+"\n");
				break;
			case "empty":
				if(deque.isEmpty()) {
					sb.append("1\n");
				}
				else {
					sb.append("0\n");
				}
				break;
			case "front":
				if(deque.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(deque.getFirst()+"\n");
				}
				break;
			case "back":
				if(deque.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(deque.getLast()+"\n");
				}
				break;
			}
			
		}
		
		System.out.println(sb);
	}

}
