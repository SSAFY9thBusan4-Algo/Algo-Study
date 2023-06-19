import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, ans, coin[][];
	static char arr[][];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new char[n][m];
		coin = new int[2][2];
		
		int idx = 0;
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				arr[i][j] = s.charAt(j);
				
				if(arr[i][j] == 'o') {
					coin[idx][0] = i;
					coin[idx++][1] = j;					
				}
			}
		}
		
		bfs();
		
		if(ans==0 || ans>10) System.out.println(-1);
		else System.out.println(ans);
	}

	private static void bfs() {
		Queue<int[][]> que = new ArrayDeque<>();
		que.offer(coin);
		while(!que.isEmpty()) {
			if(ans++ > 10) return;
			
			int size = que.size();
			while(size-- > 0) {
				int[][] cur = que.poll();
				int[] a = cur[0];
				int[] b = cur[1];
				
				for(int i=0; i<4; i++) {
					int ax = a[0] + dx[i];
					int ay = a[1] + dy[i];
					int bx = b[0] + dx[i];
					int by = b[1] + dy[i];
					
					// 둘 다 동시에 나감
					if(outRange(ax, ay) && outRange(bx, by)) continue;
					// 하나만 나감
					else if(outRange(ax, ay) || outRange(bx, by)) return;
					
					if(arr[ax][ay] == '#') {
						ax = a[0];
						ay = a[1];
					}
					
					if(arr[bx][by] == '#') {
						bx = b[0];
						by = b[1];
					}
					
					que.offer(new int[][] {{ax, ay}, {bx, by}});
				}
			}
		}
	}
	
	static boolean outRange(int x, int y) {
		if(x<0 || x>=n || y<0 || y>=m) return true;
		
		return false;
	}
}
