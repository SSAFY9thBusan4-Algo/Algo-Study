package week3.PRG_60058_LV2_괄호변환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(solution(br.readLine()));
	}

	public static String solution(String p) {
		/*
		 * 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
		 */
		if (p.length()==0) {
			return "";
		}
		/*
		 * 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 
		 * 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야하며, v는 빈 문자열이 될 수 있습니다. 
		 */
		String u = "";
		String v = "";
		int open=0, close=0;
		
		for(int i=0;i<p.length();i++) {
			if(p.charAt(i)=='(') {
				open++;
			}
			else {
				close++;
			}
			u+=p.charAt(i);
			if(open == close) { //여는 괄호랑 닫는 괄호의 수가 같으면 (균형잡힌 괄호 문자열)
				v=p.substring(i+1);
				break;
			}
		}		

		/*
		 * 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
		 * 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
		 */
		if(right(u)) {
			return u += solution(v);
		}
		
		/*
		 * 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
		 * 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
		 * 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어붙입니다. 
		 * 4-3. ')'를 다시 붙입니다. 
		 * 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에붙입니다. 
		 * 4-5. 생성된 문자열을 반환합니다.
		 */
		else {
			String temp = "(";
			temp += solution(v);
			temp += ")";
			u = u.substring(1, u.length()-1);
			for(int i=0;i<u.length();i++) {
				if(u.charAt(i) == '(') {
					temp += ')';
				}
				else {
					temp += '(';
				}
			}
			return temp;
		}
	}

	private static boolean right(String u) {
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<u.length();i++) {
			if(u.charAt(i) == '(') {
				stack.push('(');
			}
			else {
				if(stack.isEmpty() || stack.peek()!= '(') {
					return false;
				}
				else {
					stack.pop();
				}
			}
		}
		
		return true;
	}
}
