package week3.PRG_60058_LV2_괄호변환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Solution {
	public static String answer;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		Solution sol = new Solution();
		System.out.println(sol.solution(in.readLine()));
	}

	public String solution(String p) {
		String answer = mainAlgorithm(p);
		return answer;
	}

	// 입력을 올바른 문자열로 변환하는 핵심 메소드
	public static String mainAlgorithm(String w) {
		// 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환
		if (w == "")
			return "";

		// 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
		String[] UV = seperateUV(w);
		String u = UV[0];
		String v = UV[1];

		String str = "";
		// 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행
		if (isCorrect(u)) {
			// 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환
			return u.concat(mainAlgorithm(v));
		}
		// 4. 문자열 u가 "올바른 괄호 문자열"이 아닐 때
		else {
			// 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙임
			str = "(";
			// 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙임
			str = str.concat(mainAlgorithm(v));
			// 4-3. ')'를 다시 붙임
			str = str.concat(")");
			// 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙임
			str = str.concat(reverse(u.substring(1, u.length() - 1)));
		}

		// 4-5. 생성된 문자열을 반환
		return str;
	}

	// 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리하는 메소드
	private static String[] seperateUV(String w) {
		String[] arr = w.split("");

		int leftCnt = 0; // (의 개수를 count
		int rightCnt = 0; // )의 개수를 count
		String u = "";
		String v = "";

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("("))
				leftCnt++;
			if (arr[i].equals(")"))
				rightCnt++;

			// 균형잡힌 괄호 문자열일 때("("의 개수와 "("의 개수가 같을 때)
			if (leftCnt == rightCnt) {
				u = String.join("", Arrays.copyOf(arr, i + 1));
				if (i + 1 < arr.length) // u가 전체 문자열일 때 인덱스 초과가 나올 수 있으므로 예외 처리
					v = String.join("", Arrays.copyOfRange(arr, i + 1, arr.length));
				break;
			}
		}

		return new String[] { u, v };
	}

	// 3. 문자열 u가 "올바른 괄호 문자열"인지 확인 하는 메소드
	private static boolean isCorrect(String u) {
		Stack<String> stack = new Stack<>();
		String arr[] = u.split("");

		// "("일 때 stack에 넣고 ")"일 때 stack을 pop해서 짝이 맞는지 비교
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(")")) {
				if (!stack.isEmpty()) {
					// 괄호짝이 맞지 않을 때
					if (!stack.pop().equals("(")) {
						return false;
					}
				}
				// ")"가 "("보다 많을 때
				else {
					return false;
				}
			} else {
				stack.push(arr[i]);
			}

		}

		// "("가 ")"보다 많을 때
		if (!stack.isEmpty())
			return false;

		return true;
	}

	// 4-4. 문자열의 괄호 방향을 뒤집는 메소드
	private static String reverse(String substring) {
		String arr[] = substring.split("");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("(")) {
				arr[i] = ")";
			} else if (arr[i].equals(")")) {
				arr[i] = "(";
			}
		}

		return String.join("", arr);
	}

}
