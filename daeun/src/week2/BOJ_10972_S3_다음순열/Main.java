package week2.BOJ_10972_S3_다음순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	static int N;
	static int[] num; //순열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] split = br.readLine().split(" ");
		num = new int[split.length];
		for(int i=0;i<split.length;i++) {
			num[i] = Integer.parseInt(split[i]);
		}
		if(permutation()) {
			for(int i=0; i<N; i++) {
				System.out.print(num[i]+" ");
			}
		}
		else {
			System.out.println("-1");
		}
	}
	private static boolean permutation() {
		int now = num.length-1; //뒤에서부터 오름차순 확인
		while(now>0 && num[now-1]>num[now]) { //now => 오름차순이 아닌 첫 index
			now--;
		}
		
		if(now==0) { // 뒤에서부터 쭉 오름차순
			return false;
		}
		
		int now2 = num.length -1;
		while(num[now2]<num[now-1]) { //(오름차순 바로 앞의 숫자와 바꿀) 그 다음으로 큰 숫자를 찾는 거
			now2--;
		}

		//now2는 오름차순 앞의 숫자와 바꿀 숫자
		//now-1은 오름차순 앞의 숫자
		int temp = num[now-1];
		num[now-1] = num[now2];
		num[now2] = temp;
		
		//그 뒤는 다시 정렬
		Arrays.sort(num, now, num.length); //나머지 정렬
		return true;
	}
}


