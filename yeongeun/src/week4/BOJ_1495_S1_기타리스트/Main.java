package src.week4.BOJ_1495_S1_기타리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);	//1 ≤ N ≤ 50
		int S = Integer.parseInt(input[1]);	//0 ≤ S ≤ M
		int M = Integer.parseInt(input[2]);	//1 ≤ M ≤ 1,000
		
		int[] volumelist = new int[N];
		input = br.readLine().split(" ");
		for(int i = 0 ; i < N ; i++) volumelist[i] = Integer.parseInt(input[i]);
		
		// ========================================================
		// solve
		
		Set<Integer> pre = new HashSet<>();		// 이전 볼륨들을 저장.
		pre.add(S);		// 시작 볼륨
		
		for(int i = 0 ; i < N ; i++) {
			Set<Integer> cur = new HashSet<>();		//현재 만들어 질 수 있는 볼륨들을 저장.
			
			// 이전 값을 꺼내서 +,- 값들을 현재 set에 저장.
			for(int vol : pre) {
				if(vol+volumelist[i] <= M) cur.add(vol+volumelist[i]);
				if(vol-volumelist[i] >= 0) cur.add(vol-volumelist[i]);
			}
			
			pre = cur;
			if(cur.size() == 0) break;	// 연주할 수 없는 경우.
		}
		
		int result = -1;
		for(int vol : pre) {
			if(result < vol) result = vol;
		}
		System.out.println(result);
		
	}
	
	
}