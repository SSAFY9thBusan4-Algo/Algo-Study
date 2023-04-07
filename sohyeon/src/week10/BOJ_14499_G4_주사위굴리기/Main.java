package week10.BOJ_14499_G4_주사위굴리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	// 동 서 북 남
	private static int[] dr = {0,0,0,-1,1};
	private static int[] dc = {0,1,-1,0,0};
	
	private static int[] dice = new int[6];
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = in.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int x = Integer.parseInt(input[2]);
		int y = Integer.parseInt(input[3]);
		int K = Integer.parseInt(input[4]);
		
		int[][] map = new int[N][M];
		for (int r=0; r<N; r++) {
			map[r] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		}
		
		int[] kList = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		for (int k : kList) {
			
			// 주사위 이동시키기
			x += dr[k];
			y += dc[k];
			
			if (x<0 || x>=N || y<0 || y>=M) {
				x -= dr[k];
				y -= dc[k];
				continue;
			}
			roll(k);
			
			// 이동한 지도 칸의 정수와 주사위 바닥면의 수 처리
			if (map[x][y] == 0) {
				map[x][y] = dice[3];
			}else {
				dice[3] = map[x][y];
				map[x][y] = 0;
			}
			
			// 주사위 윗면 수 저장
			sb.append(dice[1]+"\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	
	public static void roll(int d) {
		int[] list = null;  // 회전할 단면들
		switch (d) {
		case 1:
			list = new int[] {3,5,1,4};
			break;
		case 2:
			list = new int[] {4,1,5,3};
			break;
		case 3:
			list = new int[] {0,1,2,3};
			break;
		case 4:
			list = new int[] {3,2,1,0};
			break;
		}
		
		int sub = dice[list[0]];
		dice[list[0]] = dice[list[1]];
		dice[list[1]] = dice[list[2]];
		dice[list[2]] = dice[list[3]];
		dice[list[3]] = sub;
	}
	
}