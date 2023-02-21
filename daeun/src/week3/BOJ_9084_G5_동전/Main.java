package week3.BOJ_9084_G5_동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			int[] coin = new int[N];
			
			String[] split = br.readLine().split(" ");
			for(int i=0;i<N;i++) {
				coin[i] = Integer.parseInt(split[i]);
			}
			
			int M = Integer.parseInt(br.readLine());
			int[] money = new int[M+1];

			money[0] = 1;
			for(int i : coin) {
				for(int j=i;j<M+1;j++) { //동전 금액부터 시작
					money[j] += money[j-i];
				}
			}
	
			sb.append(money[M]).append("\n");
			
		}
		System.out.println(sb);
	}
}