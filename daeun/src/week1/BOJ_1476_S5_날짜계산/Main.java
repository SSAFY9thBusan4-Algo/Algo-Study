package week1.BOJ_1476_S5_날짜계산;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = br.readLine().split(" ");
		int E = Integer.parseInt(split[0]);
		int S = Integer.parseInt(split[1]);
		int M = Integer.parseInt(split[2]);
		
		int e=1, s=1, m=1, year = 1;
		while (true) {
			if(E==e && S==s && M==m) {
				break;
			}
			e++;
			s++;
			m++;
			year++;
			if(e==16) e=1;
			if(s==29) s=1;
			if(m==20) m=1;
		}
		System.out.println(year);
	}
}