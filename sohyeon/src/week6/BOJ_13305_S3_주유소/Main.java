package week6.BOJ_13305_S3_주유소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		// 데이터 타입 주의!! 1이상 1,000,000,000 이하 이므로 long 타입 사용
		// 도로의 길이
		long[] roadL = Arrays.stream(in.readLine().split(" ")).mapToLong(s->Long.parseLong(s)).toArray(); 
		// 주유소의 리터당 가격
		long[] oilP = Arrays.stream(in.readLine().split(" ")).mapToLong(s->Long.parseLong(s)).toArray();
		
		boolean[] visited = new boolean[N];
		
		long result = 0;
		for (int i=0; i<N-1; i++) {
			if (!visited[i]) {
				visited[i] = true;
				long dist = roadL[i];
				int next = i+1;
				while (true) {
					if (oilP[next] >= oilP[i]) {
						visited[next] = true;
						dist += roadL[next];						
						next++;
						if (next == N-1) break;
					}
					else break;
				}
				result += dist * oilP[i];
			}
		}
		
		System.out.println(result);
	}
	
}
