package src.week20.BOJ_12904_G5_A와B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main12904 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuffer S = new StringBuffer(br.readLine());
		StringBuffer T = new StringBuffer(br.readLine());
		
		while(T.length() > S.length()) {
			if(T.charAt(T.length()-1) == 'A') {
				T.deleteCharAt(T.length()-1);
			}
			else {
				T.deleteCharAt(T.length()-1);
				T.reverse();
			}
		}
		
		int result = 1;
		for(int i = 0 ; i < S.length() ; i++) {
			if(S.charAt(i) != T.charAt(i)) result = 0;
		}
		
		System.out.println(result);
		
	}
}
