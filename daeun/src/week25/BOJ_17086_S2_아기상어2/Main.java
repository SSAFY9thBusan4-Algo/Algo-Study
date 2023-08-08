import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
	static int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		boolean[][] map = new boolean[N][M];
		for(int i=0;i<N;i++) {
			split = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = split[j].equals("0") ? true : false;
			}
		}
		
		int result = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]) {
					Queue<int[]> queue = new ArrayDeque<>();
					boolean[][] visit = new boolean[N][M];
					queue.offer(new int[] {i, j, 0});
					visit[i][j] = true;
					
					out:
					while(!queue.isEmpty()) {
						int[] now = queue.poll();
						for(int d=0;d<8;d++) {
							int x = now[0] + dx[d];
							int y = now[1] + dy[d];
							if(x<0 || y<0 || x>=N || y>=M || visit[x][y]) {
								continue;
							}
							if(!map[x][y]) {
								result = Math.max(result, now[2]+1);
								break out;
							}
							queue.offer(new int[] {x, y, now[2]+1});
							visit[x][y] = true;
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}
