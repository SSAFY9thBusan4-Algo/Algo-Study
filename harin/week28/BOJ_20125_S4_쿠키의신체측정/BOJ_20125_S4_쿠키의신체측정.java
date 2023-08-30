import java.io.*;
import java.util.*;

public class BOJ_20125_S4_쿠키의신체측정 {
	
	static int N; //크기 
	static char map[][];
	
	static int dr[] = {-1, 1, 0, 0}; // 상 하 좌 우
	static int dc[] = {0, 0, -1, 1}; 
	
	static class Pos {
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력 
		N = Integer.parseInt(in.readLine());
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String str = in.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		// 심장 찾기
		Pos heartPos = new Pos(0,0);
		boolean flag = false;
		for(int r=0; r<N; r++) {
			if(flag) break;
			for(int c=0; c<N; c++) {
				if(map[r][c] != '*') continue;
				int check = 0;
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(0 <= nr && nr < N && 0 <= nc && nc < N) {
						if(map[nr][nc] == '*') check++;
					}
				}
				if(check == 4) {
					heartPos = new Pos(r, c);
					flag = true;
					break;
				}
			}
		}
		
		System.out.println((heartPos.r + 1) + " " + (heartPos.c + 1));
		
		// 왼쪽 팔, 오른쪽 팔, 허리, 왼쪽 다리, 오른쪽 다리의 길이
		// 왼쪽 팔 길이 재기
		int leftArm = 0;
		for(int i = 0; i < heartPos.c; i++) {
			if(map[heartPos.r][i] == '*') leftArm++;
		}
		
		// 오른쪽 팔 길이 재기
		int rightArm = 0;
		for(int i = heartPos.c + 1; i < N; i++) {
			if(map[heartPos.r][i] == '*') rightArm++;
		}
		
		// 허리 길이 재기
		int waist = 0;
		for(int i = heartPos.r + 1; i < N; i++) {
			if(map[i][heartPos.c] == '*') waist++;
		}
		
		// 다리 길이 재기
		int leftLeg = 0;
		int rightLeg = 0;
		for(int i = N-1; i > heartPos.r; i--) {
			if(map[i][heartPos.c - 1] == '*') leftLeg++;
			if(map[i][heartPos.c + 1] == '*') rightLeg++;
			
		}
		
		System.out.print(leftArm + " " + rightArm + " " + waist + " " + leftLeg + " " + rightLeg);
		
	}
	
}
