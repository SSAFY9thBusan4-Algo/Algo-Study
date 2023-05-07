package week13.BOJ_9935_G4_문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	private static char[] characters;
	private static char[] pattern;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		characters = in.readLine().toCharArray();
		pattern = in.readLine().toCharArray();
		
		Stack<Character> stack = new Stack<>();
		
		// 문자열 스텍에 넣으면서 폭발시키기
		for (char c : characters) {
			stack.push(c);
			if (stack.size() >= pattern.length) {
				boolean isBomb = true;
				for (int i=pattern.length-1, j=stack.size()-1; i>=0; i--,j--) {
					if (stack.get(j) != pattern[i]) {
						isBomb = false;
						break;
					}
				}
				if (isBomb) {
					for (int i=0; i<pattern.length; i++) {
						stack.pop();
					}
				}
			}
				
		}
		
		if (stack.size()!=0) {
			StringBuilder sb = new StringBuilder();
			for (Character c:stack) sb.append(c);
			System.out.println(sb.toString());
		}else {
			System.out.println("FRULA");
		}
				
	}

}
