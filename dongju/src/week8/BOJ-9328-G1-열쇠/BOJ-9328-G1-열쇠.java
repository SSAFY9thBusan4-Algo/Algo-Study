import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static char[][] arr;
	static boolean[] key;
	static int n, m, cnt;
	static boolean[][] visited;
	static ArrayList<int[]>[] room;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			arr = new char[n+2][m+2];
			visited = new boolean[n+2][m+2];
			for(int i=0; i<n+2; i++) {
				Arrays.fill(arr[i], '.');
			}
						
			for(int i=1; i<=n; i++) {
				String s = br.readLine();
				for(int j=1; j<=m; j++) {
					arr[i][j] = s.charAt(j-1);
				}
			}
			
			key = new boolean[26]; // a~z 키를 담을 배열
			room = new ArrayList[26]; // A~Z 문을 담을 배열
			for(int i=0; i<26; i++) {
				room[i] = new ArrayList<>();
			}
			
			String s = br.readLine();
			if(!s.equals("0")) {
				for(int i=0; i<s.length(); i++) {
					key[s.charAt(i)-97] = true;
				}
			}
			
			cnt = 0;
			bfs();

			sb.append(cnt).append("\n");
		}

		System.out.println(sb);
	}

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	private static void bfs() {
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		while(!que.isEmpty()) {
			int[] temp = que.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx>=0 && nx<n+2 && ny>=0 && ny<m+2 && arr[nx][ny]!='*' && !visited[nx][ny]) {
					// 문
					if(arr[nx][ny]-'A'>=0 && arr[nx][ny]-'A'<=25) {
						
						if(key[arr[nx][ny]-'A']) { // 열쇠 O
							arr[nx][ny] = '.';
							visited[nx][ny] = true;
							que.offer(new int[] {nx, ny});
						}
						else { // 열쇠 X
							room[arr[nx][ny]-'A'].add(new int[] {nx, ny});
						}
					}
					
					// 열쇠
					if(arr[nx][ny]-'a'>=0 && arr[nx][ny]-'a'<=25) {
						key[arr[nx][ny]-'a'] = true;
						visited[nx][ny] = true;
						que.offer(new int[] {nx, ny});
						
						for(int j=0; j<26; j++) {
							if(room[j].size()!=0 && key[j]) { // 못 연 문 존재, 키 있음
								
								for(int k=0; k<room[j].size(); k++) {
									int[] open = room[j].get(k);
									arr[open[0]][open[1]] = '.';
									visited[open[0]][open[1]] = true;
									que.offer(new int[] {open[0], open[1]});
								}
							}
						}
					}
					
					// 문서
					if(arr[nx][ny] == '$') {
						cnt++;
						visited[nx][ny] = true;
						que.offer(new int[] {nx, ny});
					}
					
					// 땅
					if(arr[nx][ny] == '.') {
						visited[nx][ny] = true;
						que.offer(new int[] {nx, ny});
					}
				}
			}
		}
	}
}
