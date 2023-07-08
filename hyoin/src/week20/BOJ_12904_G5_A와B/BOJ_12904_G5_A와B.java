package week20.BOJ_12904_G5_A와B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12904_G5_A와B {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String S = in.readLine();
		String T = in.readLine();
		
		StringBuffer sb = new StringBuffer(T);
		// T를 기준으로 맨 끝이 A이면 삭제 or 맨끝이 B이면 삭제 후 뒤집기
		while(true) {
			// 종료조건
			// S와 처리된 T의 길이가 같을 때 S와 처리된 T가 같으면 1, 다르면 0
			if(S.length()==sb.length()) { 
				if(S.equals(sb.toString())) {
					System.out.println("1");
				}
				else {
					System.out.println("0");
				}
				break;
			}
			
			if(sb.charAt(sb.length()-1)=='A') { // 현재 T의 맨끝이 A이면 A삭제
				sb.deleteCharAt(sb.length()-1);
			}
			else if(sb.charAt(sb.length()-1)=='B') { // 현재 T의 맨끝이 B이면 B삭제 후 뒤집기
				sb.deleteCharAt(sb.length()-1);
				sb.reverse();
			}
		}
	}
}
