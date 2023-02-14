package src.week2.BOJ_7576_G5_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력파일 객체화
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);	// 2 ≤ M,N ≤ 1,000
		int M = Integer.parseInt(input[1]);

		int[][] map = new int[M][N];
		Queue<Pointer> visitlist = new LinkedList<Pointer>();
		
		boolean isAll = true;
		//입력 받으면서 1인 곳 큐에 넣어주기
		for(int i = 0 ; i < M ; i++) {
			input = br.readLine().split(" ");
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j] == 1)
					visitlist.add(new Pointer(i,j));
				else if(map[i][j] == 0)
					isAll = false;
			}
			map[i] = Arrays.stream(input).mapToInt(s-> Integer.parseInt(s)).toArray();
		}

		// solve
		/*
		 * bfs로 풀면 될것같음.
		 * 1인 곳을 queue에 다 넣고 
		 * 하나씩 빼면서 상하좌우 스택에추가.
		 * 시간초과임.
		 * 왜냐면 stack에다가 넣어놨으니까~ 바보다
		 * 근데 99에서 틀렸다고하네
		 * 와 반례찾음
		 * 2 2
		 * 1 -1
		 * -1 1 이경우를 고려 안해줬었네
		 */

		
		// 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력
		if(isAll) {
			System.out.println(0);
			return;
		}
		
		int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};


		// 2. 스택에서 꺼내면서 상하좌우 스택에넣음.
		while(!visitlist.isEmpty()) {
			Pointer curp = visitlist.poll();

			// 상하좌우
			for(int d = 0 ; d < delta.length; d++) {
				int dx = curp.x + delta[d][0];
				int dy = curp.y + delta[d][1];

				if(dx < 0 || dy < 0 || dx >= M || dy >= N)
					continue;

				int value = map[curp.x][curp.y] +1;
				if(map[dx][dy] == 0 || map[dx][dy] > value) {
					map[dx][dy] = value;
					visitlist.add(new Pointer(dx,dy));
				}
			}
		}

		// 출력
		int result = 0;
		A : 
			for(int i = 0 ; i < M ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(map[i][j] == 0) {
						result = -1;
						break A;
					}
					result = Math.max(result, map[i][j]);
				}
			}
		if(result > 1) result--;
		System.out.println(result);
	}
	

}

class Pointer {
	int x;
	int y;
	public Pointer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
