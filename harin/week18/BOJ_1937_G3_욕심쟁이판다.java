	import java.io.*;
	import java.util.*;
	
	public class BOJ_1937_G3_욕심쟁이판다 {
		
		static class Pos { // 위치 클래스 선언 
			int r;
			int c;
			
			public Pos(int r, int c) {
				super();
				this.r = r;
				this.c = c;
			}
		}
		
		static int N; // map 한 변의 길이
		static int map[][]; 
		static int dp[][];
		
		//탐색 위한 변수
		static int dr[] = {-1, 1, 0, 0}; //상 하 좌 우
		static int dc[] = {0, 0, -1, 1}; 
		
		static int ans = Integer.MIN_VALUE; //답
		
		public static void main(String[] args) throws Exception {
			System.setIn(new FileInputStream("input.txt"));
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			N = Integer.parseInt(in.readLine()); // 한 변의 길이 
			
			map = new int[N][N]; //map 선언
			dp = new int[N][N]; //dp 배열 선언
			
			// map 입력 받기
			StringTokenizer st;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// dfs 모든 map의 지점에 판다를 놔두기 (DP 테이블 갱신)
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					ans = Math.max(dfs(new Pos(i, j)), ans);
				}
			}
			
			System.out.println(ans);
		}
		
		public static int dfs(Pos pos) {
			
			if(dp[pos.r][pos.c] != 0) return dp[pos.r][pos.c]; //한 번 갱신된 지역이라면 기록한 값을 그대로 리턴한다. 
			
			dp[pos.r][pos.c] = 1; // 처음 이동거리 1로 시작
			
			// 주위 방문하기
			for(int i=0; i<4; i++){
				int nr = pos.r + dr[i];
				int nc = pos.c + dc[i];
				
				if(0 <= nr && nr < N && 0 <= nc && nc < N) { //범위 안에 있다면
					// 다음에 갈 곳이 현재 대나무 값보다 크다면 이동 가능
					if(map[nr][nc] > map[pos.r][pos.c]) {
						dp[pos.r][pos.c] = Math.max(dp[pos.r][pos.c], dfs(new Pos(nr, nc)) + 1); // 기존 값과 새로운 값 비교하여 갱신
					}
				}
			}
			
			return dp[pos.r][pos.c];	
		}
	
	}
