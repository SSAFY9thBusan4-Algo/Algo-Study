package week3.PRG_60058_LV2_괄호변환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String p = in.readLine();
		
		System.out.println(solution(p));
		
	}

	private static String solution(String p) {
		if (p.length() == 0) return p;
		int cnt1=0, cnt2=0, idx = 0;
		while ((cnt1==0 && cnt2==0) || cnt1!=cnt2) {
			if (p.charAt(idx) == '(') cnt1++;
			else if (p.charAt(idx) == ')') cnt2++;
			idx++;
		}
		String u = p.substring(0,idx);
		String v = p.substring(idx, p.length());
		
		if (isright(u)) {
			return u + solution(v);
		}
		else {
			StringBuilder rev = new StringBuilder();
			String s = u.substring(1,u.length()-1);
			rev.append(reverse(s));
			return "(" + solution(v) + ")" + rev.toString();
		}
		
	}

	private static String reverse(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<str.length(); i++) {
			if (str.charAt(i)=='(') sb.append(')');
			else sb.append('(');
		}
		return sb.toString();
	}

	private static boolean isright(String s) {
		Stack<Character> stack = new Stack<>();
		Stack<Character> sub = new Stack<>();
		for (int i=0; i<s.length(); i++) {
			stack.push(s.charAt(i));
		}		
		while (!stack.isEmpty()) {
			char cur = stack.pop();
			if (sub.size()==0) sub.push(cur);
			else if (cur == ')') {
				sub.push(cur);
			}
			else {
				if (sub.isEmpty()) return false;
				sub.pop();
			}
		}
		if (sub.isEmpty()) return true;
		else return false;
	}
	
}

/*
 * 제출코드
 * import java.util.Stack;

class Solution {
    public String solution(String p) {
		
		return sol(p);
		
	}

	private static String sol(String p) {
		if (p.length() == 0) return p;
		int cnt1=0, cnt2=0, idx = 0;
		while ((cnt1==0 && cnt2==0) || cnt1!=cnt2) {
			if (p.charAt(idx) == '(') cnt1++;
			else if (p.charAt(idx) == ')') cnt2++;
			idx++;
		}
		String u = p.substring(0,idx);
		String v = p.substring(idx, p.length());
		
		if (isright(u)) {
			return u + sol(v);
		}
		else {
			StringBuilder rev = new StringBuilder();
			String s = u.substring(1,u.length()-1);
			rev.append(reverse(s));
			return "(" + sol(v) + ")" + rev.toString();
		}
		
	}
    
    private static String reverse(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<str.length(); i++) {
			if (str.charAt(i)=='(') sb.append(')');
			else sb.append('(');
		}
		return sb.toString();
	}

	private static boolean isright(String s) {
		Stack<Character> stack = new Stack<>();
		Stack<Character> sub = new Stack<>();
		for (int i=0; i<s.length(); i++) {
			stack.push(s.charAt(i));
		}		
		while (!stack.isEmpty()) {
			char cur = stack.pop();
			if (sub.size()==0) sub.push(cur);
			else if (cur == ')') {
				sub.push(cur);
			}
			else {
				if (sub.isEmpty()) return false;
				sub.pop();
			}
		}
		if (sub.isEmpty()) return true;
		else return false;
    }
}

 */
