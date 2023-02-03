package week1.BOJ_6064_S1_카잉달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int temp_x=0;
			int temp_y=0;
			int result=0;
			while(!(temp_x==x&&temp_y==y)) {
				temp_x++;
				temp_y++;
				result++;
				
				if(temp_x==M+1)
					temp_x=1;
				if(temp_y==N+1)
					temp_y=1;
				
				if(temp_x==M&&temp_y==N) {
					result=-1;
					break;
				}
			}
			
			System.out.println(result);
		}
	}

}

