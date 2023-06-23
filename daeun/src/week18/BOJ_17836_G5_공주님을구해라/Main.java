import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int T = Integer.parseInt(split[2]);
		int[][] map = new int[N][M];
		boolean[][][] visit = new boolean[N][M][2];
		for(int i=0;i<N;i++) {
			split = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,0,0});
		visit[0][0][0] = true;
		int time = 0;
		boolean flag = false;
		out:
		while(time <= T) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
				int[] now = queue.poll();
				int X = now[0];
				int Y = now[1];
				int weapon = now[2];
				if(X==N-1 && Y==M-1) {
					flag = true;
					break out;
				}
				for(int d=0; d<4; d++) {
					int x = X+ dx[d];
					int y = Y + dy[d];
					if(x<0 || x>=N || y<0 || y>= M || visit[x][y][weapon]) {
						continue;
					}
					//빈 공간 이거나, 벽인데 무기가 있거나
					if(map[x][y] == 0 || (map[x][y] == 1 && weapon == 1)) {
						queue.offer(new int[] {x, y, weapon});
						visit[x][y][weapon] = true;
					}
					//무기거나
					else if(map[x][y] == 2) {
						queue.offer(new int[] {x, y, 1});
						visit[x][y][0] = true;
						visit[x][y][1] = true;
					}
				}
			}
			time++;
		}
		if(flag) {
			System.out.println(time);
		}
		else {
			System.out.println("Fail");
		}
	}
}
