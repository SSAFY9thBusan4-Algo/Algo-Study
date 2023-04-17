package week10.BOJ_14499_G4_주사위굴리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//1시간 30분?
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] dx = {0,0,-1,1};
		int[] dy = {1,-1,0,0};
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int x = Integer.parseInt(split[2]);
		int y = Integer.parseInt(split[3]);
		int K = Integer.parseInt(split[4]);
		int[][] map = new int[N][M];
		for(int i=0;i<N;i++) {
			split = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		split = br.readLine().split(" ");
		int[] dice = {0, 0, 0, 0, 0, 0}; //위, 아래, 동, 서, 남, 북
		int temp;
		for(int i=0;i<K;i++) {
			int command = Integer.parseInt(split[i])-1;
			//주사위를 굴리니까 왼쪽, 오른쪽
			int X = x + dx[command];
			int Y = y + dy[command];
			if(X<0 || X>=N || Y<0 || Y>=M) continue;
			
			switch(command) {
				case 0: //동
					//위 0 -> 동2 -> 아래1 -> 서3 -> 위0
 					temp = dice[0];
					dice[0] = dice[3];
					dice[3] = dice[1];
					dice[1] = dice[2];
					dice[2] = temp;
					break;
				case 1: //서
					//위 0 -> 서3 -> 아래1 -> 동2 -> 위0
					temp = dice[0];
					dice[0] = dice[2];
					dice[2] = dice[1];
					dice[1] = dice[3];
					dice[3] = temp;					
					break;
				case 2: //북
					//위0 -> 북5 -> 아래1 -> 남4 -> 위0
					temp = dice[0];
					dice[0] = dice[4];
					dice[4] = dice[1];
					dice[1] = dice[5];
					dice[5] = temp;
					break;
				case 3: //남
					//위0 -> 남4 -> 아래1 -> 북5 -> 위0
					temp = dice[0];
					dice[0] = dice[5];
					dice[5] = dice[1];
					dice[1] = dice[4];
					dice[4] = temp;
					break;
				}
			
			int floor = map[X][Y]; //바닥면에 있는 글자
			if(floor == 0) { //바닥이 0이면
				map[X][Y] = dice[1];
				//여기서 복사되면 없어질 거라고 생각해서 오래 걸렸다...
			}
			else { //바닥에 글자가 있으면 
				dice[1] = floor;
				map[X][Y] = 0;
			}
			x = X;
			y = Y;
			sb.append(dice[0]).append("\n");
		}
		System.out.println(sb);
	}

}