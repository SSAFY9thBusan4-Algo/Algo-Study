package week12.BOJ_3020_G5_개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, H; //길이, 높이
	private static int[] up; // 종유석 
	private static int[] down; // 석순
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		up = new int[N/2];
		down = new int[N/2];
		
		// 홀수 입력은 석순, 짝수 입력은 종유석
		for (int i=0; i<N/2; i++) {
			
			up[i] = Integer.parseInt(in.readLine());
			down[i] = Integer.parseInt(in.readLine());
			
		}
		
		Arrays.sort(up);
		Arrays.sort(down);
		
		int min = N;
		int cnt = 0;
		for (int i=1; i<=H; i++) {
			int count = binarySearch(0,N/2,i,down)+binarySearch(0,N/2,H-i+1,up);
			
			if (count == min) cnt++;
			else if (count < min) {
				cnt = 1;
				min = count;
			}
		}
		
		System.out.println(min+" "+cnt);
		
	}

	private static int binarySearch(int left, int right, int h, int[] arr) {
		
		while(left<right) {
			int mid = (left+right)/2;
			
			if (arr[mid]>=h) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		
		return arr.length-left;
	}
	
}
