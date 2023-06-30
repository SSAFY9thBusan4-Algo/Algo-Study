import java.io.*;
import java.util.*;

public class BOJ_2615_S1_오목 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		//map 입력 받기
		int map[][] = new int[19][19]; //오목판 초기화
		StringTokenizer st;
		for(int i=0; i<19; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 오목의 경우
		// -> 가로 5개 (0)
		// -> 세로 5개 (1)
		// -> 오른쪽 대각선 아래 5개 (2)
		// -> 오른똑 대각선 위 5개 (3)
		// 방향 변수
		int dr[] = {0, 1, 1, -1};
		int dc[] = {1, 0, 1, 1};

		int ansVal=0, ansR=0, ansC=0;
		boolean find = false; //찾으면 반복문 종료

		// 오목 조건 확인 (반복문으로 해결)
		// 문제 조건에서 가장 왼쪽에 있는 바둑알부터 결정한다고 했으므로 열부터 반복문 시작
		for(int c=0; c<19; c++) {
			if(find) break;
			for(int r=0; r<19; r++) {
				if(find) break;
				if(map[r][c] == 1 || map[r][c] == 2) { //오목돌 발견
					int omokVal = map[r][c]; //오목이 흰돌인지 검정돌인지
					for(int d=0; d<4; d++) { //4개 방향 다 해보기
						find = true;
						int omokR = r; //오목 위치 열
						int omokC = c; //오목 위치 행
						
						//오목두기 전에 이전 방향에 같은 색깔 오목이 있는지 확인(육목방지)
						if(isRange(omokR-dr[d], omokC-dc[d])){							
							if(map[omokR-dr[d]][omokC-dc[d]] == omokVal) find = false;
						}
						
						//오목 돌 놔두기
						for(int o=0; o<4; o++) {
							int nr = omokR + dr[d];
							int nc = omokC + dc[d];
							
							//범위 안에 있고 다음 값이 같은 색깔의 오목이면 다음 방향에 오목 놔두기
							if(isRange(nr, nc) && map[nr][nc] == omokVal) {
								omokR = nr;
								omokC = nc;
								
								// 5개를 두고 다음 방향에 같은 오목값이 있는지 확인(육목방지)
								if(o==3 && isRange(omokR+dr[d], omokC+dc[d])) {
									if(map[omokR+dr[d]][omokC+dc[d]] == omokVal) find = false;
								}
							} 
							else find=false;
							
							if(!find) break; //어짜피 오목이 안되니까 반복문 종료
						}
						
						// 오목 답을 찾았으면
						if(find) {
							ansVal = omokVal;
							ansR = r+1;
							ansC = c+1;
							break;
						}
					}
				}
			}
		}

		if(ansVal==0) { // 승부가 안 났으면 0 출력
			System.out.println(0);
		}
		else {
			System.out.println(ansVal);
			System.out.println(ansR + " " + ansC);
		}
	}

	// 범위 체크
	private static boolean isRange(int r, int c) {
		if(0 <= r && r < 19 && 0 <= c && c < 19) return true;
		return false;
	}
}
