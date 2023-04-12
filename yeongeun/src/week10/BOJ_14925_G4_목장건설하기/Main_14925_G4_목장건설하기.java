package src.week10.BOJ_14925_G4_목장건설하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14925_G4_목장건설하기 {

	 public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
    // 가로 길이 구하기
		int[][] map = new int[N][M];
		for(int i = 0 ; i < N; i++) {
			input = br.readLine().split(" ");
			if(input[0].charAt(0) == '0') map[i][0] = 1;
			else map[i][0] = 0;
			
			for(int j = 1 ; j < M; j++) {
				if(input[j].charAt(0) == '0') {
					map[i][j] = map[i][j-1] + 1; 
				}
				else {
					map[i][j] = 0;
				}
			}
		}
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}

		
		int max = 0;
		for(int i = 0 ; i < N; i++) {
			for(int j = M-1 ; j >= 0; j--) {
				if(map[i][j] > max) {	// 현재 가로길이가 max보다 클때
					if(map[i][j] == 1) {
						max = 1;
						continue;
					}
					
					// 세로길이 구하기
					int cnt = 1;
					int minwidth = map[i][j];
					while(i+cnt < N && cnt <= map[i][j]) {
						if(map[i+cnt][j] < minwidth) {
							if(map[i+cnt][j] <= max) {
								minwidth = cnt < minwidth ? cnt : minwidth;
								break;
							}
							minwidth = map[i+cnt][j];
						}
						cnt++;
					}
					// 가로길이, 세로길이 중에 더 작은 값
					minwidth = cnt < minwidth ? cnt : minwidth;
					if(minwidth > max) max = minwidth;
				}
			}
		}
		System.out.println(max);
		
	}
	
}
