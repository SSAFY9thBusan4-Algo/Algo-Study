package week1.BOJ_1476_S5_날짜계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		String[] str = br.readLine().split(" ");
		int E = Integer.parseInt(str[0]), S = Integer.parseInt(str[1]), M = Integer.parseInt(str[2]);
		
		int cur = 1;
		
		while (true) {
			if (((cur%15 == 0 && E == 15) || (cur % 15 == E)) && 
					((cur%28 == 0 && S == 28) || (cur % 28 == S)) && 
					((cur%19 == 0 && M == 19) || (cur % 19 == M))) {
				break;
			}
			cur++;
		}
		System.out.println(cur);
	}
}
