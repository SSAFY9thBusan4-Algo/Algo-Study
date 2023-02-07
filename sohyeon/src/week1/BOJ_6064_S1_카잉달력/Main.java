package week1.BOJ_6064_S1_카잉달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			String[] str = br.readLine().split(" ");
			int M = Integer.parseInt(str[0]), N = Integer.parseInt(str[1]), x = Integer.parseInt(str[2]), y = Integer.parseInt(str[3]);
			int ad = M;
			
			int r = x;
			
			while (true) {
				if (r > M*N){
					r = -1;
					break;
				}
				if ((r % M == x || (x % M == 0 && r % M == 0)) && (r % N == y || (y % N == 0 && r % N == 0))) {
					break;
				}
				r += ad;
			}
			System.out.println(r);
		}
	}
}
