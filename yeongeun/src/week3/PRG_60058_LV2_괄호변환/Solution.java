package src.week3.PRG_60058_LV2_괄호변환;

class Solution {
    public String solution(String p) {
        /*
        w = u + v

        u <- 올바른
        w = u + f(v);

        u <- 올바르지 않음.
        1. result = ( + f(v) + )
        2. result += u의 첫째, 마지막 제거. 괄호 뒤집기.
        문자열 반환.

         */
        return translate(p);
    }

    private String translate(String s) {
        int len = s.length();
        int isOpen = 0; //+1 : 열림, -1 : 닫힘.
        boolean flag = true;    // 전체 string이 올바른 괄호 문자열 인가?
        boolean flagU = true;   // u string이 올바른 괄호 문자열 인가?
        int splitidx = -1;      // u와v를 가르는 index 지점.
        for(int idx = 0 ; idx < len; idx++) {
            if(s.charAt(idx) == '(') isOpen++;
            else isOpen--;

            if(isOpen < 0) {    // 열림 없이 닫힘 먼저오면.
                flag = false;
            }
            if(isOpen == 0 && splitidx == -1) { // 균형잡힌 괄호문자열 u.
                splitidx = idx;
                flagU = flag;
            }
        }

        if(flag && isOpen == 0) {  // 올바른 괄호 문자열
            return s;
        }
        else {  // w = u(left) + v(right)

            StringBuilder sb = new StringBuilder();
            if(flagU) {		//u가 올바른 괄호라면 u + v
                sb.append(s, 0, splitidx+1)
                        .append(translate(s.substring(splitidx + 1)));
            }
            else {			//u가 올바르지 않으면 (v) + u자르고 반대로
                sb.append('(').append(translate(s.substring(splitidx + 1))).append(')');

                char[] array = s.substring(1, splitidx).toCharArray();
                for (char c : array) {
                    if (c == '(') sb.append(')');
                    else sb.append('(');
                }

            }

            return sb.toString();
        }

    }
}