package src.week19.BOJ_14938_G4_서강그라운드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static int[] items;
	private static int[][] matrix;
	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		int R = Integer.parseInt(in[2]);
		
		items = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1 ; i <= N ; i++) items[i] = Integer.parseInt(st.nextToken());
		
		matrix = new int[N+1][N+1];
		// init
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1; j <= N ; j++)
				matrix[i][j] = 256;
		}
		// input
		for(int i = 0 ; i < R ; i++) {
			in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]);
			int b = Integer.parseInt(in[1]);
			int l = Integer.parseInt(in[2]);
			
			matrix[a][b] = matrix[b][a] = l;
		}
		
		//solve
		getMinDistance();
		System.out.println(getMaxItemCnt());
	}
	
	private static int getMaxItemCnt() {
		int max = 0;
		
		for(int i = 1 ; i <= N ; i++) {	// 출발지가 i일때 M보다 작은 곳에 갈 수 있다.
			int sum = 0;
			for(int j = 1; j <= N ; j++) {
				if(i == j || matrix[i][j] <= M) sum += items[j];
			}
			if(sum > max) max = sum;
		}
		
		return max;
	}

	private static void getMinDistance() {
		// a에서 b까지 가는 최단 경로
		for(int i = 1; i <= N ; i++) {	// i를 거쳐간다.
			
			for(int a = 1 ; a <= N ; a++) {
				if(i == a) continue;
				for(int b = 1; b <= N ; b++) {
					if(i == b || a == b) continue;
					
					int t = matrix[a][i] + matrix[i][b];
					if(t < matrix[a][b]) matrix[a][b] = t;
				}
			}
		}
		
	}
}
