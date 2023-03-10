package week6.BOJ_13305_S3_주유소;

import java.util.*;
import java.io.*;

public class BOJ_13305_S3_주유소 {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int[] roadLength;
	static int[] oilPrice;

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		roadLength = new int[N-1];
		oilPrice = new int [N];
		
		
		st= new StringTokenizer(br.readLine());
		for (int i = 0; i < N-1; i++) {
			roadLength[i] = Integer.parseInt(st.nextToken());
		}
		
		st= new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			oilPrice[i] = Integer.parseInt(st.nextToken());
		}
		
		// 뒤에서부터 확인
		// 주유소 더 싼 도시가 나오면 거기서 기름 넣는 방식으로 갱신
		// 마지막 도시에서는 기름 넣을 필요가 없기 때문에 마지막은 확인 안함
		
		int result = 0;
		int minPrice = Integer.MAX_VALUE;
		int roadSum = 0;
		
		for(int i=N-2; i>=0; i--) {
			roadSum+= roadLength[i];
			if(oilPrice[i]<minPrice) {
				minPrice = oilPrice[i];
				result = minPrice * roadSum;
			}
			else {
				result += oilPrice[i] * roadLength[i];
			}

		}
		
		System.out.println(result);
	}
}