package src.week7.BOJ_2174_G5_로봇시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int A, B;
	private static final String dir = "NWSE";
	private static int[][] delta = {{1,0},{0,-1},{-1,0},{0,1}};
	private static int[][] map;
	private static int[][] robot;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		A = Integer.parseInt(input[0]);
		B = Integer.parseInt(input[1]);
		
		input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		
		map = new int[B+1][A+1];
		robot = new int[N+1][3];
		
		int x,y,r;
		for(int i = 1 ; i <= N; i++) {
			input = br.readLine().split(" ");
			x = Integer.parseInt(input[0]);
			y = Integer.parseInt(input[1]);
			r = dir.indexOf(input[2].charAt(0));
			
			map[y][x] = i;	//맵에는 현재 로봇의 번호 저장
			robot[i][0] = r;	//로봇배열에는 방향, 좌표 저장
			robot[i][1] = x;
			robot[i][2] = y;
		}
		
		// solve
		boolean ok = true;
		for(int i = 0 ; i < M ; i++) {
			input = br.readLine().split(" ");
			
			int n = Integer.parseInt(input[0]);
			char order = input[1].charAt(0);
			int rcnt = Integer.parseInt(input[2]);
			
			int result = excute(n,order,rcnt);
			if(result == -1) {
				System.out.println("Robot "+n+" crashes into the wall");
				ok = false;
				break;
			}
			else if(result != 0) {
				System.out.println("Robot "+n+" crashes into robot "+result);
				ok = false;
				break;
			}
		}
		
		if(ok) System.out.println("OK");
	}
	
	private static int excute(int n, char order, int rcnt) {
		int d;
		
		switch(order) {
		case 'L':
			rcnt %= 4;
			d = (robot[n][0] + rcnt) % 4;
			robot[n][0] = d;
			
			break;
		case 'R':
			rcnt %= 4;
			d = ((robot[n][0] - rcnt)+4) % 4;
			robot[n][0] = d;
			
			break;
		case 'F':
			d = robot[n][0];
			int curx = robot[n][1];
			int cury = robot[n][2];
			
			for(int i = 0 ; i < rcnt; i++) {
				curx += delta[d][1];
				cury += delta[d][0];
				
				// 좌표를 벗어나는 경우
				if(curx < 1 || curx > A || cury < 1 || cury > B) {
					return -1;
				}
				// 로봇에 충돌하는 경우
				if(map[cury][curx] != 0) {
					return map[cury][curx];
				}
			}
			// 정상적으로 다 움직이면
			map[robot[n][2]][robot[n][1]] = 0;
			map[cury][curx] = n;
			robot[n][1] = curx;
			robot[n][2] = cury;
			
			break;
		default:
			System.out.println("err");
		}
		
		
		return 0;
	}
}
