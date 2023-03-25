package src.week7.BOJ_10775_G2_공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int[] dokingNum;
	
	private static int findDokingNum(int cur) {
		if(cur == 1) return 1;
		if(dokingNum[cur] == 0) return cur;
		else return dokingNum[cur] = findDokingNum(dokingNum[cur]);
	}
	
	private static boolean isDokingPossible(int cur) {
		int doking = findDokingNum(cur);
		if(doking == 1) {
			if(dokingNum[1] == 0)
				dokingNum[1] = -1;
			else 
				return false;
		}
		else {
			
			dokingNum[cur] = dokingNum[doking] = doking-1;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		dokingNum = new int[G+1];
		int airplaneCnt;
		for(airplaneCnt = 0 ; airplaneCnt < P ; airplaneCnt++) {
			int curdoking = Integer.parseInt(br.readLine());
			
			boolean possible = isDokingPossible(curdoking);
			if(!possible) break;
		}
		
		System.out.println(airplaneCnt);
	}
}
