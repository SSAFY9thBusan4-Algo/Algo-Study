package week9.BOJ_21318_S1_피아노체조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		int[] list = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		
		int[] D = new int[N+1];
		for (int i=1; i<N; i++) {
			if (list[i-1]>list[i]) D[i] = D[i-1]+1;
			else D[i] = D[i-1];
		}	
		D[N] = D[N-1];
				
		int Q = Integer.parseInt(in.readLine());
		for (int q=0; q<Q; q++) {
			String[] xy = in.readLine().split(" ");
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);					
						
			result.append(D[y-1]-D[x-1]+"\n");
		}
		
		System.out.println(result.toString());
		
	}
	
}
