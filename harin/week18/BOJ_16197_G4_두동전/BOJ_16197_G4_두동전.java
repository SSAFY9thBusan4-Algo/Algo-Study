import java.io.*;
import java.util.*;


public class BOJ_16197_G4_두동전 {

	//동전 클래스
	static class Coins {
		int r1;
		int c1;
		int r2;
		int c2;
		int cnt;
		
		public Coins(int r1, int c1, int r2, int c2, int cnt) {
			super();
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Coins [r1=" + r1 + ", c1=" + c1 + ", r2=" + r2 + ", c2=" + c2 + ", cnt=" + cnt + "]";
		}
	}

	static int N, M; //세로, 가로 크기
	static char[][] map; 

	// BFS 탐색 필요한 것 미리 선언
	static Queue<Coins> queue = new ArrayDeque<Coins>();
	static boolean[][][][] isVisited;
	static int dr[] = {-1, 1, 0, 0}; //상 하 좌 우
	static int dc[] = {0, 0, -1, 1};

	static int ans = -1; // 답 변수 

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); //세로 크기 입력
		M = Integer.parseInt(st.nextToken()); //가로 크기 입력

		map = new char[N][M]; //map 크기
		isVisited = new boolean[N][M][N][M]; // 방문 체크 배열 크기 
		int coinIdx = 0; // 동전 번호 
		int r1=0, c1=0, r2=0, c2=0;
		
		//map 정보 입력 받기
		for(int i=0; i<N; i++) {
			String str = in.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'o') { //o일때 동전 위치 저장
					if(coinIdx==1) {
						r2 = i; c2 = j;
					}
					else {
						r1 = i; c1 = j;
						coinIdx++;
					}
				}
			}
		}
		
		queue.offer(new Coins(r1, c1, r2, c2, 0));
		isVisited[r1][c1][r2][c2] = true;

		BFS();
		
		System.out.println(ans);

	}

	private static void BFS() {
		
		boolean find = false; //찾았다면 true로 탐색 종료시키기
		
		while(!queue.isEmpty() && !find) {
			
			Coins coins = queue.poll();
			
			// coins의 cnt 값이 10이라면 탐색 멈추기
			if(coins.cnt == 10) break;
			
			for(int i=0; i<4; i++) { //인접한 공간으로 이동
				int nr1 = coins.r1 + dr[i];
				int nc1 = coins.c1 + dc[i];
				int nr2 = coins.r2 + dr[i];
				int nc2 = coins.c2 + dc[i];
				
				int outCnt = 0; //떨어진 횟수 변수
				
				// 첫번째 동전이 떨어질 경우 
				if(!isRange(nr1, nc1)) outCnt++;
				
				// 두번째 동전이 떨어질 경우
				if(!isRange(nr2, nc2)) outCnt++;
				
				if(outCnt == 1) { //한 개 떨어졌으면 답 나옴
					ans = coins.cnt + 1;
					find = true;
					break;
				}
				else if(outCnt == 2) continue; //두 개 떨어졌으면 그냥 continue
				
				
				if(isRange(nr1, nc1) && isRange(nr2, nc2)) { // 둘 다 범위 안에 있을 때는 이동시켜야함! (outCnt == 0)
					//만약 다음 이동 칸이 벽이라면 이동하지 않고 계속 그자리 유지!!
					if(map[nr1][nc1] == '#') {
						nr1 = coins.r1;
						nc1= coins.c1;
					}	
					if(map[nr2][nc2] == '#') {
						nr2 = coins.r2; 
						nc2 = coins.c2;
					}
					
					if(!isVisited[nr1][nc1][nr2][nc2]) { //방문 체크
						queue.offer(new Coins(nr1, nc1, nr2, nc2, coins.cnt + 1));
						isVisited[nr1][nc1][nr2][nc2] = true;
					}
				}
			}
			
		}
	}
	
	// 동전이 범위 안에 있는지 체크 (false면 보드에서 떨어진 것)
	private static boolean isRange(int r, int c) {
		if(0 <= r && r < N && 0 <= c && c < M) return true;
		return false;
	}

}

