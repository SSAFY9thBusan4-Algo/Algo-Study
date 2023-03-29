import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n, m, rainCnt;
	static boolean[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new boolean[n][m];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			int temp = Integer.parseInt(st.nextToken());
			
			for(int j=n-1; j>n-1-temp; j--) {
				arr[j][i] = true;
			}
		}
		
		// 맨 밑칸에서 부터 블록이 아닌 칸 탐색
		for(int i=n-1; i>=0; i--) {
			isRain(i, 0);
		}
		
		System.out.println(rainCnt);
	}

	private static void isRain(int x, int y) {
		
		int left, right;
		// 왼쪽 블록 찾기
		for(int i=y; i<m; i++) {
			if(arr[x][i]) { // 블록을 만났을 경우
				left = i;
				
				for(int j=i+1; j<m; j++) { // 오른쪽 블록 찾기
					if(arr[x][j]) {
						right = j;
						rainCnt += right - left - 1;
						isRain(x, j); // 오른쪽 블록부터 마지막 칸 까지 다시 탐색
						return;
					}
				}
			}
		}
	}
}
