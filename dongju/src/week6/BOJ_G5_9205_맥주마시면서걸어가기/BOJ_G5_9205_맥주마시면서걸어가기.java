import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static int startX, startY, endX, endY;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			visited = new boolean[n];
			
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			
			arr = new int[n][2];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				arr[i][0] = x;
				arr[i][1] = y;
			}
			
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			bfs(startX, startY); // 시작지점에서 BFS로 갈수있는 정점(편의점) 모두 탐색
			
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs(int startX, int startY) {
		Queue<int[]> que = new ArrayDeque<>();
		
		que.offer(new int[] {startX, startY});
		while(!que.isEmpty()) {
			int[] temp = que.poll();
			int x = temp[0]; 
			int y = temp[1];
			
			if(Math.abs(x-endX) + Math.abs(y-endY) <= 1000) { // 해당 정점에서 도착지와 거리가 1000이하이면 종료
				sb.append("happy");
				return;
			}
			
			for(int i=0; i<arr.length; i++) {
				int nextX = arr[i][0];
				int nextY = arr[i][1];
				
				if(!visited[i] && (Math.abs(x-nextX) + Math.abs(y-nextY)) <= 1000) {
					visited[i] = true;
					que.offer(new int[] {nextX, nextY});
				}
			}
		}
		
		sb.append("sad");
	}
}