package week14.BOJ_16463_S3_13일의금요일;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int result = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int y = 2019;
		int m = 1;
		int d = 1;
		int wd = 2;
		
		while (y <= N) {
			int[] month = null;
			if (y%400==0 || (y%100!=0 && y%4==0)) {
				month = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			}else {
				month = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			}
			
			while (m <= 12) {
				while (d <= month[m]) {
					if (d==13 && wd==5) result++;
					if (wd == 7) wd=0;
					d++;
					wd++;
				}
				d=1;
				m++;
			}
			m=1;
			y++;	
		}
		
		System.out.println(result);
	}
}
