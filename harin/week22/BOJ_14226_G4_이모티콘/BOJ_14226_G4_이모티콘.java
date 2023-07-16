import java.io.*;
import java.util.*;

public class BOJ_14226_G4_이모티콘 {

	static class imoticon {
		int clipboard;
		int time;
		int total;
		
		public imoticon(int clipboard, int time, int total) {
			this.clipboard = clipboard;
			this.time = time;
			this.total = total;
		}
	}
	
	static int s; // 이모티콘을 s개 만들어야한다.
	static int ans; 
	
	//BFS 탐색을 위한 변수
	static boolean[][] isVisited = new boolean[1001][1001];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		s = Integer.parseInt(in.readLine());
		
		BFS();
		
		System.out.println(ans);
		
	}

	private static void BFS() {
		Queue<imoticon> queue = new ArrayDeque<>();
		queue.offer(new imoticon(0, 0, 1));
		isVisited[0][1] = true;
		
		while(!queue.isEmpty()) {
			imoticon curIm = queue.poll();
			
			if(curIm.total == s) {
				ans = curIm.time;
				return;
			}
			
			// 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
			queue.offer(new imoticon(curIm.total, curIm.time + 1, curIm.total));
			
			// 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
			if(curIm.clipboard != 0 && curIm.total + curIm.clipboard <= s 
					&& !isVisited[curIm.clipboard][curIm.total + curIm.clipboard]) {
				queue.offer(new imoticon(curIm.clipboard, curIm.time + 1, curIm.total + curIm.clipboard));
				isVisited[curIm.clipboard][curIm.total + curIm.clipboard] = true;
			}
			
			// 화면에 있는 이모티콘 중 하나를 삭제한다.
			if(curIm.total >= 1 && !isVisited[curIm.clipboard][curIm.total - 1]) {
				queue.offer(new imoticon(curIm.clipboard, curIm.time + 1, curIm.total - 1));
				isVisited[curIm.clipboard][curIm.total - 1] = true;
			}
		}
		
	}

}
