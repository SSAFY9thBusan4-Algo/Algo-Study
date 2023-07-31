import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, M, before[][], after[][];
	static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        before = new int[N][M];
        after = new int[N][M];
        for(int i=0;i<N;i++) {
        	split = br.readLine().split(" ");
        	for(int j=0;j<M;j++) {
        		before[i][j] = Integer.parseInt(split[j]);
        	}
        }
        
        for(int i=0;i<N;i++) {
        	split = br.readLine().split(" ");
        	for(int j=0;j<M;j++) {
        		after[i][j] = Integer.parseInt(split[j]);
        	}
        }
        
        out1:
        for(int i=0;i<N;i++) {
        	for(int j=0;j<M;j++) {
        		if(before[i][j] != after[i][j]) { //백신을 맞은 부분
        			bfs(i, j);
        			break out1;
        		}
        	}
        }
        
        boolean result = true;
        out2:
        for(int i=0;i<N;i++) {
        	for(int j=0;j<M;j++) {
        		if(before[i][j] != after[i][j]) {
        			result = false;
        			break out2;
        		}
        	}
        }
        
        System.out.println(result ? "YES" : "NO");
        
    }
	private static void bfs(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][M];
		queue.offer(new int[] {i, j});
		visit[i][j] = true;
		
		int origin = before[i][j];
		int change = after[i][j];
		before[i][j] = after[i][j];
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int d=0;d<4;d++) {
				int x = now[0]+dx[d];
				int y = now[1]+dy[d];

				//범위를 벗어나거나 기존 숫자랑 다른 경우는 패스
				if(x<0 || x>=N || y<0 || y>=M || visit[x][y] || before[x][y]!=origin) {
					continue;
				}
				before[x][y] = change;
				queue.offer(new int[] {x,y});
				visit[x][y] = true;
			}
		}
		
	}
}
