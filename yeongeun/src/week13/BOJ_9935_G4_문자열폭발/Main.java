package week13.BOJ_9935_G4_문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str = br.readLine().toCharArray();
		char[] bom = br.readLine().toCharArray();
		int[] idx = setLookUpArr(bom);
		
		Deque<Integer> result = start(str, bom, idx);

		if(result.isEmpty()) System.out.println("FRULA");
		else {
			StringBuilder sb = new StringBuilder();
			while(!result.isEmpty()) {
				sb.append(str[result.pollFirst()]);
			}
			System.out.println(sb);
		}
	}
	
	private static Deque<Integer> start(char[] str, char[] bom, int[] idx) {
		int[] backidx = new int[str.length];
		Deque<Integer> remain = new ArrayDeque<>();
		
		int cur = 0;
		for(int i = 0 ; i < str.length ; ++i) {
			remain.offerLast(i);
			if(str[i] == bom[cur]) {
				backidx[i] = ++cur;
				
				if(cur == bom.length) {	//폭발
					for(int cnt = 0; cnt < bom.length; cnt++) {
						remain.pollLast();
					}
					// 다시 포인터 설정
					if(remain.isEmpty()) cur = 0;
					else cur = backidx[remain.peekLast()];
				}
			}
			else if(cur != 0 && backidx[i-1] != 0) {
				cur = idx[backidx[i-1]-1];	// 포인터 설정
				if(str[i] == bom[cur]) cur++;
				else if(str[i] == bom[0]) cur = 1;
				else cur = 0;
				backidx[i] = cur;
			}
		}
		return remain;
	}

	private static int[] setLookUpArr(char[] arr) {
		int[] idx = new int[arr.length];
		
		int pre = 0;
		idx[0] = 1;
		for(int i = 1 ; i < arr.length; ++i) {
			if(arr[i] == arr[pre]) {
				idx[i] = ++pre;
			}
			else pre = 0;
		}
		
		return idx;
	}
}
