import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	
	static char[][] arr = new char[12][6];
	static boolean[][] v;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i=0; i<12; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		int ans = 0;
		while(true) {
			boolean isPuyo = false;
			
			// 뿌요 터트리기
			v = new boolean[12][6];
			for(int i=11; i>=0; i--) {
				for(int j=0; j<6; j++) {
					if(arr[i][j] != '.' && !v[i][j]) {
						if(bfs(i, j)) isPuyo = true;
					}
				}
			}
			
			// 필드 정리하기
			for(int i=0; i<6; i++) {
				cleanField(i);
			}
			
			if(!isPuyo) break;
			
			ans++;
		}
		
		System.out.println(ans);
	}

	private static void cleanField(int j) {
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<12; i++) {
			stack.push(arr[i][j]);
		}
		
		int idx = 11;
		while(!stack.isEmpty()) {
			if(stack.peek() == '#') {
				stack.pop();
				continue;
			}
			
			arr[idx--][j] = stack.pop();
		}
		
		for(int i=idx; i>=0; i--) {
			arr[i][j] = '.';
		}
	}

	private static boolean bfs(int r, int c) {
		Queue<int[]> que = new ArrayDeque<>();
		ArrayList<int[]> list = new ArrayList<>();
		
		list.add(new int[] {r, c});
		que.offer(new int[] {r, c});
		v[r][c] = true;
		char color = arr[r][c];
		while(!que.isEmpty()) {
			int[] temp = que.poll();
			
			for(int i=0; i<4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				
				if(nx>=0 && nx<12 && ny>=0 && ny<6 && arr[nx][ny]==color && !v[nx][ny]) {
					v[nx][ny] = true;
					que.offer(new int[] {nx, ny});
					list.add(new int[] {nx, ny});
				}
			}
		}
		
		if(list.size() >= 4) {
			for(int[] l: list) {
				arr[l[0]][l[1]] = '#';
			}
			
			return true;
		}
		
		return false;
	}
}
