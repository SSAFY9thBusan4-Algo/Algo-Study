package src.week6.BOJ_13305_S3_주유소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] distance = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] price = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		//solve
		N--;
		long sumPrice = 0;
		int minPrice= price[0];
		for(int i = 0 ; i < N ; i++) {
			
			// 1. min값과 현재 주유소 가격 비교.
			if(minPrice > price[i]) minPrice = price[i];
			
			// 2. 현재 거리만큼 이동.
			sumPrice += (long)minPrice * distance[i];
		}
		
		System.out.println(sumPrice);
	}
}
