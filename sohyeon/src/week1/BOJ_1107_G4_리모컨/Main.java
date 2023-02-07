package week1.BOJ_1107_G4_리모컨;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException {
		
		Scanner scan = new Scanner(System.in);
        
        int N = scan.nextInt();
        int M = scan.nextInt();
		
        boolean[] btn = new boolean[10];
		for (int i = 0; i < M; i++) {
			int n = scan.nextInt();
			btn[n] = true;
		}
        
		if (N == 100) {
			System.out.println(0);
		}
		else {	
			
			int min_cnt = Math.abs(100-N);
			
//			int max_channel = 0;
//			for (int i = 0; i < String.valueOf(N).length(); i++) {
//				max_channel = max_channel*10 + 9;
//			}			
//			System.out.println(max_channel);
//			
//			for (int i = 0; i < max_channel+1; i++) {
//  왜 안되는지 모르겠음 (90%에서 틀림)
			
			for (int i = 0; i < 1000000; i++) {
				String num = String.valueOf(i);
				boolean flag = true;
				for (int j = 0; j < num.length(); j++) {
					if (btn[num.charAt(j) - '0']) {					
						flag = false;
						break;
					}					
				}
				if (flag) {
					min_cnt = Math.min(min_cnt, Math.abs(N-i)+num.length());
				}
			}
			
			System.out.println(min_cnt);
		}		
		
	}
}
