import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static char[][][] arr;
	static int l, r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while(true) {
			st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			if(l==0 && r==0 && c==0) break;
			
			arr = new char[l][r][c];
			
			int startF = 0, startX = 0, startY = 0;
			for(int i=0; i<l; i++) {
				for(int j=0; j<r; j++) {
					String s = br.readLine();
					for(int k=0; k<c; k++) {
						char temp = s.charAt(k);
						
						if(temp == 'S') {
							startF = i;
							startX = j;
							startY = k;
						}
						
						arr[i][j][k] = temp;
					}
				}
				
				br.readLine();
			}
			
			int ans = bfs(startF, startX, startY);
			
			if(ans != -1) sb.append("Escaped in "+ ans +" minute(s).");
			else sb.append("Trapped!");
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	static int[] df = {1, -1, 0, 0, 0, 0};
	static int[] dx = {0, 0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 0, 1, 0, -1};
	private static int bfs(int startF, int startX, int startY) {
		arr[startF][startX][startY] = '#';
		
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {startF, startX, startY});
		int cnt = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			cnt++;
			
			while(size-- > 0) {
				int[] temp = que.poll();
				int f = temp[0];
				int x = temp[1];
				int y = temp[2];
				
				for(int i=0; i<6; i++) {
					int nf = f + df[i];
					int nx = x + dx[i];
					int ny = y + dy[i];
					
                    // if문 왼쪽부터 체크 하면서 범위를 벗어나면 if문 탈출
					if(nf>=0 && nf<l && nx>=0 && nx<r && ny>=0 && ny<c && arr[nf][nx][ny] != '#') {
						if(arr[nf][nx][ny] == 'E') return cnt;
						
						arr[nf][nx][ny] = '#';
						que.offer(new int[] {nf, nx, ny});
					}
				}
			}
		}
		
		return -1;
	}
}
