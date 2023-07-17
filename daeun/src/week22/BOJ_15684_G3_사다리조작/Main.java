import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, M, H, cnt, map[][];
	static boolean clear;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]); //세로
        M = Integer.parseInt(split[1]); //이미 칠해진 가로선
        H = Integer.parseInt(split[2]); //가로

        map = new int[H+1][N+1];
        for(int i=0;i<M;i++){
            split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            //1 1 이면 (1,1) - (1,2) 연결
            map[a][b] = 1; //사다리 줄의 오른쪽이 연결
            map[a][b+1] = -1; //사다리 줄의 왼쪽이 연결
        }
        
        for(int i=0;i<4;i++) {
        	cnt = i; //몇개 추가했을 경우로 할건지
        	dfs(1, 1, 0);
        	if(clear) {
        		break;
        	}
        }
        System.out.println(clear ? cnt : -1);
    }

	private static void dfs(int x, int y, int add) {
		if(clear) {
			return;
		}
		if(cnt == add) {
			if(check()) {
				clear = true;
			}
			return;
		}
		
		for(int i=y;i<H+1;i++) {
			for(int j=x;j<N;j++) {
				if(map[i][j]==0 && map[i][j+1]==0) { //다른 가로랑 연결 안 되게
					map[i][j] = 1;
					map[i][j+1] = -1;
					
					dfs(1, 1, add+1);
					
					//영향 미치니까 다시 빼고
					map[i][j] = 0;
					map[i][j+1] = 0;
				}
			}
		}
	}

	private static boolean check() {
		for(int i=1;i<N+1;i++) {
			int x = 1;
			int y = i;
			
			while(x<H+1) {
				if(map[x][y] == 1) { //오른쪽
					y++;
				}
				else if(map[x][y] == -1) { //왼쪽
					y--;
				}
				x++; //아래
			}
			
			//i번이 i번에 도착하는지
			if(y != i) {
				return false;
			}
		}
		
		return true;
	}
}
