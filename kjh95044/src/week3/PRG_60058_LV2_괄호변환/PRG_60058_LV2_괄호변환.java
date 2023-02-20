package week3.PRG_60058_LV2_괄호변환;

public class PRG_60058_LV2_괄호변환 {
	// 균형 잡힌 괄호 문자열
	// '(' 와 ')' 로만 이루어진 문자열이 있을 경우,
	// '(' 의 개수와 ')' 의 개수가 같다면 이를 균형잡힌 괄호 문자열이라고 부릅니다.

	// 괄호 문자열
	// 그리고 여기에 '('와 ')'의 괄호의 짝도 모두 맞을 경우에는
	// 이를 올바른 괄호 문자열이라고 부릅니다.

	static public String solution(String p) {
		String answer = "";
//      1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
		if (p.equals(""))
			return answer;

//      2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 
//      단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, 
//		   v는 빈 문자열이 될 수 있습니다. 

		int leftCnt = 0;
		int rightCnt = 0;
		String u = "";
		String v = "";

		for (int i = 0; i < p.length(); i++) {
			char c = p.charAt(i);
			if (c == '(') {
				leftCnt++;
			} else {
				rightCnt++;
			}

			// 왼쪽 괄호와 오른쪽 괄호의 갯수가 같으면 균형잡힌 괄호 문자열이다.
			// 인풋으로 균형잡힌 괄호 문자열만 들어오기 때문에 위를 제외한 나머지도 균형잡힌 괄호 문자열이다.
			if (leftCnt == rightCnt) {
				u = p.substring(0, i + 1);
				v = p.substring(i + 1, p.length());
				break;
			}
		}

		// System.out.println(isCorrectbracketStr(u));

		// 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
		// 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.

		if (isCorrectBracketStr(u)) {
			// 1단계부터 다시 수행
			answer = u + solution(v);
		} else {
			// 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
			// 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
			String temp = "(";
			// 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
			temp+= solution(v);
			// 4-3. ')'를 다시 붙입니다.
			temp+= ")";
			// 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
			temp+= reverseBracket(u.substring(1, u.length()-1));
			// 4-5. 생성된 문자열을 반환합니다.
			return temp;
		}

		return answer;
	}

	private static boolean isCorrectBracketStr(String u) {
		int cnt = 0;
		for (char c : u.toCharArray()) {
			if (c == '(') {
				cnt++;
			} else {
				cnt--;
			}
			if (cnt < 0) {
				return false;
			}
		}
		return true;
	}
	
	private static String reverseBracket(String bracket) {
		String result ="";
		for(char c : bracket.toCharArray()) {
			if(c == '(') {
				result += ')';
			}
			else {
				result += '(';
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// "(()())()"
		String s = solution("(()())()");
		System.out.println(s);

		// "()"
		s = solution(")(");
		System.out.println(s);

		// "()(())()"
		s = solution("()))((()");
		System.out.println(s);

	}
}
