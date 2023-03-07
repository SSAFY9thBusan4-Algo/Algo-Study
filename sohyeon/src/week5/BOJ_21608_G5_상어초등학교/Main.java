package com.ssafy.live19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	private static int N, result;
	private static int[] student;
	private static Map<Integer, int[]> like;
	private static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		int sCnt = (int) Math.pow(N, 2);  // 학생수
		// 자리를 배치할 순서대로 학생 저장
		student = new int[sCnt];
		
		// 각 학생이 좋아하는 학생을 키로 하는 map에 list에 add
		like = new HashMap<>();
		
		for (int i = 0; i < sCnt; i++) {
			int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
			student[i] = input[0];
			int[] l = Arrays.copyOfRange(input, 1, 5);
			like.put(student[i], l);								
		}		
		
		// 자리에 학생 배치
		map = new int[N][N];
		
		for (int s : student) {
			
			// 자리를 돌아다니며 최적의 자리 찾기
			check(s);
			
		}
		
		// 각 자리 학생의 만족도 더하기
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				result += check2(i,j);
			}
		}
		
		System.out.println(result);
		
	}

	private static int[] dr = {-1,0,1,0};
	private static int[] dc = {0,1,0,-1};
	private static void check(int s) {
		int maxL = 0;  // 갈 수 있는 자리 중 좋아하는 학생이 가장 많이 인접한 수, 갱신될 때 row, col도 같이 갱신
		int maxE = 0;  // maxL이 갱신될때 인접한 빈칸수
		// 비어있는 칸 중 제일 위, 왼쪽에 있는칸
		int[] rc = findE();
		int row = rc[0];
		int col = rc[1];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 0) {  // 아직 학생이 배치되지 않은 자리라면 4방향 확인
					int cnt = 0; // 좋아하는 학생이 인접한 수
					int cntE = 0; // 인접한 빈자리 수
					for (int d = 0; d < 4; d++) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						if (0<=nr && nr<N && 0<=nc && nc<N) {
							if (map[nr][nc] == 0) {
								cntE++;
							}
							else {
								for (int l : like.get(s))	{
									if (l == map[nr][nc]) {
										cnt++;
										break;
									}
								}
							}							
						}
					}
					if (cnt > maxL) {
						maxL = cnt;
						maxE = cntE;
						row = r;
						col = c;
					}
					else if (cnt == maxL && maxE < cntE) {
						maxE = cntE;
						row = r;
						col = c;
					}
					
				}
			}
		}
		map[row][col] = s;		
		
	}
	
	private static int check2(int r, int c) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if (0<=nr && nr<N && 0<=nc && nc<N) {
				for (int l : like.get(map[r][c])){
					if (l == map[nr][nc]) {
						cnt++;
						break;
					}
				}							
			}
		}
		return (int) Math.pow(10, cnt-1);
	}
	private static int[] findE() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] == 0) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	
}
