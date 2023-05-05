package week13.BOJ_13164_G5_행복유치원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, K;  // 원생 수, 조의 개수
	private static int[] heights;  // 키 정보
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		heights = new int[N];
		
		heights = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		
		// 학생들 키차이 구하기
		int[] list = new int[N];
		for (int i=0; i<N-1; i++) {
			list[i] = heights[i+1]-heights[i];
		}
		Arrays.sort(list);
//		System.out.println(Arrays.toString(list));
		
		int result = 0;
		for (int i=1; i <= N-K; i++) result+=list[i];
		
		System.out.println(result);
	}


}
