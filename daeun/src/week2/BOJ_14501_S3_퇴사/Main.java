package week2.BOJ_14501_S3_퇴사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] split;
		int[] T = new int[N+1];
		int[] P = new int[N+1];
		int[] sum = new int[N+2];
		
		for(int i=1;i<N+1;i++) {  //1일부터 시작
			split = br.readLine().split(" ");
			T[i] = Integer.parseInt(split[0]); //끝나는 날은 i+T[i]
			P[i] = Integer.parseInt(split[1]);
		}
		
		for(int i=N;i>0;i--) {
			if(i+T[i] > N+1) { //기간이 퇴사 후 라면 이전 값 유지
				sum[i] = sum[i+1];
			}
			else { //이전값이 큰지 or 지금 i가 끝나는 날에 p 더한게 큰지
				sum[i] = Math.max(sum[i+1], P[i]+sum[i+T[i]]); 
			}
		}
		System.out.println(sum[1]);
		
	}
}