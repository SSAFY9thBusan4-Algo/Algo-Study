package src.week8.BOJ_14719_G5_빗물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		int h = Integer.parseInt(in[0]);
		int w = Integer.parseInt(in[1]);

		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		// solve
		int result = 0;
		
		int pre = arr[0];
		for(int i = 1 ; i < w ; i++) {
			if(arr[i] > pre) {	// 자기보다 왼쪽에 더 큰 애 없음
				pre = arr[i];
			}
			else {	// 오른쪽 큰 애 구하기
				int right = arr[i];
				for(int j = i+1 ; j < w ; j++) {
					if(arr[j] > right) {
						right = arr[j];
					}
					if(right >= pre) {	// 오른쪽 왼쪽 중에 작은 수로
						right = pre;
						break;
					}
				}
				
				//쌓이는 빗물 칸 수
				result += right - arr[i];
			}
		}
		
		
		System.out.println(result);

	}
}
