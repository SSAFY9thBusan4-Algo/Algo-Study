package src.week18.BOJ_17218_비밀번호만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main17218_비밀번호만들기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] s1 = (' '+br.readLine()).toCharArray();	// 앞에 빈공간 하나 더 추가. 1부터 하려고
		char[] s2 = (' '+br.readLine()).toCharArray();
		
		int[][] d = new int[s1.length][s2.length];
		
		// 최장 공통 부분 찾기
		for(int i = 1 ; i < s1.length; i++) {
			for(int j = 1 ; j < s2.length ; j++) {
				
				if(s1[i] == s2[j]) {
					d[i][j] = d[i-1][j-1] +1;
				}
				else {
					d[i][j] = d[i-1][j] > d[i][j-1] ? d[i-1][j] : d[i][j-1]; 
				}
			}
		}
		
		// 공통된 부분의 문자열 찾기. 뒤에서부터.
		int x = s1.length-1, y = s2.length-1;
		char[] result = new char[40];
		int cnt = -1;
		
		while(d[x][y] != 0) {
			if(d[x][y] == d[x-1][y]) {
				x = x-1;
			}
			else if(d[x][y] == d[x][y-1]) {
				y = y-1;
			}
			else {
				result[++cnt] = s1[x];
				x = x-1;
				y = y-1;
			}
		}
		
		// 문자열 뒤집기
		StringBuilder sb = new StringBuilder();
		for(int i = cnt ; i >= 0 ; i--) {
			sb.append(result[i]);
		}
		System.out.println(sb);
	}
}
