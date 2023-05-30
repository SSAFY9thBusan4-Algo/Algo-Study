pakage src.week14.BOJ_16463_S3_13일의금요일

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static final int[] month = {0, 3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int count = 0;
		int cur13 = 0;	// 2019년 1월 13일은 일요일 이다.
		for(int year = 2019 ; year <= N ; year++) {
			
			for(int y = 1 ; y <= 12 ; y++) {
				// 현재 달의 13일이 무슨 금요일인지 확인.
				if((cur13%=7) == 5) count++;
				
				if(y == 2) {	//윤년일 때만 +1. 윤년 아니면 0
					if(isYun(year)) cur13 += 1;
				}
				else {
					cur13 += month[y];
				}
			}
		}
		
		System.out.println(count);
	}
	
	private static boolean isYun(int year) {
		if(year % 4 == 0) {
			if(year % 400 == 0) return true;
			if(year % 100 == 0) return false;
			return true;
		}
		return false;
	}
}
