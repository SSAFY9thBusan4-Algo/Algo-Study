import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, map[][], numbers[], result;
	static int[] dx = {0,0,0,1,-1}, dy = {0,-1,1,0,0};
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		numbers = new int[3];
		for(int i=0;i<N;i++) {
			String[] split = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}// 입력 끝
		
		result = Integer.MAX_VALUE;
    
		//조합으로 3곳 고르고 bfs
		combination(0,0);
		System.out.println(result);
	}

	private static void combination(int cnt, int start) {
		if(cnt == 3) {
			int sum = 0;
			visit = new boolean[N][N];
			for(int i=0;i<3;i++) {
				int x = numbers[i]/N; //행
				int y = numbers[i]%N; //열
				for(int d=0;d<5;d++) {
					int X = x+dx[d];
					int Y = y+dy[d];
					if(X<0 || X>=N || Y<0 || Y>=N) { //화단을 넘어간다면
						return;
					}
					if(visit[X][Y]) { //꽃이 있다면
						return;
					}
					visit[X][Y] = true;
					sum+=map[X][Y];
					if(sum > result) { //합이 이미 최솟값을 넘었다면
						return;
					}
				}
			}
			result = Math.min(result, sum);
			return;
		}
		
		for(int i=start;i<N*N;i++) {
			numbers[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
}
