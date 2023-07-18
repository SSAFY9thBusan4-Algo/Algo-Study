package src.week19.BOJ_8983_G4_사냥꾼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	private static int[] sadae;
	private static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// input
		String[] in = br.readLine().split(" ");
		int M = Integer.parseInt(in[0]);
		int N = Integer.parseInt(in[1]);
		L = Integer.parseInt(in[2]);
		
		// 1. 사대위치
		sadae = new int[M];
		in = br.readLine().split(" ");
		for(int i = 0 ; i < M ; i++) {
			sadae[i] = Integer.parseInt(in[i]);
		}
		
		// 오름차순으로 정렬
		Arrays.sort(sadae);
		
		// 2. 동물 위치
		int cnt = 0;	// 결과. 잡을 수 있는 동물 수
		for(int i = 0 ; i < N ; i++) {
			in = br.readLine().split(" ");
			if(canHunt(Integer.parseInt(in[0]), Integer.parseInt(in[1]))) ++cnt;
		}
		
		// output
		System.out.println(cnt);
	}

	private static boolean canHunt(int a, int b) {

		// 1. y축값이 이미 사정거리L을 넘어선 경우
		int l = L - b;
		if(l < 0) return false;
		
		// let l = 남아있는 사정거리
		// 2. x축값이 같은 사대가 있는 경우
		int idx = Arrays.binarySearch(sadae, a);
		if(idx >= 0) return true;
		
		// 3. 좌우 인덱스 값 확인해서 남은 사정거리안에 있는 경우
		idx = (idx * -1) -1;	//a보다 크면서 제일 작은 사대의 index값.

		if(idx != 0 && Math.abs(sadae[idx-1]-a) <= l) return true;
		if(idx < sadae.length && Math.abs(sadae[idx]-a) <= l) return true;
		
		return false;
	}
}
