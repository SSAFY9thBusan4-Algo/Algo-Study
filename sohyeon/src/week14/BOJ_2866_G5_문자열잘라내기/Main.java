package week14.BOJ_2866_G5_문자열잘라내기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] table = new char[R][C];
		for (int r=0; r<R; r++) {
			table[r] = in.readLine().toCharArray();
		}
		
		Set<String> set = new HashSet<String>();
		for (int c=0; c<C; c++) {
			String s = "";
			for (int r=0; r<R; r++) {
				s += table[r][c];
			}
			set.add(s);
		}
		
		int cnt = 0;
		Queue<String> queue = new ArrayDeque<String>();
		for1:for (int i=0; i<R; i++) {
			for (String s : set) {
				queue.offer(s.substring(1));
			}
			set = new HashSet<>();
			while(!queue.isEmpty()) {
				set.add(queue.poll());
			}
			if (set.size() < C) {
				System.out.println(cnt);
				break for1;
			}
			cnt++;
		}
	}
	
}
