import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dx = {1, 0, -1, 0}; //x축 y축이라서 다르구나...
		int[] dy = {0, -1, 0, 1};
		boolean[][] map = new boolean[101][101];
		for(int i=0;i<N;i++) {
			String[] split = br.readLine().split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			int d = Integer.parseInt(split[2]);
			int g = Integer.parseInt(split[3]);

			ArrayList<Integer> dirlist = new ArrayList<>();
			dirlist.add(d);
			for(int j=0;j<g;j++) { //세대 만큼
				int size = dirlist.size();
				for(int k=size-1;k>=0;k--) { //회전해서 따라 만들어야 하니까 지금 끝점부터 옮기는 느낌으로
					dirlist.add((dirlist.get(k)+1) %4); 
				}
			}
			
			map[x][y] = true;
			for(int dir: dirlist) {
				x+=dx[dir];
				y+=dy[dir];
				map[x][y] = true;
			}
		}
		
		int result = 0;
		for(int j=0;j<100;j++) {
			for(int k=0;k<100;k++) {
				if(map[j][k]&&map[j+1][k]&&map[j][k+1]&&map[j+1][k+1]) {
					result++;
				}
			}
		}
		System.out.println(result);	
	}
}
