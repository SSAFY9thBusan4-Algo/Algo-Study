package week23.BOJ_15565_S1_귀여운라이언;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dolls = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		
		int result = Integer.MAX_VALUE;
		int p1=-1, p2=-1;
		int cnt = 0;
		for (int i=0; i<N; i++) {
			if (dolls[i]==1) cnt++;
			if (cnt==1) p1 = i;
			if (cnt==K) p2 = i;
		}
		if (p2==-1) {
			System.out.println(-1);
			return;
		}
		while (p1<N && p2<N) {
			
			result = Math.min(result, p2++ - p1++ +1);
			while(p1<N && dolls[p1]!=1) p1++;
			while(p2<N && dolls[p2]!=1) p2++;
			
		}
		
		System.out.println(result);
		
	}
	
}
