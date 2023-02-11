package week2.BOJ_7576_G5_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int cnt = 0;
	private static int M;
	private static int N;
	private static int[][] box;
	private static Queue<int[]> ripen = new ArrayDeque<int[]>();  // 익은 토마토
	private static int rest=0;   // 익지 않은 토마토 수

	private static int[] dr = {0,1,0,-1};
	private static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		box = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++){
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) ripen.offer(new int[] {i,j});
				else if (box[i][j] == 0) rest += 1;
			}
		}

		ripener();

	}

	private static void ripener(){
		int ripen_cnt = ripen.size();

		if (ripen_cnt == 0){
			if (rest == 0){
				System.out.println(cnt);
			}
			else {
				System.out.println(-1);
			}
			return;
		}
		else{
			if (rest == 0){
				System.out.println(cnt);
				return;
			}
		}

		for (int i = 0; i < ripen_cnt; i++){
			int[] rc = ripen.poll();
			int r = rc[0];
			int c = rc[1];
			for (int j = 0; j < 4; j++) {
				int nr = r + dr[j];
				int nc = c + dc[j];

				if (0<=nr && nr<N & 0<=nc && nc<M && box[nr][nc] == 0){
					box[nr][nc] = 1;
					ripen.offer(new int[] {nr,nc});
					rest--;
				}
			}
		}
		cnt++;
		ripener();

	}
	
}
