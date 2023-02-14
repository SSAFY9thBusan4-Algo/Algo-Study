package week2.BOJ_10972_S3_다음순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

public static void main(String[] args) throws NumberFormatException, IOException {
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		String[] str = in.readLine().split(" ");
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(str[i]);
		}
		
		for (int i = N-1; i>=0; i--) {
			if (i == 0) {
				System.out.println(-1);
				break;
			}
			if (numbers[i]<numbers[i-1]) continue;
			else {
				int[] nums = new int[N-i+1];
				int idx = 0;
				for (int j = i-1; j < N; j++) {
					nums[idx++] = numbers[j];
				}
				Arrays.sort(nums);
				idx = i;
				boolean flag = true;
				for (int n : nums) {
					if (n>numbers[i-1] && flag) {
						numbers[i-1] = n;
						flag = false;
					}
					else {
						numbers[idx++] = n;
					}
				}
				for (int n : numbers) {
					sb.append(n + " ");
				}
				System.out.println(sb.toString());				
				break;				
			}
		}					
	}
	
}
