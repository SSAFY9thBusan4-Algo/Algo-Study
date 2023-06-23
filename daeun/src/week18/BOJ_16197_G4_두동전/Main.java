import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class Main {
	static class Coin{
		int x;
		int y;
		public Coin(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, dx[] = {0,0,-1,1}, dy[] = {-1,1,0,0};
	static char map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		map = new char[N][M];
		Coin one = null, two = null;
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j);
				if(str.charAt(j) == 'o') {
					if(one == null) one = new Coin(i, j);
					else two = new Coin(i, j);
				}
			}
		} //입력 끝
		
		//최소횟수 => BFS
		System.out.println(bfs(one, two)); 
	}

	private static Integer bfs(Coin one, Coin two) {
		Queue<Coin[]> queue = new ArrayDeque<>();
		HashSet<Integer> visit = new HashSet<>();
		queue.offer(new Coin[] {one, two});
		//27000 900 30 1
		int num = one.x*27000+ one.y*900 + two.x*30 + two.y;
		visit.add(num);
		int cnt = 1;
		
		while(!queue.isEmpty()) {
			if(cnt > 10) return -1;
			int size = queue.size();
			for(int i=0;i<size;i++) {
				Coin[] now = queue.poll();
				for(int d=0;d<4;d++) {
					int x1 = now[0].x+dx[d];
					int y1 = now[0].y+dy[d];

					int x2 = now[1].x+dx[d];
					int y2 = now[1].y+dy[d];
					
					//one만 떨어진 경우
					if(out(x1, y1) && !out(x2, y2)) {
						return cnt;
					}
					//two만 떨어진 경우
					if(!out(x1, y1) && out(x2, y2)) {
						return cnt;
					}
					//둘 다 떨어진 경우
					if(out(x1, y1) && out(x2, y2)) {
						continue;
					}
					//둘 다 벽으로 가는 경우
					if(map[x1][y1]=='#' && map[x2][y2]=='#') {
						continue;
					}
					//one이 벽으로 가는 경우
					if(map[x1][y1]=='#' && map[x2][y2]!='#') {
						//two가 one과 겹치는지 확인
						if(now[0].x == x2 && now[0].y == y2) continue;
						//벽이면 이동하지 않음
						x1 = now[0].x;
						y1 = now[0].y;
					}
					//two가 벽으로 가는 경우
					else if(map[x1][y1]!='#' && map[x2][y2]=='#') {
						//one이 two와 겹치는지 확인
						if(now[1].x == x1 && now[1].y == y1) continue;
						//벽이면 이동하지 않음
						x2 = now[1].x;
						y2 = now[1].y;
					}
					
					num = x1*27000+ y1*900 + x2*30 + y2;
					if(visit.contains(num)) continue;
					
					queue.offer(new Coin[] {new Coin(x1, y1), new Coin(x2, y2)});
					visit.add(num);
				}
			}
			cnt++;
		}
		
		return -1;
	}

	private static boolean out(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=M) {
			return true;
		}
		return false;
	}
	
}
