package src.week2.BOJ_10866_S4_덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Main {
	private static Deque<Integer> list = new LinkedList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 입력 객체화
		int N = Integer.parseInt(br.readLine());
		
		// 알고리즘 풀기
		for(int line = 0 ; line < N ; line++) {
			String command = br.readLine();
			if(command.charAt(1) == 'u') push(command);
			else sb.append(excute(command)).append("\n");
			
		}
		
		System.out.println(sb);
	}
	
	
	private static int excute(String command) {
		switch(command.charAt(0)) {
		case 's' :	//size
			return list.size();
		case 'e' :	//empty
			if(list.isEmpty()) return 1;
			else return 0;
		case 'f' :	//front
			if(list.peekFirst() == null)
				return -1;
			else
				return list.peekFirst();
		case 'b' :	//back
			if(list.peekLast() == null)
				return -1;
			else
				return list.peekLast();
		default :	//pop
			switch(command.charAt(4)) {
			case 'f' : //pop_front
				try {
					return list.removeFirst();
				}
				catch(NoSuchElementException e) {
					return -1;
				}
			case 'b' : //pop_front
				try {
					return list.removeLast();
				}
				catch(NoSuchElementException e) {
					return -1;
				}
			}
		}
		return -2;
	}
	private static void push(String command) {
		int num = Integer.parseInt(command.split(" ")[1]);
		if(command.charAt(5) == 'f') {
			list.addFirst(num);
		}
		else {
			list.addLast(num);
		}
	}
}
