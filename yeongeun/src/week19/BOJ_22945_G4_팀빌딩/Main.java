package src.week19.BOJ_22945_G4_팀빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] nums = new int[N];
		for(int i = 0 ; i < N ; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		//solve
		
		int left = 0, right = N-1, L = N-2;
		int maxVal = Math.min(nums[left], nums[right]) * L;
		
		while(L > 1) {
			
			if(nums[left] < nums[right]) left++;
			else right--;
			
			int val = Math.min(nums[left], nums[right]) * --L;
			if(val > maxVal) maxVal = val;
		}
		
		
		System.out.println(maxVal);
	}
}
