package week3.BOJ_1992_S1_쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static String[][] tf;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tf = new String[N][N];
		
		for(int i=0;i<N;i++) {
			String[] temp = br.readLine().split("");
			for(int j=0;j<N;j++) {
				tf[i][j] = temp[j];
				
			}
		}
		
		round(0, 0, N);
		System.out.println(sb);
		
	}

	//재귀를 시작할 x, y 좌표와, 탐색할 길이
	private static void round(int x, int y, int n) {
		if(same(x, y, n)) {
			sb.append(tf[x][y]);
		}
		else {
			int half = n/2;
			sb.append("(");
			//4분면으로 나누자
			round(x, y, half);
			round(x, y+half, half);
			round(x+half, y, half);
			round(x+half, y+half, half);
			sb.append(")");
		}
		 
	}

	private static boolean same(int x, int y, int n) {
		if(n == 1) { //1개짜리
			return true;
		}
		else {
			String first = tf[x][y];
			for(int i=x;i<x+n;i++) {
				for(int j=y;j<y+n;j++) {
					if(!tf[i][j].equals(first)) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}