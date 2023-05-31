import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int k = Integer.parseInt(split[2]);
		
		boolean[][] map = new boolean[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j) == '1'? false:true;
			}
		}
		
		//최단경로 => BFS
		boolean[][][] visit = new boolean[N][M][k+1];
		boolean flag = false;
		int result = -1;
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,0,0});
		visit[0][0][0] = true;
		int cnt = 0;
		
		out:
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
				int[] now = queue.poll();
				if(now[0] == N-1 && now[1] == M-1) {
					flag = true;
					break out;
				}
				
				for(int d=0;d<4;d++) {
					int X = now[0]+dx[d];
					int Y = now[1]+dy[d];
					int wall = now[2];
					if(X<0 || X>=N || Y<0 || Y>=M) {
						continue;
					}
					if(map[X][Y]) { //벽이 없으면
						if(!visit[X][Y][wall]) {
							visit[X][Y][wall] = true;
							queue.offer(new int[] {X, Y, wall});
						}
					}
					else { //벽이 있으면
						if(wall+1 <= k && !visit[X][Y][wall+1]){
							visit[X][Y][wall+1] = true;
							queue.offer(new int[] {X, Y, wall+1});
						}
					}
				}
			}
			cnt++;
		}
		if(flag) {
			result = cnt+1;
		}
		System.out.println(result);
	}
}
