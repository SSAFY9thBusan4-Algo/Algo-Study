package week1.BOJ_3085_S3_사탕게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int result = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[][] board = new char[N][N];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<N; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N-1; j++) {
				// 행 검색
				char n = board[i][j];
				board[i][j] = board[i][j+1];
				board[i][j+1] = n;
				result = Math.max(result, check(board, N));
				n = board[i][j];
				board[i][j] = board[i][j+1];
				board[i][j+1] = n;
				
				// 열 검색
				n = board[j][i];
		        board[j][i] = board[j+1][i];
		        board[j+1][i] = n;
		        result = Math.max(result, check(board, N));
		        n = board[j][i];
		        board[j][i] = board[j+1][i];
		        board[j+1][i] = n;
			}
		}
		System.out.println(result);
	}
	
	
	public static int check(char[][] board, int N) {
		int result = 0;
		for (int i=0; i<N; i++) {
			int n = 1;
			int m = 1;
			for (int j=0; j<N-1; j++) {
				if (board[i][j] == board[i][j+1]) {
					n += 1;
					result = Math.max(result, n);
				}else {
					n = 1;
				}
				if (board[j][i] == board[j+1][i]) {
					m += 1;
					result = Math.max(result, m);
				}else {
					m = 1;
				}
			}
		}
		
		return result;
	}
}

