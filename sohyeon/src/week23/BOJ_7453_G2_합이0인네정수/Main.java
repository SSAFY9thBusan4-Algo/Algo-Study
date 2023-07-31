package week23.BOJ_7453_G2_합이0인네정수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		StringTokenizer st;
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] preSum = new int[2][n*n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				preSum[0][i*n+j] = A[i]+B[j];
				preSum[1][i*n+j] = C[i]+D[j];
			}
		}
		
		Arrays.sort(preSum[0]);
		Arrays.sort(preSum[1]);
		
		
		long result=0;
		int p1=0;
		int p2=n*n-1;
		int end = n * n;
		while(p1<end && p2>=0) {
			
			int sum = preSum[0][p1]+preSum[1][p2];			
			int cnt1=1;
			int cnt2=1;
			if (sum == 0) {			
				while (p1<=end-2 && preSum[0][p1] == preSum[0][p1+1]) {
					cnt1++;
					p1++;					
				}
				while (0<p2 && preSum[1][p2] == preSum[1][p2-1]) {
					cnt2++;
					p2--;
				}
				result += (long) cnt1*cnt2;
			}
			if (sum<0) {
				p1++;
			}
			else{
				p2--;
			}
		}
		System.out.println(result);
	}

	
		
}
