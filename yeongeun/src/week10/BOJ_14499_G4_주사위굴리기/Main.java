package src.week10.BOJ_14499_G4_주사위굴리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static int N, M, curx, cury;
	private static int[][] map;
	private static int[] realNumber;
	private static int[] indexNumber;
	private static final int[][] delta = {{},{0,1},{0,-1},{-1,0},{1,0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// input
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		curx = Integer.parseInt(input[2]);
		cury = Integer.parseInt(input[3]);
		
		int k = Integer.parseInt(input[4]);
		
		map = new int[N][];
		for(int i = 0 ; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int[] cmd = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		// solve
		
		// init 주사위 사분면 초기화. 주사위 처음 숫자는 0.
		realNumber = new int[6];
		indexNumber = new int[6];
		for(int i = 1 ; i < 4 ; i++) {
			indexNumber[i] = i;
		}
		indexNumber[4] = 5;
		indexNumber[5] = 4;
		
		
		// run
		for(int i = 0 ; i < k ; i++) {
			int r = run(cmd[i]);
			
			if(r != -1) {
				sb.append(r).append('\n');
			}
		}
		
		System.out.println(sb);
	}
	
	
	private static int run(int dir) {

		// 1. 지도에서 현재위치 이동. 범위밖이면 return -1;
		int dx = curx + delta[dir][0];
		int dy = cury + delta[dir][1];
		if(dx < 0 || dx >= N || dy < 0 || dy >= M) return -1;
		curx = dx;
		cury = dy;
		
		// 2. 주사위 굴리기.
		// 굴린 주사위의 사분면 바꿔주기
		turnIdxNum(dir);
		
		// 3. 숫자 복사
		if(map[curx][cury] == 0) {
			map[curx][cury] = realNumber[indexNumber[0]];
		}
		else {
			realNumber[indexNumber[0]] = map[curx][cury];
			map[curx][cury] = 0;
		}
		
		// 4. 윗면 반환
		return realNumber[indexNumber[2]];
	}
	
	private static void turnIdxNum(int dir) {
		int t;
		
		switch(dir) {
		case 1:		// 동
			t = indexNumber[0];
			indexNumber[0] = indexNumber[4];
			indexNumber[4] = indexNumber[2];
			indexNumber[2] = indexNumber[5];
			indexNumber[5] = t;
			
			break;
		case 2:		// 서 
			t = indexNumber[0];
			indexNumber[0] = indexNumber[5];
			indexNumber[5] = indexNumber[2];
			indexNumber[2] = indexNumber[4];
			indexNumber[4] = t;
			
			break;
		case 3 :	// 북
			t = indexNumber[3];
			for(int i = 3 ; i > 0; i--) {
				indexNumber[i] = indexNumber[i-1];
			}
			indexNumber[0] = t;
			
			break;
		case 4 : 	// 남
			t = indexNumber[0];
			for(int i = 0 ; i < 3; i++) {
				indexNumber[i] = indexNumber[i+1];
			}
			indexNumber[3] = t;
			
			break;
		}
		
	}
}
