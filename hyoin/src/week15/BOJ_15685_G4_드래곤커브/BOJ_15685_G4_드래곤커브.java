package week15.BOJ_15685_G4_드래곤커브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685_G4_드래곤커브 {
	public static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		map = new boolean[101][101];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			// 방향 구하기
			List<Integer> directions = new ArrayList<>();
			directions.add(d);
			makeDirection(d,g,directions);
			
			// 꼭짓점 그리기
			drawDot(x,y,directions);
		}
		
		// 정사각형 구하기
		int result=0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]&&map[i+1][j]&&map[i][j+1]&&map[i+1][j+1]) {
					result++;
				}
			}
		}
		
		System.out.println(result);
	}

	// 꼭짓점 그리기
	private static void drawDot(int x, int y, List<Integer> directions) {
		map[x][y]=true;
		
		for(int i=0; i<directions.size(); i++) {
			int d = directions.get(i);
			
			// 꼭짓점 좌표 구하기
			switch(d) {
			case 0:
				x++;
				break;
			case 1:
				y--;
				break;
			case 2:
				x--;
				break;
			case 3:
				y++;
				break;
			}
			
			map[x][y]=true;
		}
	}

	// 방향 구하기
	// 다음 방향은 이전 방향의 반시계 방향으로 90도 회전한 것
	// 최신 방향부터 과거 방향까지 역순으로 방향 생김
	private static void makeDirection(int d, int g, List<Integer> directions) {
		for(int i=0; i<g; i++) { // 세대
			int size = directions.size(); // 이전까지 저장된 방향 크기
			for(int j=0; j<size; j++) {
				int nindex = size-1-j; // 제일 최신 방향의 index
				int ndirection = (directions.get(nindex)+1)%4; // 반시계 방향으로 90도
				directions.add(ndirection);
			}
		}
	}
}
