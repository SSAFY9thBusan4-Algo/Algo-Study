import java.io.*;
import java.util.*;

public class BOJ_7682_G5_틱택토 {
	
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String str = in.readLine();
			if(str.equals("end")) break;
			
			int xCnt = 0;
			int oCnt = 0;
			map = new char[3][3];
			for(int i=0; i<9; i++) {
				map[i/3][i%3] = str.charAt(i);
				if(str.charAt(i) == 'X') xCnt++;
				else if(str.charAt(i) == 'O') oCnt++;
			}
			
			// 게임판을 꽉 채웠을 때 
			if(xCnt+oCnt == 9 && xCnt == oCnt + 1) {
				// O가 이기면 불가능 
				if(isPossible('O')) sb.append("invalid").append('\n');
				// X랑 O 공동 우승 불가능 
				else if(isPossible('O') && isPossible('X')) sb.append("invalid").append('\n');
				else sb.append("valid").append('\n');
			}
			// 게임판을 꽉 못 채운 경우 
			else {
				// X랑 O 공동 우승 불가능 
				if(isPossible('O') && isPossible('X')) sb.append("invalid").append('\n');
				// X가 이겼을 경우, O 개수가 X 개수보다 1 작아야함. 
				else if(isPossible('X') && xCnt == oCnt + 1) sb.append("valid").append('\n');
				// O가 이겼을 경우, X개수와 O개수는 같아야한다.
				else if(isPossible('O') && xCnt == oCnt) sb.append("valid").append('\n');
				// 승부가 안 났을 경우 
				else sb.append("invalid").append('\n');
			}
			

		
		};
		
		System.out.println(sb);
	}
	
	
	private static boolean isPossible(char horse) {
		// 가로
		for(int i=0; i<3; i++) {
			int cnt = 0;
			for(int j=0; j<3; j++) {
				if(map[i][j] == horse) cnt++;
			}
			if(cnt == 3) return true;
		}
		
		// 세로
		for(int i=0; i<3; i++) {
			int cnt = 0;
			for(int j=0; j<3; j++) {
				if(map[j][i] == horse) cnt++;
				if(cnt==3) return true;
			}
		}
		
		// 대각선 
		if(map[0][0] == horse && map[1][1] == horse && map[2][2] == horse) return true;
		if(map[0][2] == horse && map[1][1] == horse && map[2][0] == horse) return true; 
		
		return false;
	}

}
