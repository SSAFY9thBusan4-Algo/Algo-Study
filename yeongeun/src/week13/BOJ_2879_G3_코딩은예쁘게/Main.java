package week13.BOJ_2879_G3_코딩은예쁘게;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] n1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] n2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int[] arr = new int[N];
		for(int i = 0 ; i < N ; i++) arr[i] = n2[i] - n1[i];
		
		int result = Math.abs(arr[0]);
		
		for(int i = 1 ; i < N; i++) {
			if(arr[i-1] >= 0 && arr[i] < 0) {
				result += Math.abs(arr[i]);
			}
			else if(arr[i-1] <= 0 && arr[i] > 0) {
				result += arr[i];
			}
			else if(arr[i] > 0 && arr[i-1] < arr[i]){
				result += arr[i] - arr[i-1];
			}
			else if(arr[i] < 0 && arr[i-1] > arr[i]){
				result += arr[i-1] - arr[i];
			}
		}
		
		
		System.out.println(result);
	}
}
