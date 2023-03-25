package week7.BOJ_2206_G3_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, M;
	static long result;
	static boolean[][] map, visit[];
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String[] split = br.readLine().split(" ");
       N = Integer.parseInt(split[0]);
       M = Integer.parseInt(split[1]);
       map = new boolean[N+1][M+1];
       visit = new boolean[N+1][M+1][2];
       for(int i=1;i<N+1;i++) {
    	   String str = br.readLine();
    	   for(int j=1;j<M+1;j++) {
    		   if(str.charAt(j-1)=='0') {
    			   map[i][j] = true;
    		   }
    		   else {
    			   map[i][j] = false;
    		   }
    	   }
       }
       bfs(1,1);
       System.out.println(result);
	}
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {x, y, 1, 0}); //x좌표, y좌표, 거리, 벽뚫 여부
		visit[x][y][0] = true; //벽뚫 유무로 확인해야 할 일이 있으니까 2개로
		int count = 0;
		boolean flag = false;
		
		int[] temp;
		while(!queue.isEmpty()) {
			temp = queue.poll();
			if(temp[0]==N && temp[1]==M) {
				flag = true;
				count = temp[2];
				break;
			}
			
			for(int d=0;d<4;d++) {
				int X = temp[0]+dx[d];
				int Y = temp[1]+dy[d];
				
				if(X>0 && X<=N && Y>0 && Y<=M ) { //일단 범위 안
					if(map[X][Y]) { //벽이 아니면
						if(!visit[X][Y][temp[3]])
						queue.offer(new int[] {X, Y, temp[2]+1, temp[3]});
						visit[X][Y][temp[3]] = true;
					}
					else { //벽이면
						if(temp[3]==0 && !visit[X][Y][1]) { //아직 안 뚫고, 벽을 뚫었을 때 안 갔으면
							queue.offer(new int[] {X, Y, temp[2]+1, 1});
							visit[X][Y][1] = true;
						}
					}					
				}
			}
		
		}
		if(flag) result = count;
		else result = -1;
	}
}