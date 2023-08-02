package src.week25.BOJ_2607_S3_비슷한단어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2607 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String first = br.readLine();
		int[] count = alpabetCounter(first);
		
		int ans = 0;
		for(int i = 1; i < N ; i++) {
			String in = br.readLine();
			if(compare(count, alpabetCounter(in))) ++ans;
		}
		
		System.out.println(ans);
	}

	private static boolean compare(int[] origin, int[] comp) {
		
		int c1 = 0, c2 = 0;
		for(int i = 0 ; i < 26 ; i++) {
			if(origin[i] > comp[i]) c1 += origin[i] - comp[i];
			else c2 += comp[i] - origin[i];
		}
		
		if(c1+c2 == 0 || c1+c2 == 1) return true;
		else if(c1 == 1 && c2 == 1) return true;
		return false;
	}
	
	private static int[] alpabetCounter(String str) {

		int[] count = new int[26];
		
		for(char c : str.toCharArray()) {
			count[c-'A']++;
		}
		
		
		return count;
	}
}
