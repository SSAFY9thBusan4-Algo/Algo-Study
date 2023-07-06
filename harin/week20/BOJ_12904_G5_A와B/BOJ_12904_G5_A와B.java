import java.io.*;

public class BOJ_12904_G5_A와B {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String S = in.readLine();
		String T = in.readLine();

    // 문자열 T와 S의 길이와 같아질 때 까지 반복
		while(T.length() != S.length()) {
			char lastCh = T.charAt(T.length() - 1); //T의 마지막 글자 확인
			T = T.substring(0, T.length()-1); //T 마지막 글자 없애기 (마지막 글자가 A,B 일 때)
			if(lastCh == 'B') { //마지막 글자가 B일때
				String reverse = "";
				for(int i=T.length() - 1; i>=0; i--) { // 문자열 역순으로 변환
					reverse += T.charAt(i);
				}
				T = reverse;
			}
		}
		
		if(T.equals(S)) System.out.println(1); //만약 T가 S와 같다면 1 출력
		else System.out.println(0); // 아니라면 0 출력
	}
}
