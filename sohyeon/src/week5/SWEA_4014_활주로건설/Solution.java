package week5.SWEA_4014_활주로건설;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
public class Solution {

	private static StringBuilder sb = new StringBuilder();
		
	private static int[][] map;
	private static int result, N, X;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//System.setIn(new FileInputStream(new File("res/input.txt")));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#"+tc+" ");
	
			result = 0;
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
			}
			
			for (int i = 0; i < N; i++) {
				checkRow(i);
				checkCol(i);
			}
			
			sb.append(result+"\n");
			
		}
		
		System.out.println(sb);
	}

	
	private static void checkCol(int c) {
		
		// 높은 지형을 만났을때 cnt1이 X보다 크면 경사로를 만들 수 있음. 
		// 높은 지형을 만났을때 1, 낮은 지형을 만났을때 0로 초기화
		// 이전 지형과 높이가 같을때 cnt2가 0이면 경사로를 만들어햐 하는 지역이 아니므로 +1, cnt2가 0보다 크면 그냥 pass
		int cnt1 = 0;  
		// 낮은 지형을 만났을때 X만큼의 지역에 경사로가 만들어져야 하므로 cnt2 = X
		// cnt2가 0보다 크면 이전칸과 높이를 비교했을 때 같으면 -1, 다르면 활주로 건설 불가
		// cnt2가 0보다 큰데 모든 지역 탐색 끝나면 활주로 건설 불가
		int cnt2 = 0;  
		
		boolean flag = true;
		
		cnt1 += 1;
		for (int i = 1; i < N; i++) {
			
			if (map[i][c] == map[i-1][c]) {
				if (cnt2 == 0) cnt1 +=1;
				else cnt2 -= 1;
			}
			else if (map[i][c]-1 == map[i-1][c]) {  // 지형이 높아졌을 때
				if (cnt2 > 0) {
					flag = false; // 내리면 경사가 지어져야하는데 지형이 높아졌을 때
					break;
				}
				
				if (cnt1 >= X) {  // 이전 지형에 오르막 경사를 지을 길이가 충분하다면
					cnt1 = 1;
				}
				else {
					flag = false;
					break;
				}
			}
			else if (map[i][c]+1 == map[i-1][c]) {  // 지형이 낮아졌을 때
				if (cnt2 > 0) {
					flag = false; // 내리막 경사가 지어져야하는데 지형이 낮아졌을 때
					break;
				}
				cnt2 = X-1;
				cnt1 = 0;
			}
			else { // 높이 차이가 1보다 큰 지형을 만났을 때
				flag = false;
				break;
			}
		}
		
		// flag 가 false이면 건설 불가
		// 모든 지역을 탐색했는데 cnt2가 0이 아니면 마지막에 경사로가 다 만들어지지 못했으므로 건설 불가
		if (flag && cnt2 == 0) {
			result += 1;
		}
	}

	private static void checkRow(int r) {
		
		// 높은 지형을 만났을때 cnt1이 X보다 크면 경사로를 만들 수 있음. 
		// 높은 지형을 만났을때 1, 낮은 지형을 만났을때 0로 초기화
		// 이전 지형과 높이가 같을때 cnt2가 0이면 경사로를 만들어햐 하는 지역이 아니므로 +1, cnt2가 0보다 크면 그냥 pass
		int cnt1 = 0;  
		// 낮은 지형을 만났을때 X만큼의 지역에 경사로가 만들어져야 하므로 cnt2 = X
		// cnt2가 0보다 크면 이전칸과 높이를 비교했을 때 같으면 -1, 다르면 활주로 건설 불가
		// cnt2가 0보다 큰데 모든 지역 탐색 끝나면 활주로 건설 불가
		int cnt2 = 0;  
		
		boolean flag = true;
		
		cnt1 += 1;
		for (int i = 1; i < N; i++) {
			
			if (map[r][i] == map[r][i-1]) {
				if (cnt2 == 0) cnt1 +=1;
				else cnt2 -= 1;
			}
			else if (map[r][i]-1 == map[r][i-1]) {  // 지형이 높아졌을 때
				if (cnt2 > 0) {
					flag = false; // 내리면 경사가 지어져야하는데 지형이 높아졌을 때
					break;
				}
				
				if (cnt1 >= X) {  // 이전 지형에 오르막 경사를 지을 길이가 충분하다면
					cnt1 = 1;
				}
				else {
					flag = false;
					break;
				}
			}
			else if (map[r][i]+1 == map[r][i-1]) {  // 지형이 낮아졌을 때
				if (cnt2 > 0) {
					flag = false; // 내리막 경사가 지어져야하는데 지형이 낮아졌을 때
					break;
				}
				cnt2 = X-1;
				cnt1 = 0;
			}
			else { // 높이 차이가 1보다 큰 지형을 만났을 때
				flag = false;
				break;
			}
		}
		
		// flag 가 false이면 건설 불가
		// 모든 지역을 탐색했는데 cnt2가 0이 아니면 마지막에 경사로가 다 만들어지지 못했으므로 건설 불가
		if (flag && cnt2 == 0) {
			result += 1;
		}
		
	}


}