package week4.BOJ_2096_G5_내려가기;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		int N = Integer.parseInt(br.readLine());
		int[] line = new int[3]; //입력받는 한 줄
		int[] maxA = new int[3]; //최댓값 구할 배열
		int[] minA = new int[3]; //최솟값 구할 배열
		int[] now = new int[3];
		
		String[] split = new String[3];
		for(int i=0;i<N;i++) {
			split = br.readLine().split(" ");
			for(int j=0;j<3;j++) {
				line[j] = Integer.parseInt(split[j]); 
			}
			
			if(i==0) { //첫 줄
				maxA[0] = line[0];
				maxA[1] = line[1];
				maxA[2] = line[2];
				
				minA[0] = line[0];
				minA[1] = line[1];
				minA[2] = line[2];
			}
			else { 
				//지금 위치에서 더할 수 있는 두 자리의 값 중 큰 값으로... 순서 반대로 생각했다가 틀렸다.
				//maxA를 계속 써야하니까 다른 배열 필요
				now[0] = line[0] + Math.max(maxA[0], maxA[1]);
				now[1] = line[1] + Math.max(maxA[0], Math.max(maxA[1], maxA[2]));
				now[2] = line[2] + Math.max(maxA[1], maxA[2]);
				
				maxA[0] = now[0];
				maxA[1] = now[1];
				maxA[2] = now[2];
				
				now[0] = line[0] + Math.min(minA[0], minA[1]);
				now[1] = line[1] + Math.min(minA[0], Math.min(minA[1], minA[2]));
				now[2] = line[2] + Math.min(minA[1], minA[2]);

				minA[0] = now[0];
				minA[1] = now[1];
				minA[2] = now[2];
				
			}
		}
		
		max = Math.max(maxA[0], Math.max(maxA[1], maxA[2]));
		min = Math.min(minA[0], Math.min(minA[1], minA[2]));
		
		System.out.println(max+" "+min);
		
	}	
}