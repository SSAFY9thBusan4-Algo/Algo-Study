package week12.BOJ_1796_G4_신기한키보드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int result=10000000;
	private static char[] S;
	private static int[][] alpaindex = new int[26][2];

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		S = in.readLine().toCharArray();
		
		for (int alp='a'; alp<='z'; alp++) {
			int maxidx=-1, minidx=51;
			for (int i=0; i<S.length; i++) {
				if (S[i] == alp) {
					maxidx = Math.max(maxidx, i);
					minidx = Math.min(minidx, i);
				}
			}
			alpaindex[alp-'a'][0] = maxidx;
			alpaindex[alp-'a'][1] = minidx;
		}
		
		recursion('a', 0, S.length);
		
		System.out.println(result);
	}
	
	private static void recursion(char alpa, int curser, int moveCnt) {
		
		if (moveCnt > result) return;
		else if (alpa > 'z') {
			result = Math.min(result, moveCnt);
			return;
		}
		
		int maxidx = alpaindex[alpa - 'a'][0];
		int minidx = alpaindex[alpa - 'a'][1];
		
		// 문자가 하나만 있을 때
		if (maxidx == minidx) {
			recursion((char)(alpa+1), maxidx, moveCnt+Math.abs(curser-maxidx));
		}
		// 문자가 2개 이상
		else if (maxidx != -1) {
			recursion((char)(alpa+1), minidx, moveCnt+Math.abs(curser-maxidx)+Math.abs(minidx-maxidx));
			recursion((char)(alpa+1), maxidx, moveCnt+Math.abs(curser-minidx)+Math.abs(minidx-maxidx));
		}
		// 문자가 하나도 없을 때
		else {
			recursion((char)(alpa+1), curser, moveCnt);
		}
		
	}
	
}
