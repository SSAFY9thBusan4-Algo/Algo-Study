import java.io.*;
import java.util.*;

public class BOJ_18429_S3_근손실 {
	
	static int N, K;
	static int[] weights;
	
	static boolean[] isSelected;
	static int[] numbers;
	
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 운동 키트의 개수 
		K = Integer.parseInt(st.nextToken()); // 매일 감소하는 운동 중량 
		
		weights = new int[N+1]; // 각 운동키트의 중량 저장
		
		st = new StringTokenizer(in.readLine()); // 중량 저장 입력받기 
		for(int i=1; i<=N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		// 0. 순열 구현 위한 변수 초기화
		isSelected = new boolean[N+1];
		numbers = new int[N];
		
		// 1. 운동 키트 사용 순서 정하기 
		permutation(0);
		
		System.out.println(ans);
		
		
	}
	
	static public void permutation(int cnt) {
		if(cnt == N) {
			int weight = 500;
			boolean flag = true;
			for(int i=0; i<N; i++) {
				// 2. 운동 키트 만큼 중량 증가하기	
				weight += weights[numbers[i]];
				// 3. K만큼 근손실
				weight -= K;
				if(weight < 500) {
					flag = false;
					break;
				}
			}
			if(flag) ans++;
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			numbers[cnt] = i;
			permutation(cnt+1);
			isSelected[i] = false;
		}
		
	}
	

}
