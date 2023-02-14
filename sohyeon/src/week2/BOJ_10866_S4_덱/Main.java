package week2.BOJ_10866_S4_덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		// 덱
		List<Integer> deque = new ArrayList<Integer>();
		
		for (int i = 0; i < N; i++) {
			
			String[] str = in.readLine().split(" ");
			String ord = str[0];
			
			if (str.length == 1) {				
				if (ord.equals("size")) {
					sb.append(deque.size()+"\n");
				}
				else if (ord.equals("empty") ) {
					if (deque.isEmpty()) sb.append(1+"\n");
					else sb.append(0+"\n");
				}
				else if (ord.equals("front")) {
					if (deque.size() != 0) sb.append(deque.get(0)+"\n");
					else sb.append(-1+"\n");
					
				}
				else if (ord.equals("back")) {
					if (deque.size() != 0) sb.append(deque.get(deque.size()-1)+"\n");
					else sb.append(-1+"\n");
				}
				else if (ord.equals("pop_front")) {
					if (deque.size() != 0) sb.append(deque.remove(0)+"\n");
					else sb.append(-1+"\n");				
				}
				else if (ord.equals("pop_back")) {
					if (deque.size() != 0) sb.append(deque.remove(deque.size()-1)+"\n");
					else sb.append(-1+"\n");				
				}
			}
			else {
				int num = Integer.parseInt(str[1]);
				if (ord.equals("push_front")) {
					deque.add(0, num);
				}
				else if (ord.equals("push_back") ) {
					deque.add(num);
				}				
			}								
		}
		System.out.println(sb.toString());
	}
	
}