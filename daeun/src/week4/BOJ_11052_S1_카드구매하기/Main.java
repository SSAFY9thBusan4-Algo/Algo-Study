package week4.BOJ_11052_S1_카드구매하기;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {

	private static int N, price[], sum[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));		
		N = Integer.parseInt(br.readLine());
		price = new int[N+1];
		sum = new int[N+1];
		String[] split = br.readLine().split(" ");
		
		//인덱스를 1부터 시작하는걸로 바꾸니까 잘 되네..?
		for(int i=1;i<N+1;i++) {
			price[i] = Integer.parseInt(split[i-1]);
		}

		for(int i=1;i<N+1;i++) {
			for(int j=1;j<=i;j++) {
				sum[i] = Math.max(sum[i], sum[i-j]+price[j]);
			}
		}
		
		System.out.println(sum[N]);
		
	}

}