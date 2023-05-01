package week13.BOJ_9935_G4_문자열폭발;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935_G4_문자열폭발 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		String str = in.readLine();
		String bombStr = in.readLine();

		Stack<Character> result = new Stack<>(); // 결과 문자열 담을 스택

		for (int i = 0; i < str.length(); i++) {
			char curChar = str.charAt(i);
			result.push(curChar);

			boolean isSame = true;
			if (result.size() >= bombStr.length()) { // 현재 남아있는 문자열이 폭발할 문자열보다 길다면 폭발 가능한지 확인
				// 폭발 문자열과 비교
				for (int j = 0; j < bombStr.length(); j++) { // 뒤에서부터 폭발문자열 크기만큼 비교
					if (result.get(result.size() - bombStr.length() + j) != bombStr.charAt(j)) { // 문자열이 같지 않다면 더이상 비교할 필요 X
						isSame = false;
						break;
					}
				}

				// 문자열이 폭발 문자열이라면 해당 크기만큼 폭발시킴
				if (isSame) {
					for (int j = 0; j < bombStr.length(); j++) {
						result.pop();
					}
				}
			}
		}

		if (result.size() == 0) {
			sb.append("FRULA");
		} else {
			for (int i = 0; i < result.size(); i++) {
				sb.append(result.get(i));
			}
		}
		System.out.println(sb.toString());

		// 메모리 초과난 코드
/*		int start = 0;
		int end = start + bombStr.length();
		for (int i = start; i < str.length() - bombStr.length() + 1; i++) {
			start = i;
			end = start + bombStr.length();
			String sub = str.substring(start, end);
			if (bombStr.equals(sub)) {
				sb.append(str.substring(0, start));
				sb.append(str.substring(end, str.length()));
				str = sb.toString();
				i = i - 2;
				if (i < -1) {
					i = -1;
				}
				sb = new StringBuffer();
			}
		}

		if (str.equals("")) {
			System.out.println("FRULA");
		} else {
			System.out.println(str);
		}*/

	}
}
