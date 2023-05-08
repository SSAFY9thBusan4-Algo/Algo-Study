pakage src.week14.BOJ_2866_G5_문자열잘라내기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
    // input
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);
		
		char[][] input = new char[N][];
		for(int i = 0 ; i < N ; i++) {
			input[i] = br.readLine().toCharArray();
		}
		
    // solve
		StringBuilder[] builder = new StringBuilder[M];
		for(int i = 0 ; i < M ; i++) builder[i] = new StringBuilder();
		
		boolean[] isSame = new boolean[N];	// N번째 행에 같은 문자열이 있는가.
		
		for(int i = N-1 ; i > 0 ; i--) {
			Set<String> set = new HashSet<>();  // 중복검사
			for(int j = 0 ; j < M ; j++) {
				String s = builder[j].append(input[i][j]).toString();
				if(set.contains(s)) isSame[i] = true;
				set.add(s);
			}
		}
		
    // output
		int count = 0;
		for(int i = 1 ; i < N ; i++) {
			if(isSame[i]) break;
			count++;
		}
		
		System.out.println(count);
	}
}
