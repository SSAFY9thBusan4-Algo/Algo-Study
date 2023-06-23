package src.week18.BOJ_2666_벽장문의이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2666_벽장문의이동 {
	
	private static int N, L;
	private static int[] open;
	private static int[][] save;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] in = br.readLine().split(" ");
		
		int s1 = Integer.parseInt(in[0]);
		int s2 = Integer.parseInt(in[1]);
		
		L = Integer.parseInt(br.readLine());
		
		open = new int[L];
		for(int i = 0 ; i < L ; i++) open[i] = Integer.parseInt(br.readLine());
		
		// L번째에 해당문 이외에 나머지 n 문을 열면 얻는 최소이동횟수.
		save = new int[L+1][N+1];
		
		int r = next(s1,s2,0);
		System.out.println(r);
		
	}

	private static int next(int s1, int s2, int idx) {
		if(idx == L) {
			return 0;
		}
		// 다음 문 열기
		int door = open[idx];
		
		int r1 = save[idx+1][s2];
		if(r1== 0) r1 = next(door, s2, idx+1);
		int c1 = Math.abs(s1 - door);
		
		int r2 = save[idx+1][s1];
		if(r2 == 0) r2 = next(door, s1, idx+1);
		int c2 = Math.abs(s2 - door);
		
		// 최소 이동 횟수
		int r = r1+c1 < r2+c2 ? r1+c1 : r2+c2;
		save[idx][s2] = r;
		return r;
	}
}
