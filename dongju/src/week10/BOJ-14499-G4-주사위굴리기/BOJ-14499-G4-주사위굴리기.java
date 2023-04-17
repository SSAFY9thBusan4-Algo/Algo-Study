import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static int[] cmd, dice;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dice = new int[6];
	
		
		cmd = new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			cmd[i] = Integer.parseInt(st.nextToken());
			
			int temp;
			switch (cmd[i]) {
			case 1: // 동
				if(y+1 >= m) continue;
				
				temp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[5]; 
				dice[5] = dice[2];
				dice[2] = temp;
				
				if(arr[x][++y] == 0) arr[x][y] = dice[5];
				else {
					dice[5] = arr[x][y];
					arr[x][y] = 0;
				}
				
				sb.append(dice[0]).append("\n");
				break;
			case 2: // 서
				if(y-1 < 0) continue;
				
				temp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[5]; 
				dice[5] = dice[3];
				dice[3] = temp;
				
				if(arr[x][--y] == 0) arr[x][y] = dice[5];
				else {
					dice[5] = arr[x][y];
					arr[x][y] = 0;
				}
				
				sb.append(dice[0]).append("\n");
				break;
			case 3: // 북
				if(x-1 < 0) continue;
				
				temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[5]; 
				dice[5] = dice[1];
				dice[1] = temp;
				
				if(arr[--x][y] == 0) arr[x][y] = dice[5];
				else {
					dice[5] = arr[x][y];
					arr[x][y] = 0;
				}
				
				sb.append(dice[0]).append("\n");
				break;
			case 4: // 남
				if(x+1 >= n) continue;
				
				temp = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[5]; 
				dice[5] = dice[4];
				dice[4] = temp;
				
				if(arr[++x][y] == 0) arr[x][y] = dice[5];
				else {
					dice[5] = arr[x][y];
					arr[x][y] = 0;
				}
				
				sb.append(dice[0]).append("\n");
				break;
			}
		}
		
		System.out.println(sb);
	}
}
