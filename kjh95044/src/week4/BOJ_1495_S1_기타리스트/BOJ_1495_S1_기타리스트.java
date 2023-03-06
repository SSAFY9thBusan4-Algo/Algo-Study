package week4.BOJ_1495_S1_기타리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1495_S1_기타리스트 {
	
	static StringBuilder sb = new StringBuilder();
	static int N,S,M;
	static int[] volumes;
	static boolean[] result;

	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		volumes = new int[N];
		result = new boolean [M+1];
		result[S] = true;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			volumes[i] = Integer.parseInt(st.nextToken());
			
		}
		
		for(int i=0; i<volumes.length; i++) {
			
		}
	}
}
