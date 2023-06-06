package week16.BOJ_14620_S2_꽃길;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14620_S2_꽃길 {
	public static int N;
	public static int[][] map;
	public static int[] dx = {1,-1,0,0};
	public static int[] dy = {0,0,-1,1};
	public static boolean[][] isVisited;
	public static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isVisited = new boolean[N][N];
		result = Integer.MAX_VALUE;
		
		dfs(0,0);
		System.out.println(result);

	}

	private static void dfs(int cnt, int sum) {

		// 기저부분
		if(cnt==3) {
			result = Math.min(result, sum);
			return;
		}
		
		// 가장자리 제외 탐색
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<N-1; j++) {
				if(!isVisited[i][j]) { // 꽃이 심어져있지 않을 때
					if(!isOk(i,j)) { // 꽃을 심을 수 없을 때
						continue;
					}
					
					int fsum=flowerCheck(i,j,true); // 꽃잎을 true로 표시하고 비용계산
					dfs(cnt+1, sum+fsum);
					flowerCheck(i,j,false); // 꽃임을 false로 표시

				}
			}
		}
	}

	// 꽃잎을 true or false로 표시 & 비용계싼
	private static int flowerCheck(int x, int y, boolean status) {
		int sum=0;
		isVisited[x][y]=status;
		sum+=map[x][y];
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			sum+=map[nx][ny];
			isVisited[nx][ny]=status;
		}
		return sum;
	}

	// 현재 좌표 근처로 꽃잎이 있는지 확인
	private static boolean isOk(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(isVisited[nx][ny]) {
				return false;
			}
		}
		return true;
	}

}
