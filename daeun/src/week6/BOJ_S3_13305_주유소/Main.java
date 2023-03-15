package week6.BOJ_S3_13305_주유소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//해당 숫자가 몇번까지 숫자중에 제일 큰 가격인지 확인할까? 했지만 숫자가 너무 크다!
		//일단 지나가면서 제일 작은 가격이 나오면 그걸 저장하고 거리 길이에 곱해가는 걸로
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); //도시 개수
		long[] distance = new long[N-1];
		long[] price = new long[N-1]; //어차피 마지막 도시에서는 주유를 안 할거니까
		
		String[] split = br.readLine().split(" ");
		for(int i=0;i<N-1;i++) {
			distance[i] = Integer.parseInt(split[i]);
		}
		split = br.readLine().split(" ");
		for(int i=0;i<N-1;i++) {
			price[i] = Integer.parseInt(split[i]);
		}
		
		
		long cost = 0;
		long min = price[0];
		for(int i=0;i<N-1;i++) {
			min = Math.min(min, price[i]); //더 작은 가격을 찾기
			cost += min * distance[i];
		}
		
		System.out.println(cost);
		
	}
}